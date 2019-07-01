package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.*;
import ru.cft.focusstart.matrosov.server.exception.ClientException;
import ru.cft.focusstart.matrosov.server.net.Client;

import java.util.List;

/**
 * interface holding all necessary methods to work with clients
 */
public interface ClientService {

    /**
     * Method returns all active clients
     *
     * @return list of the client
     */
    List<Client> getClients();

    /**
     * Disconnects all active clients from the server
     *
     * @throws ClientException if some errors occurred while disconnecting
     */
    void disconnectAll() throws ClientException;

    /**
     * Sets the user object to the active client. That means setting the info client was passed to the server about him
     *
     * @param client client to be updated
     * @param message message with user's info
     * @throws ClientException  if some problem with client occurred
     */
    void addClient(Client client, LoginMessage message) throws ClientException;

    /**
     * Removes the client from active clients list
     *
     * @param client to be removed
     * @throws ClientException if some error occurred while closing socket or sending info messages
     */
    void removeClient(Client client) throws ClientException;

    /**
     * Sends chat message to all active clients
     *
     * @param message to be send
     */
    void sendChatMessageToAll(ChatMessage message) throws ClientException;

    /**
     * Sends info message to all active clients
     *
     * @param message to be send
     */
    void sendInfoMessageToAll(InfoMessage message) throws ClientException;

    /**
     * Sends client list message to all active clients
     *
     * @param message to be send
     */
    void sendClientListMessageToAll(ClientListMessage message) throws ClientException;
}
