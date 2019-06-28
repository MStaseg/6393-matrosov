package ru.cft.focusstart.matrosov.common;

public class ClientMessage extends JsonMessage  {
    private String userName;

    public ClientMessage(String userName) {
        this.userName = userName;
    }

    public ClientMessage() {}

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "ClientMessage{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
