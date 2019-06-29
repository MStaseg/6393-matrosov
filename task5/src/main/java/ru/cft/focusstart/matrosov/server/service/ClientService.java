package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.ClientMessage;
import ru.cft.focusstart.matrosov.common.JsonMessage;
import ru.cft.focusstart.matrosov.common.Message;
import ru.cft.focusstart.matrosov.server.exception.ServiceClientException;
import ru.cft.focusstart.matrosov.server.exception.ServiceMessageException;
import ru.cft.focusstart.matrosov.server.net.Client;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public interface ClientService {
    List<Client> getClients();
    void disconnectAll() throws ServiceClientException;
    void disconnect(Client client) throws ServiceClientException, ServiceMessageException;
    void setName(Client client, ClientMessage message) throws ServiceClientException, ServiceMessageException;
}
