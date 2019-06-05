package ru.cft.focusstart.matrosov.exception;

public class IllegalGameParametersException extends RuntimeException {
    public IllegalGameParametersException(String errorMessage) {
        super(errorMessage);
    }
}
