package ru.cft.focusstart.matrosov.common;

public class ErrorMessage extends JsonMessage {
    private String cause;

    public ErrorMessage(String cause) {
        this.cause = cause;
    }

    public ErrorMessage() {}

    public String getCause() {
        return cause;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "cause='" + cause + '\'' +
                '}';
    }
}
