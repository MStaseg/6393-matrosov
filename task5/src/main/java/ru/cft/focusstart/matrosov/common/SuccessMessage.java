package ru.cft.focusstart.matrosov.common;

public class SuccessMessage extends JsonMessage  {
    private boolean success;

    public SuccessMessage(boolean success) {
        this.success = success;
    }

    public SuccessMessage() {}

    public boolean getSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "SuccessMessage{" +
                "success=" + success +
                '}';
    }
}
