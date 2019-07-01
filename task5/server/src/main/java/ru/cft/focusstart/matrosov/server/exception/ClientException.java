package ru.cft.focusstart.matrosov.server.exception;

public class ClientException extends Exception {
    public ClientException(String errorMessage) {
        super(errorMessage);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
