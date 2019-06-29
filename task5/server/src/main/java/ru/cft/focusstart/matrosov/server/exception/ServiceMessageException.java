package ru.cft.focusstart.matrosov.server.exception;

public class ServiceMessageException extends Exception {
    public ServiceMessageException(String errorMessage) {
        super(errorMessage);
    }

    public ServiceMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
