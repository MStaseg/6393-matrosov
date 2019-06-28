package ru.cft.focusstart.matrosov.client.observer;

import ru.cft.focusstart.matrosov.common.ClientMessage;

import java.util.List;

public interface ClientObserver {
    void onNewClient(ClientMessage client);
    void onNewClientList(List<ClientMessage> clients);
}
