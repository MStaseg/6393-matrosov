package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.Message;
import ru.cft.focusstart.matrosov.server.net.Client;
import ru.cft.focusstart.matrosov.server.repository.Repositories;

import java.net.Socket;
import java.util.List;

public class DefaultClientService implements ClientService {
    @Override
    public void addClient(Socket socket) {
        Client client = new Client(socket);
        Repositories.getClientRepository().add(client);
    }

    @Override
    public void sendMessage(Message message) {

    }

    @Override
    public void sendMessageList(List<Message> messages) {

    }
}
