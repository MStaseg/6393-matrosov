package ru.cft.focusstart.matrosov;

import ru.cft.focusstart.matrosov.exception.ApplicationParametersException;

import java.util.*;

public class ApplicationParameters {

    private static final String PROPERTY_FILE_NAME = "/application_ru.properties";
    private static ApplicationParameters instance;

    private String inputFilePath;
    private String outputFilePath;
    private Map<String, String> dictionary;

    public static synchronized ApplicationParameters getInstance() {
        if (instance == null) {
            instance = new ApplicationParameters();
            instance.loadDictionaryFromFile();
        }
        return instance;
    }

    /**
     * Use this method to set the params got from the terminal while starting the program
     * @param args String[] from command line
     */
    void setInputParameters(String[] args) throws ApplicationParametersException {
        if (inputFilePath != null)
            return;

        if (args.length >= 1) {
            inputFilePath = args[0];
        } else {
            throw new ApplicationParametersException("Не задан путь до исходного файла");
        }

        if (args.length >= 2) {
            outputFilePath = args[1];
        }
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    /**
     * This method translates an english word to other language if it exists in the property file
     *
     * @param key String, that should be translated
     * @return translated word or string itself if not found
     */
    public String getDictionaryValueByKey(String key) {
        String value = dictionary.get(key);
        return value != null ? value : key;
    }

    private void loadDictionaryFromFile() {
        dictionary = new HashMap<>();

        Properties properties = new Properties();
        try {
            properties.load(ApplicationParameters.class.getResourceAsStream(PROPERTY_FILE_NAME));
        } catch (Exception e) {
            System.out.println("Не удалось загрузить словарь из файла " + PROPERTY_FILE_NAME);
        }

        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            dictionary.put(key, value);
        }
    }
}
