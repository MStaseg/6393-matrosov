package ru.cft.focusstart.matrosov;

public class ApplicationParameters {

    private String inputFilePath;
    private String outputFilePath;

    private static ApplicationParameters instance;

    static synchronized ApplicationParameters getInstance() {
        if (instance == null) {
            instance = new ApplicationParameters();
        }
        return instance;
    }


}
