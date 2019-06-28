package ru.cft.focusstart.matrosov.client;

import ru.cft.focusstart.matrosov.client.controller.ControllerManager;
import ru.cft.focusstart.matrosov.client.model.ConnectionManager;

import java.io.IOException;
import java.net.Socket;

public class ClientApplication {
    public static void main(String[] args) {
        ControllerManager.connectionController.setVisible(true);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Socket socket = ConnectionManager.getInstance().getSocket();
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}
