package ru.cft.focusstart.matrosov;

import java.util.*;

public class ApplicationParameters {
    private String inputFilePath;
    private String outputFilePath;

    private static ApplicationParameters instance;

    public static synchronized ApplicationParameters getInstance() {
        if (instance == null) {
            instance = new ApplicationParameters();
        }
        return instance;
    }

    public void setInputParameters(String[] args) {

    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public Map<String, String> dictionary() {
        Map<String, String> dictionary = new HashMap<>();
        // TODO: Move it to json file
        dictionary.put("radius", "Радиус");
        dictionary.put("diameter", "Диаметр");
        dictionary.put("width", "Ширина");
        dictionary.put("height", "Высота");
        dictionary.put("firstSide", "Первая сторона");
        dictionary.put("secondSide", "Вторая сторона");
        dictionary.put("thirdSide", "Третья сторона");
        dictionary.put("size", "Разамер");

        return dictionary;
    }
}
