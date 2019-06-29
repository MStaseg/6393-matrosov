package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.*;
import ru.cft.focusstart.matrosov.server.exception.ServiceClientException;
import ru.cft.focusstart.matrosov.server.exception.ServiceMessageException;
import ru.cft.focusstart.matrosov.server.net.Client;
import ru.cft.focusstart.matrosov.server.repository.Repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultClientService implements ClientService {

    static final DefaultClientService instance = new DefaultClientService();

    private DefaultClientService() {}

    @Override
    public synchronized List<Client> getClients() {
        return Repositories.getClientRepository().get();
    }

    @Override
    public void disconnectAll() throws ServiceClientException {
        List<Client> clients = getClients();
        for (Client client : clients) {
            try {
                client.disconnect();
            } catch (IOException e) {
                throw new ServiceClientException("Ошибка при отсоединении клиента " + client.getName());
            }
        }
    }

    @Override
    public void disconnect(Client client) throws ServiceClientException, ServiceMessageException {
        try {
            client.disconnect();
            Repositories.getClientRepository().remove(client);
            if (client.getName() != null) {
                List<Client> clients = Repositories.getClientRepository().get();
                List<ClientMessage> list = new ArrayList<>();
                for (Client c: clients) {
                    list.add(new ClientMessage(c.getName()));
                }
                Services.getMessageService().sendMessageToAll(new ClientList(list));
                InfoMessage infoMessage = new InfoMessage(client.getName() + " отключился");
                Services.getMessageService().sendMessageToAll(infoMessage);
            }
        } catch (IOException e) {
            throw new ServiceClientException("Ошибка при отсоединении клиента " + client.getName());
        }
    }

    @Override
    public void setName(Client client, ClientMessage message) throws ServiceMessageException {
        List<Client> clients = Services.getClientService().getClients();
        boolean incorrectUserName = false;
        for (Client c : clients) {
            if (message.getUserName().equals(c.getName())) {
                incorrectUserName = true;
            }
        }

        if (incorrectUserName) {
            ErrorMessage errorMessage = new ErrorMessage("Такой ник уже занят другим пользователем");
            Services.getMessageService().sendMessage(client, errorMessage);
        } else {
            Repositories.getClientRepository().add(client);
            clients = Services.getClientService().getClients();

            SuccessMessage successMessage = new SuccessMessage(true);
            Services.getMessageService().sendMessage(client, successMessage);

            client.setName(message.getUserName());
            List<ClientMessage> list = new ArrayList<>();
            for (Client c: clients) {
                list.add(new ClientMessage(c.getName()));
            }
            Services.getMessageService().sendMessageToAll(new ClientList(list));


            InfoMessage infoMessage = new InfoMessage(message.getUserName() + " присоединился");
            Services.getMessageService().sendMessageToAll(infoMessage);
        }
    }
}
