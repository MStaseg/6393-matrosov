package ru.cft.focusstart.matrosov.client;

import ru.cft.focusstart.matrosov.client.controller.ControllerManager;
import ru.cft.focusstart.matrosov.client.model.ConnectionManager;

import java.io.IOException;

public class ClientApplication {
    public static void main(String[] args) {
        ControllerManager.connectionController.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                ConnectionManager.INSTANCE.disconnect();
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии соединения" + e.getMessage());
            }
        }));
    }
}
