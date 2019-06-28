package ru.cft.focusstart.matrosov.server.exception;

public class ServiceClientException extends Exception {
    public ServiceClientException(String errorMessage) {
        super(errorMessage);
    }

    public ServiceClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
