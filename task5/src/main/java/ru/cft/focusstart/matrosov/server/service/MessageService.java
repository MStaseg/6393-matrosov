package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.JsonMessage;
import ru.cft.focusstart.matrosov.common.Message;
import ru.cft.focusstart.matrosov.server.exception.ServiceClientException;
import ru.cft.focusstart.matrosov.server.exception.ServiceMessageException;
import ru.cft.focusstart.matrosov.server.net.Client;

import java.util.List;

public interface MessageService {
    JsonMessage add(JsonMessage message);
    void sendMessage(Client client, JsonMessage message) throws ServiceMessageException;
    void sendMessageToAll(JsonMessage message) throws ServiceMessageException;
}
