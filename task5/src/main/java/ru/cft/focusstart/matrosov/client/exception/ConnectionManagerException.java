package ru.cft.focusstart.matrosov.client.exception;

public class ConnectionManagerException extends Exception {

    public ConnectionManagerException(String errorMessage) {
        super(errorMessage);
    }

    public ConnectionManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
