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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionManager {

    public static final ConnectionManager INSTANCE = new ConnectionManager();

    private Socket socket;

    private boolean isAuthorized;

    private String host;

    private int port;

    private User user;

    private PrintWriter writer;
    private BufferedReader reader;

    private List<ErrorObserver> errorObservers;

    private final JsonMapper jsonMapper = JsonMapper.INSTANCE;

    Thread messageListenerThread;

    private ConnectionManager() {
        errorObservers = new CopyOnWriteArrayList<>();
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

            writer.println(jsonMapper.mapToString(authMessage));
            writer.flush();

            addMessageListenerThread();
        } catch (IOException e) {
            socket = null;
            throw new ConnectionManagerException("Ошибка при подключении к серверу: " + e.getMessage());
        }
    }

    void sendMessage(JsonMessage message) {
        try {
            writer.println(jsonMapper.mapToString(message));
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при отправке сообщения: " + e.getMessage());
        }
    }

    private void addMessageListenerThread() {
        if (messageListenerThread != null) {
            messageListenerThread.interrupt();
        }

        messageListenerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String msg = null;
                    try {
                        msg = reader.readLine();
                    } catch (IOException e) {
                        /* Ignore but got msg=null as a result */
                    }

                    if (msg != null) {
                        JsonMessage message = jsonMapper.mapToMessage(msg);
                        MessageManager.INSTANCE.processMessage(message);
                    } else {
                        if (isAuthorized) {
                            for (ErrorObserver o : errorObservers) {
                                o.onError("Опаньки, сервер упал. Ждем переподключения...");
                            }
                            isAuthorized = false;

                        }

                        disconnect();

                        try {
                            connect(host, port, user.getName());
                            MessageManager.INSTANCE.processMessage(new InfoMessage("Сервер снова доступен"));
                        } catch (ConnectionManagerException e) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException i) {
                                break;
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Ошибка при чтении потока сокета " + e.getMessage());
                }
            }
        });
        messageListenerThread.start();
    }

    public void disconnect() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }
}
