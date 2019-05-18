package ru.cft.focusstart.matrosov.parser;

import ru.cft.focusstart.matrosov.model.*;
import ru.cft.focusstart.matrosov.exception.ParsingException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with static method that useful when you need to parse an information from existing file and
 * transform it info java objects
 */
public class FileParser {
    /**
     * Method tries to parse the existing file if exists and build Geometric2DShape based on the information
     * that was recognized while reading the file
     *
     * @param filePath path to the file that should be used in parsing
     * @return an instance of geometric shape
     * @throws ParsingException if some errors occurred on reading file or file format is incorrect e.t.c
     */
    public static Geometric2DShape parseGeometric2DShape(String filePath) throws ParsingException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            GeometricShapeType type = GeometricShapeType.valueOf(reader.readLine().toUpperCase());
            return ShapeFactory.buildShape(type, parseParametersString(reader.readLine()));
        } catch (IllegalArgumentException e) {
            throw new ParsingException("Первая строка не соответствует ни одной из возможных фигур", e);
        } catch (FileNotFoundException e) {
            throw new ParsingException("Указанный файл не существует", e);
        } catch (IOException e) {
            throw new ParsingException("Ошибка ввода " + e.getMessage(), e);
        }
    }

    /**
     * Method tries to get an array of Double getParameters values from string
     *
     * @param paramString String to be parsed from application parameters
     * @return array of Double values or empty array if no one value was parsed
     */
    private static List<Double> parseParametersString(String paramString) {
        String[] paramsSeparated = paramString.split(" ");
        List<Double> params = new ArrayList<>();

        for (String param : paramsSeparated) {
            try {
                params.add(Double.valueOf(param));
            } catch (NumberFormatException e) {
                break;
            }
        }

        return params;
    }
}
