package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.LoginMessage;
import ru.cft.focusstart.matrosov.server.exception.ServiceClientException;
import ru.cft.focusstart.matrosov.server.exception.ServiceMessageException;
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
     * @throws ServiceClientException if some errors occurred while disconnecting
     */
    void disconnectAll() throws ServiceClientException;

    /**
     * Disconnects one specific client from the server
     *
     * @param client client to be disconnected
     * @throws ServiceClientException if some problem with client disconnection occurred
     * @throws ServiceMessageException if problem happen while sending info messages to other clients
     */
    void disconnect(Client client) throws ServiceClientException, ServiceMessageException;

    /**
     * Sets the user object to the active client. That means setting the info client was passed to the server about him
     *
     * @param client client to be updated
     * @param message message with user's info
     * @throws ServiceClientException  if some problem with client occurred
     * @throws ServiceMessageException if problem happen while sending info messages to other clients
     */
    void setUser(Client client, LoginMessage message) throws ServiceClientException, ServiceMessageException;
}
