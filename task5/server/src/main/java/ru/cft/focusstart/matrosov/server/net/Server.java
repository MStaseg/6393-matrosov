package ru.cft.focusstart.matrosov.server.net;

import ru.cft.focusstart.matrosov.common.*;
import ru.cft.focusstart.matrosov.server.exception.ServerException;
import ru.cft.focusstart.matrosov.server.exception.ServiceClientException;
import ru.cft.focusstart.matrosov.server.exception.ServiceMessageException;
import ru.cft.focusstart.matrosov.server.service.Services;
import ru.cft.focusstart.matrosov.util.JsonMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    public static final Server instance = new Server();

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

    private void addMessageListener(Client client) {
        new Thread(() -> {
            boolean interrupted = false;
            while (!interrupted) {
                try {
                    String msgString = client.getMessage();
                    if (msgString != null) {
                        JsonMessage message = JsonMapper.instance.mapToMessage(msgString);
                        if (client.getUser() == null) {
                            if (message instanceof LoginMessage) {
                                LoginMessage msg = (LoginMessage) message;
                                System.out.println(message);
                                Services.getClientService().setUser(client, msg);
                            }
                        } else {
                            Services.getMessageService().sendMessageToAll(message);
                        }
                    } else {
                        Services.getClientService().disconnect(client);
                        interrupted = true;
                    }

                } catch (ServiceClientException e) {
                    System.out.println("Ошибка при работе с клиентом: " + e.getMessage());
                } catch (ServiceMessageException e) {
                    System.out.println("Ошибка при работе с сообщениями: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Неизвестная ошибка: " + e.getMessage());
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        }).start();
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                serverSocket.close();
                try {
                    Services.getClientService().disconnectAll();
                } catch (ServiceClientException e) {
                    System.out.println(e.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    private void startSocketObserver() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            Client newClient = new Client(clientSocket);
            addMessageListener(newClient);
        }
    }
}
