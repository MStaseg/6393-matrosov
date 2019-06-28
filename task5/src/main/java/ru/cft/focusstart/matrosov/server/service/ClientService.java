package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.Message;
import ru.cft.focusstart.matrosov.server.exception.ServiceClientException;
import ru.cft.focusstart.matrosov.server.net.Client;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public interface ClientService {
    void addClient(Socket socket) throws ServiceClientException;
    List<Client> getClients();
    void disconnectAll() throws ServiceClientException;
    void disconnect(Client client) throws ServiceClientException;
}
