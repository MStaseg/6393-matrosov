package ru.cft.focusstart.matrosov.client.model;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.cft.focusstart.matrosov.client.exception.ConnectionManagerException;
import ru.cft.focusstart.matrosov.client.observer.ErrorObserver;
import ru.cft.focusstart.matrosov.client.util.JsonParser;
import ru.cft.focusstart.matrosov.common.ClientMessage;
import ru.cft.focusstart.matrosov.common.InfoMessage;
import ru.cft.focusstart.matrosov.common.JsonMessage;
import ru.cft.focusstart.matrosov.common.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConnectionManager {

    private static ConnectionManager instance;

    private static Socket socket;

    private boolean isAuthorized;

    private String host;

    private int port;

    private String userName;

    private PrintWriter writer;
    private BufferedReader reader;

    private List<ErrorObserver> errorObservers;

    private ConnectionManager() {
        errorObservers = new ArrayList<>();
    }

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }

        return instance;
    }

    public String getUserName() {
        return userName;
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

    public boolean checkAuthorized() {
        return isAuthorized;
    }

    public void connect(String host, int port, String name) throws ConnectionManagerException {

        this.host = host;
        this.port = port;
        this.userName = name;

        try {
            socket = new Socket(host, port);

            isAuthorized = true;

            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            ClientMessage authMessage = new ClientMessage(name);

            JsonFactory jsonFactory = new JsonFactory();
            jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
            ObjectMapper mapper = new ObjectMapper(jsonFactory);

            writer.println(mapper.writeValueAsString(authMessage));
            writer.flush();

            addMessageListenerThread();
        } catch (IOException e) {
            socket = null;
            throw new ConnectionManagerException("Ошибка при подключении к серверу: " + e.getMessage());
        }
    }

    void sendMessage(JsonMessage message) {

        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        ObjectMapper mapper = new ObjectMapper(jsonFactory);

        try {
            writer.println(mapper.writeValueAsString(message));
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
                    JsonFactory jsonFactory = new JsonFactory();
                    jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
                    ObjectMapper mapper = new ObjectMapper(jsonFactory);

                    String msg = reader.readLine();
                    if (msg != null) {
                        JsonMessage message = mapper.readValue(msg, JsonMessage.class);
                        MessageManager.getInstance().processMessage(message);
                    } else {
                        if (isAuthorized) {
                            for (ErrorObserver o : errorObservers) {
                                o.onError("Опаньки, сервер упал. Ждем переподключения...");
                            }
                            isAuthorized = false;
                        }

                        try {
                            connect(host, port, userName);
                            MessageManager.getInstance().processMessage(new InfoMessage("Сервер снова доступен"));
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
