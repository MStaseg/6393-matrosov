package ru.cft.focusstart.matrosov.parser;

import ru.cft.focusstart.matrosov.model.*;
import java.io.*;

public class FileParser {
    public static Geometric2DShape parseGeometric2DShape(String filePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        try {
            GeometricShapeType type = FileParserUtils.geometricShapeType(reader.readLine());
            return FileParserUtils.buildShape(type, reader.readLine());
        } catch (IOException e) {
            System.out.println("При работе с исходным файлом возникла ошибка: " + e.getLocalizedMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

/**
 * Utility static method class for file parsing
 */
class FileParserUtils {
    /**
     * Get the shape type from string description
     *
     * @param type existing string
     * @return GeometricShapeType
     */
    static GeometricShapeType geometricShapeType(String type) {
        return GeometricShapeType.value(type);
    }

    /**
     * Builds shape from existing shape type and string that can be mutated into shape params array
     * @param type type of the shape
     * @param paramString string of the parameters
     * @return Geometric2DShape of null if constructing failed
     */
    static Geometric2DShape buildShape(GeometricShapeType type, String paramString) {
        String[] paramsSeparated = paramString.split(" ");
        Double[] params = new Double[paramsSeparated.length];
        int paramCount = 0;

        for (int i =0; i < paramsSeparated.length; i++) {
            try {
                params[i] = Double.valueOf(paramsSeparated[i]);
                paramCount ++;
            } catch (NumberFormatException e) {
                break;
            }
        }

        System.arraycopy(params, 0, params, 0, paramCount);
        if (paramCount < checkParamCount(type))
            return null;

        switch (type) {
            case SQUARE: return new Square(params[0]);
            case TRIANGLE: return new Triangle(params[0], params[1], params[2]);
            case CIRCLE: return new Circle(params[0]);
            case RECTANGLE: return new Rectangle(params[0], params[1]);
            default: return null;
        }
    }

    static int checkParamCount(GeometricShapeType type) {
        switch (type) {
            case SQUARE: return 1;
            case TRIANGLE: return 3;
            case CIRCLE: return 1;
            case RECTANGLE: return 2;
            default: return 0;
        }
    }
}