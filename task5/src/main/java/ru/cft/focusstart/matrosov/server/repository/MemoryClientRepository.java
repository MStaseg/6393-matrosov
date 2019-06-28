package ru.cft.focusstart.matrosov.server.repository;

import ru.cft.focusstart.matrosov.server.net.Client;

import java.util.ArrayList;
import java.util.List;

public class MemoryClientRepository implements ClientRepository {

    static final MemoryClientRepository instance = new MemoryClientRepository();

    private List<Client> clients = new ArrayList<>();

    private MemoryClientRepository() {}

    @Override
    public void add(Client client) {
        clients.add(client);
    }

    @Override
    public void remove(Client client) {
        clients.remove(client);
    }

    @Override
    public List<Client> get() {
        return new ArrayList<>(clients);
    }
}
