package ru.cft.focusstart.matrosov.server;

import ru.cft.focusstart.matrosov.server.exception.ServerException;
import ru.cft.focusstart.matrosov.server.net.Server;

public class ServerApplication {

    public static void main(String[] args) {
        try {
            Server.getInstance().start();
        } catch (ServerException e) {
            System.out.println(e.getMessage());
        }

    }

}

