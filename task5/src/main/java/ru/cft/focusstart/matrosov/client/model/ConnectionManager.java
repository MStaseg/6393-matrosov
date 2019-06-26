package ru.cft.focusstart.matrosov.client.model;

import ru.cft.focusstart.matrosov.client.exception.ConnectionManagerException;

import java.io.IOException;
import java.net.Socket;

public class ConnectionManager {

    private static ConnectionManager instance;

    private static Socket socket;

    private boolean isAuthorized;

    private ConnectionManager() {}

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }

        return instance;
    }

    public boolean checkAuthorized() {
        return isAuthorized;
    }

    public void connect(String host, int port) throws ConnectionManagerException {
        try {
            socket = new Socket(host, port);
            isAuthorized = true;
        } catch (IOException e) {
            socket = null;
            isAuthorized = false;
            throw new ConnectionManagerException("Не удалось подключиться к заданному адресу", e);
        }
    }
}
