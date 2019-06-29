package ru.cft.focusstart.matrosov.server.net;

import ru.cft.focusstart.matrosov.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private User user;
    private PrintWriter writer;
    private BufferedReader reader;

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

    public String getMessage() throws IOException {
        return reader.readLine();
    }

    public void sendMessage(String message) {
        writer.println(message);
        writer.flush();
    }

    public void disconnect() throws IOException {
        socket.close();
    }
}
