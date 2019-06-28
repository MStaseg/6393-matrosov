package ru.cft.focusstart.matrosov.server.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private String name;
    private PrintWriter writer;
    private BufferedReader reader;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream());
    }

    void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
