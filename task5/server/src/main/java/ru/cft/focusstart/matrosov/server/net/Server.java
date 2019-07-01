package ru.cft.focusstart.matrosov.server.net;

import ru.cft.focusstart.matrosov.common.*;
import ru.cft.focusstart.matrosov.server.exception.ClientException;
import ru.cft.focusstart.matrosov.server.exception.ServerException;
import ru.cft.focusstart.matrosov.server.service.Services;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    public static final Server INSTANCE = new Server();

    private ServerSocket serverSocket;

    private Server() {}

    public void start() throws ServerException {

        Properties properties = new Properties();
        try (InputStream propertiesStream = Server.class.getClassLoader().getResourceAsStream("server.properties")) {
            properties.load(Objects.requireNonNull(propertiesStream));
            serverSocket = new ServerSocket(Integer.valueOf(properties.getProperty("server.port")));
            startSocketObserver();
        } catch (Exception e) {
            throw new ServerException("Не удалось загрузить сервер", e);
        }

        addShutdownHook();
    }

    private void addChatMessageListener(Client client) {
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    JsonMessage message = getNextMessage(client);

                    if (message instanceof ChatMessage) {
                        Services.getClientService().sendChatMessageToAll((ChatMessage) message);
                    } else {
                        Services.getClientService().removeClient(client);
                        client.disconnect();
                        break;
                    }

                } catch (ClientException e) {
                    System.out.println("Ошибка при работе с клиентом: " + e.getMessage());
                }
            }
        }).start();
    }

    private void addAuthMessageListener(Client client) {
        new Thread(() -> {
            try {
                JsonMessage message = getNextMessage(client);

                if (message instanceof LoginMessage) {
                    LoginMessage msg = (LoginMessage) message;
                    Services.getClientService().addClient(client, msg);
                    addChatMessageListener(client);
                } else {
                    client.disconnect();
                }
            }  catch (ClientException e) {
                System.out.println("Ошибка при работе с клиентом: " + e.getMessage());
            }
        }).start();
    }

    private JsonMessage getNextMessage(Client client) {
        JsonMessage message = null;
        try {
            message = client.getMessage();
        } catch (ClientException e) {
            /* Ignore but got msg=null as a result */
        }
        return message;
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                serverSocket.close();
                try {
                    Services.getClientService().disconnectAll();
                } catch (ClientException e) {
                    System.out.println(e.getMessage());
                }
            } catch (IOException e) {
                System.out.println("Ошибка при завершении работы сервера" + e.getMessage());
            }
        }));
    }

    private void startSocketObserver() throws ServerException {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                try {
                    Client newClient = new Client(clientSocket);
                    addAuthMessageListener(newClient);
                } catch (IOException e) {
                    System.out.println("При подключении клиента возникла ошибка " + e.getMessage());
                }
            } catch (IOException e) {
                throw new ServerException("При работе сервера возникла ошибка", e);
            }
        }
    }
}
