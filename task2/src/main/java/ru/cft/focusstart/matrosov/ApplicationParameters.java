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

    /**
     * Use this method to set once the params got from the terminal while starting the program
     * @param args String[] from command line
     */
    public void setInputParameters(String[] args) {
        if (inputFilePath != null || outputFilePath != null)
            return;

        try {
            inputFilePath = args[0];
            outputFilePath = args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Return a file path to the input file
     * @return String
     */
    public String getInputFilePath() {
        return inputFilePath;
    }

    /**
     * Returns a file path to output file
     * @return String
     */
    public String getOutputFilePath() {
        return outputFilePath;
    }

    /**
     * Returns a dictionary with russian equivalents of output property names
     * @return Map<Key, Value>
     */
    public Map<String, String> dictionary() {
        Map<String, String> dictionary = new HashMap<>();

        // TODO: Move it to json file
        dictionary.put("radius", "Радиус");
        dictionary.put("diameter", "Диаметр");
        dictionary.put("width", "Ширина");
        dictionary.put("height", "Высота");
        dictionary.put("firstSize", "Первая сторона");
        dictionary.put("firstSizeFrontAngle", "Угол, противолежащий первой стороне");
        dictionary.put("secondSize", "Вторая сторона");
        dictionary.put("secondSizeFrontAngle", "Угол, противолежащий второй стороне");
        dictionary.put("thirdSize", "Третья сторона");
        dictionary.put("thirdSizeFrontAngle", "Угол, противолежащий третьей стороне");
        dictionary.put("size", "Размер");
        dictionary.put("diagonal", "Диагональ");

        return dictionary;
    }
}
