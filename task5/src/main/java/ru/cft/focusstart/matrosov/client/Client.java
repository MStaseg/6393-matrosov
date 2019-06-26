package ru.cft.focusstart.matrosov.client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.cft.focusstart.matrosov.client.exception.ConnectionManagerException;
import ru.cft.focusstart.matrosov.common.Message;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws ConnectionManagerException, IOException {
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        ObjectMapper mapper = new ObjectMapper(jsonFactory);

        Scanner userInputReader = new Scanner(System.in);
        String userName = userInputReader.nextLine();

        //ConnectionManager.getInstance().connect("localhost", 1111);

        Socket socket = new Socket("localhost", 1111);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Thread messageListenerThread = new Thread(() -> {
            boolean interrupted = false;
            while (!interrupted) {
                try {
                    if (reader.ready()) {
                        System.out.println(reader.readLine());
                    }
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

        new Thread(() -> {
            try {
                while (true) {
                    String userInput = userInputReader.nextLine();
                    if ("\\q".equals(userInput)) {
                        messageListenerThread.interrupt();
                        socket.close();
                        break;
                    }
                    Message message = new Message(userInput, new Date(), "Пупкин");
                    mapper.writeValue(writer, message);
                    writer.println(message);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
