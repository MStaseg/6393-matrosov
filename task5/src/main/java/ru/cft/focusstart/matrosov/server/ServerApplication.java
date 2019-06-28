package ru.cft.focusstart.matrosov.server;

import ru.cft.focusstart.matrosov.server.exception.ServiceClientException;
import ru.cft.focusstart.matrosov.server.net.Server;

import java.io.*;

public class ServerApplication {

    public static void main(String[] args) throws IOException {
        try {
            Server.getInstance().start();
        } catch (ServiceClientException e) {
            System.out.println("Ошибка при работе с клиентом: " + e.getMessage());
        }
    }

}

