package ru.cft.focusstart.matrosov.client.observer;

import ru.cft.focusstart.matrosov.common.LoginMessage;
import ru.cft.focusstart.matrosov.entity.User;

import java.util.List;

public interface ClientObserver {
    void onNewClient(LoginMessage client);
    void onNewClientList(List<User> clients);
}
