package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.JsonMessage;
import ru.cft.focusstart.matrosov.server.exception.ServiceMessageException;
import ru.cft.focusstart.matrosov.server.net.Client;

/**
 * basic interface holding all necessary methods to work with json messages
 */
public interface MessageService {

    /**
     * Adds new message to the repository
     *
     * @param message new message to be added
     * @return instance of the message that was add
     */
    JsonMessage add(JsonMessage message);

    /**
     * Sends the json message to the specific client
     *
     * @param client that should get the message
     * @param message to be send
     * @throws ServiceMessageException if some errors was occurred while sending the message
     */
    void sendMessage(Client client, JsonMessage message) throws ServiceMessageException;

    /**
     * Sends the message to all active clients
     *
     * @param message to be send
     * @throws ServiceMessageException if some errors was occurred while sending the message
     */
    void sendMessageToAll(JsonMessage message) throws ServiceMessageException;
}
