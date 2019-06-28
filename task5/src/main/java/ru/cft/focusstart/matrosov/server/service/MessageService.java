package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.JsonMessage;
import ru.cft.focusstart.matrosov.common.Message;
import ru.cft.focusstart.matrosov.server.exception.ServiceClientException;
import ru.cft.focusstart.matrosov.server.net.Client;

import java.util.List;

public interface MessageService {
    Message add(Message message);
    List<Message> getMessages();
    void sendMessage(Client client, JsonMessage message) throws ServiceClientException;
    void sendMessage(List<Client> clients, JsonMessage message) throws ServiceClientException;
    void sendMessageList(Client client, List<Message> messages) throws ServiceClientException;
}
