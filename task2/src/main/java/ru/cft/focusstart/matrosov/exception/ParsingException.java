package ru.cft.focusstart.matrosov.exception;

public class ParsingException extends Exception {

    public ParsingException(String errorMessage) {
        super(errorMessage);
    }

    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }

}
