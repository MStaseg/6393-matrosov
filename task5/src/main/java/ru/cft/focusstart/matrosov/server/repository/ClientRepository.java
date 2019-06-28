package ru.cft.focusstart.matrosov.server.repository;

import ru.cft.focusstart.matrosov.server.net.Client;

import java.util.List;

public interface ClientRepository {
    void add(Client client);
    void remove(Client client);
    List<Client> get();
}
