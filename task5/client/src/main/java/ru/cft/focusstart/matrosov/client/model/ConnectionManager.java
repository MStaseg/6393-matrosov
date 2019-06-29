package ru.cft.focusstart.matrosov.client.model;

import ru.cft.focusstart.matrosov.client.exception.ConnectionManagerException;
import ru.cft.focusstart.matrosov.client.observer.ErrorObserver;
import ru.cft.focusstart.matrosov.common.LoginMessage;
import ru.cft.focusstart.matrosov.common.InfoMessage;
import ru.cft.focusstart.matrosov.common.JsonMessage;
import ru.cft.focusstart.matrosov.entity.User;
import ru.cft.focusstart.matrosov.util.JsonMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {

    public static final ConnectionManager instance = new ConnectionManager();

    private static Socket socket;

    private boolean isAuthorized;

    private String host;

    private int port;

    private User user;

    private PrintWriter writer;
    private BufferedReader reader;

    private List<ErrorObserver> errorObservers;

    private ConnectionManager() {
        errorObservers = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public Socket getSocket() {
        return socket;
    }

    public void addErrorObserver(ErrorObserver o) {
        errorObservers.add(o);
    }

    public void removeErrorObserver(ErrorObserver o) {
        errorObservers.remove(o);
    }

    public void connect(String host, int port, String name) throws ConnectionManagerException {

        this.host = host;
        this.port = port;
        this.user = new User(name);

        try {
            socket = new Socket(host, port);

            isAuthorized = true;

            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            LoginMessage authMessage = new LoginMessage(this.user);

            writer.println(JsonMapper.instance.mapToString(authMessage));
            writer.flush();

            addMessageListenerThread();
        } catch (IOException e) {
            socket = null;
            throw new ConnectionManagerException("Ошибка при подключении к серверу: " + e.getMessage());
        }
    }

    void sendMessage(JsonMessage message) {
        try {
            writer.println(JsonMapper.instance.mapToString(message));
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при отправке сообщения: " + e.getMessage());
        }
    }

    private void addMessageListenerThread() {
        Thread messageListenerThread = new Thread(() -> {
            boolean interrupted = false;
            while (!interrupted) {
                try {
                    String msg = reader.readLine();
                    if (msg != null) {
                        JsonMessage message = JsonMapper.instance.mapToMessage(msg);
                        MessageManager.instance.processMessage(message);
                    } else {
                        if (isAuthorized) {
                            for (ErrorObserver o : errorObservers) {
                                o.onError("Опаньки, сервер упал. Ждем переподключения...");
                            }
                            isAuthorized = false;
                        }

                        try {
                            connect(host, port, user.getName());
                            MessageManager.instance.processMessage(new InfoMessage("Сервер снова доступен"));
                        } catch (ConnectionManagerException e) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException i) {
                                interrupted = true;
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Ошибка при чтении потока сокета " + e.getMessage());
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
}
