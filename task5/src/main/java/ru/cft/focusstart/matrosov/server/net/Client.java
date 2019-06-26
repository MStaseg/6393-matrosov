package ru.cft.focusstart.matrosov.server.net;

import ru.cft.focusstart.matrosov.common.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private String name;
    private PrintWriter writer;
    private BufferedReader reader;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }

    public String nextMessage() throws IOException {
        if (reader.ready()) {
            return reader.readLine();
        }

        return null;
    }


}
