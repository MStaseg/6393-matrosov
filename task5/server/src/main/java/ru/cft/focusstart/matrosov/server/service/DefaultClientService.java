package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.*;
import ru.cft.focusstart.matrosov.entity.User;
import ru.cft.focusstart.matrosov.server.exception.ClientException;
import ru.cft.focusstart.matrosov.server.net.Client;
import ru.cft.focusstart.matrosov.server.repository.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultClientService implements ClientService {

    static final DefaultClientService INSTANCE = new DefaultClientService();

    private final List<Client> clientList;

    private DefaultClientService() {
        clientList = new CopyOnWriteArrayList<>();
    }

    @Override
    public List<Client> getClients() {
        return new ArrayList<>(clientList);
    }

    @Override
    public void disconnectAll() throws ClientException {
        for (Client client : clientList) {
            client.disconnect();
        }
    }

    @Override
    public void addClient(Client client, LoginMessage message) throws ClientException {

        boolean incorrectUserName = false;
        for (Client c : clientList) {
            if (message.getUser().equals(c.getUser())) {
                incorrectUserName = true;
            }
        }

        if (incorrectUserName) {
            LoginFailMessage errorMessage = new LoginFailMessage("Такой ник уже занят другим пользователем");
            client.sendMessage(errorMessage);
        } else {
            client.setUser(message.getUser());
            clientList.add(client);

            LoginSuccessMessage successMessage = new LoginSuccessMessage();
            client.sendMessage(successMessage);

            List<User> list = new ArrayList<>();
            for (Client c: clientList) {
                list.add(c.getUser());
            }
            sendClientListMessageToAll(new ClientListMessage(list));

            InfoMessage infoMessage = new InfoMessage(message.getUser().getName() + " присоединился");
            sendInfoMessageToAll(infoMessage);
        }
    }

    @Override
    public void removeClient(Client client) throws ClientException {
        clientList.remove(client);

        List<User> list = new ArrayList<>();
        for (Client c: clientList) {
            list.add(c.getUser());
        }

        sendClientListMessageToAll(new ClientListMessage(list));
        InfoMessage infoMessage = new InfoMessage(client.getUser().getName() + " отключился");
        sendInfoMessageToAll(infoMessage);
    }

    @Override
    public void sendChatMessageToAll(ChatMessage message) throws ClientException {
        Repositories.getMessageRepository().add(message);
        sendMessageToAll(message);
    }

    @Override
    public void sendInfoMessageToAll(InfoMessage message) throws ClientException {
        Repositories.getMessageRepository().add(message);
        sendMessageToAll(message);
    }

    @Override
    public void sendClientListMessageToAll(ClientListMessage message) throws ClientException {
        sendMessageToAll(message);
    }

    private void sendMessageToAll(JsonMessage message) throws ClientException {
        for (Client client: clientList) {
            client.sendMessage(message);
        }
    }
}
