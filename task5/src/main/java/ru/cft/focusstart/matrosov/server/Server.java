package ru.cft.focusstart.matrosov.server;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.cft.focusstart.matrosov.common.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

class Server {

    private static Server instance;

    private List<Socket> clients = new ArrayList<>();
    private List<BufferedReader> readers = new ArrayList<>();
    private List<PrintWriter> writers = new ArrayList<>();

    private ServerSocket serverSocket;

    private Thread messageListenerThread;

    private ObjectMapper mapper;


    private Server() {}

    static synchronized Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }

        return instance;
    }

    void start() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        mapper = new ObjectMapper(jsonFactory);

        Properties properties = new Properties();
        try (InputStream propertiesStream = Application.class.getClassLoader().getResourceAsStream("server.properties")) {
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
                    Message message = null;
                    for (BufferedReader reader : readers) {
                        if (reader.ready()) {
                            String msgString = reader.readLine();
                            System.out.println("YEs");
                            message = mapper.readValue(msgString, Message.class);
                        }
                    }

                    if (message != null) {
                        System.out.println("new message received: " + message.getText());
                        for (PrintWriter writer : writers) {
                            writer.println(message.getText());
                            writer.flush();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("messageListenerError");
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("messageListenerInterrupted");
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
                for (Socket socket : clients) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    private void startSocketObserver() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            clients.add(clientSocket);
            readers.add(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
            writers.add(new PrintWriter(clientSocket.getOutputStream()));
        }
    }
}
