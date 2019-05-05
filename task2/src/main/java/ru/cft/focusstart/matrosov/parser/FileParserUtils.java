package ru.cft.focusstart.matrosov.parser;

import ru.cft.focusstart.matrosov.exception.ParsingException;
import ru.cft.focusstart.matrosov.model.*;

class FileParserUtils {

    /**
     * Builds shape from existing shape getType and string that can be mutated into shape params array
     * @param type getType of the shape
     * @param paramString string of the getParameters
     * @return Geometric2DShape of null if constructing failed
     */
    static Geometric2DShape buildShape(GeometricShapeType type, String paramString) throws ParsingException {
        Double[] params = parseParametersString(paramString);
        if (type == null || params.length < type.getParamExpected())
            throw new ParsingException("Параметры фигуры некорректны");

        switch (type) {
            case SQUARE: return new Square(params[0]);
            case TRIANGLE: return new Triangle(params[0], params[1], params[2]);
            case CIRCLE: return new Circle(params[0]);
            case RECTANGLE: return new Rectangle(params[0], params[1]);
            default: throw new ParsingException("Фигура " + type + " не соответствует ни одному из имеющихся шаблонов");
        }
    }

    /**
     * Method tries to get an array of Double getParameters values from string
     *
     * @param paramString String to be parsed from application parameters
     * @return array of Double values or empty array if no one value was parsed
     */
    private static Double[] parseParametersString(String paramString) {
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
        return params;
    }
}
