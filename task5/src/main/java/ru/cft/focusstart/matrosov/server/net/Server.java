package ru.cft.focusstart.matrosov.server.net;

import ru.cft.focusstart.matrosov.common.*;
import ru.cft.focusstart.matrosov.server.ServerApplication;
import ru.cft.focusstart.matrosov.server.exception.ServiceClientException;
import ru.cft.focusstart.matrosov.server.service.Services;
import ru.cft.focusstart.matrosov.server.util.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Server {

    private static Server instance;

    private ServerSocket serverSocket;

    private Thread messageListenerThread;

    private Server() {}

    public static synchronized Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }

        return instance;
    }

    public void start() throws IOException, ServiceClientException {

        Properties properties = new Properties();
        try (InputStream propertiesStream = ServerApplication.class.getClassLoader().getResourceAsStream("server.properties")) {
            properties.load(propertiesStream);
        }

        serverSocket = new ServerSocket(Integer.valueOf(properties.getProperty("server.port")));

        addMessageListener();
        addShutdownHook();
        startSocketObserver();

    }

    private void addMessageListener() {
        messageListenerThread = new Thread(() -> {
            boolean interrupted = false;
            while (!interrupted) {
                try {
                    List<Client> clients = Services.getClientService().getClients();
                    Iterator<Client> iterator = clients.iterator();

                    while (iterator.hasNext()) {
                        Client client = iterator.next();
                        String msgString = client.getMessage();

                        if (msgString != null) {
                            if (client.getName() == null)  {
                                JsonMessage message
                                        = JsonParser.getInstance().readValue(msgString, JsonMessage.class);
                                if (message instanceof ClientMessage) {

                                    ClientMessage msg = (ClientMessage) message;
                                    boolean incorrectUserName = false;
                                    for (Client c : clients) {
                                        if (msg.getUserName().equals(c.getName())) {
                                            incorrectUserName = true;
                                        }
                                    }

                                    if (incorrectUserName) {
                                        ErrorMessage errorMessage = new ErrorMessage("Такой ник уже занят другим пользователем");
                                        Services.getMessageService().sendMessage(client, errorMessage);
                                    } else {

                                        SuccessMessage successMessage = new SuccessMessage(true);
                                        Services.getMessageService().sendMessage(client, successMessage);


                                        client.setName(msg.getUserName());
                                        List<ClientMessage> list = new ArrayList<>();
                                        list.add(msg);
                                        Services.getMessageService().sendMessage(clients, new ClientList(list));


                                        InfoMessage infoMessage = new InfoMessage(msg.getUserName() + " присоединился");
                                        Services.getMessageService().sendMessage(clients, infoMessage);
                                    }
                                }
                            } else {
                                JsonMessage message = JsonParser.getInstance().readValue(msgString, JsonMessage.class);
                                if (message != null) {
                                    Services.getMessageService().sendMessage(clients, message);
                                }
                            }
                        } else {
                            if (client.getName() != null) {
                                InfoMessage infoMessage = new InfoMessage(client.getName() + " отключился");
                                Services.getMessageService().sendMessage(clients, infoMessage);
                            }
                            iterator.remove();
                            Services.getClientService().disconnect(client);
                        }
                    }


                } catch (ServiceClientException e) {
                    System.out.println("err");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        });
        messageListenerThread.start();
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                messageListenerThread.interrupt();
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

    private void startSocketObserver() throws ServiceClientException, IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            Services.getClientService().addClient(clientSocket);
        }
    }
}
