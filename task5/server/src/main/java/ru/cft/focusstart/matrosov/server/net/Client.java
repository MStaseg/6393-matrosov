package ru.cft.focusstart.matrosov.server.net;

import ru.cft.focusstart.matrosov.common.ChatMessage;
import ru.cft.focusstart.matrosov.common.JsonMessage;
import ru.cft.focusstart.matrosov.entity.User;
import ru.cft.focusstart.matrosov.server.exception.ClientException;
import ru.cft.focusstart.matrosov.server.service.Services;
import ru.cft.focusstart.matrosov.util.JsonMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private final Socket socket;
    private User user;
    private final PrintWriter writer;
    private final BufferedReader reader;

    private final JsonMapper jsonMapper = JsonMapper.INSTANCE;

    Client(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public JsonMessage getMessage() throws ClientException {
        try {
            String message = reader.readLine();
            if (message == null) {
                return null;
            }
            return jsonMapper.mapToMessage(message);
        } catch (IOException e) {
            throw new ClientException("Ошибка при парсинге сообщения", e);
        }
    }

    public void sendMessage(JsonMessage message) throws ClientException {
        try {
            writer.println(jsonMapper.mapToString(message));
            writer.flush();
        } catch (IOException e) {
            throw new ClientException("Ошибка при отправке сообщения", e);
        }
    }

    public void disconnect() throws ClientException {
        try {
            socket.close();
        } catch (IOException e) {
            throw new ClientException("Ошибка при отсоединении клиента", e);
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "user=" + user +
                '}';
    }
}
