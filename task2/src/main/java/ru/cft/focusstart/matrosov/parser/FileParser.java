package ru.cft.focusstart.matrosov.parser;

import ru.cft.focusstart.matrosov.model.Geometric2DShape;
import ru.cft.focusstart.matrosov.model.GeometricShapeType;
import ru.cft.focusstart.matrosov.exception.ParsingException;

import java.io.*;

public class FileParser {
    public static Geometric2DShape parseGeometric2DShape(String filePath) throws ParsingException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            GeometricShapeType type = GeometricShapeType.valueOf(reader.readLine().toUpperCase());
            return FileParserUtils.buildShape(type, reader.readLine());
        } catch (IllegalArgumentException e) {
            throw new ParsingException("Первая строка не соответствует ни одной из возможных фигур", e);
        } catch (FileNotFoundException e) {
            throw new ParsingException("Указанный файл не существует", e);
        } catch (IOException e) {
            throw new ParsingException("Ошибка ввода " + e.getMessage(), e);
        }
    }
}
