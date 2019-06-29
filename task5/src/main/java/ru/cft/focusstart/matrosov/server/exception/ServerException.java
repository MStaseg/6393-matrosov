package ru.cft.focusstart.matrosov.server.exception;

public class ServerException extends Exception {
    public ServerException(String errorMessage) {
        super(errorMessage);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
