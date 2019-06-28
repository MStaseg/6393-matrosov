package ru.cft.focusstart.matrosov.server.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private ClientState state;
    private String name;
    private PrintWriter writer;
    private BufferedReader reader;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.state = ClientState.UNNAMED;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream());
    }

    public void setName(String name) {
        this.name = name;
        this.state = ClientState.ACTIVE;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }

    public ClientState getState() {
        return state;
    }

    public boolean hasMessage() throws IOException {
        return reader.ready();
    }

    public String getMessage() throws IOException {
        return reader.readLine();
    }

    public void sendMessage(String message) throws IOException {
        writer.println(message);
        writer.flush();
    }

    public void disconnect() throws IOException {
        socket.close();
    }
}
