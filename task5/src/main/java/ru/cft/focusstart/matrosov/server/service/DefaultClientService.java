package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.Message;
import ru.cft.focusstart.matrosov.server.exception.ServiceClientException;
import ru.cft.focusstart.matrosov.server.net.Client;
import ru.cft.focusstart.matrosov.server.repository.Repositories;
import ru.cft.focusstart.matrosov.server.util.JsonParser;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class DefaultClientService implements ClientService {

    static final DefaultClientService instance = new DefaultClientService();

    private DefaultClientService() {}

    @Override
    public void addClient(Socket socket) throws ServiceClientException {
        try {
            Client client = new Client(socket);
            Repositories.getClientRepository().add(client);
        } catch (IOException e) {
            throw new ServiceClientException("Не удалось создать нового клиента", e);
        }
    }

    @Override
    public List<Client> getClients() {
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
    public void disconnect(Client client) throws ServiceClientException {
        try {
            client.disconnect();
            Repositories.getClientRepository().remove(client);
        } catch (IOException e) {
            throw new ServiceClientException("Ошибка при отсоединении клиента " + client.getName());
        }
    }
}
