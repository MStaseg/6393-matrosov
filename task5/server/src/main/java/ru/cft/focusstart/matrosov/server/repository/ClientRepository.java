package ru.cft.focusstart.matrosov.server.repository;

import ru.cft.focusstart.matrosov.server.net.Client;

import java.util.List;

/**
 * Basic interface for class holding server clients
 */
public interface ClientRepository {

    /**
     * Adds new client to the repo
     *
     * @param client new client
     */
    void add(Client client);

    /**
     * Removes client from the repo
     *
     * @param client current client
     */
    void remove(Client client);

    /**
     * Method return all existing active clients
     *
     * @return list of clients
     */
    List<Client> get();
}
