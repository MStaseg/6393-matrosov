package ru.cft.focusstart.matrosov.model;

/**
 * Enumeration for geometric figure types
 */
public enum GeometricShapeType {
    TRIANGLE, CIRCLE, RECTANGLE, SQUARE;

    /**
     * Return russian description of each figure type
     *
     * @return String
     */
    public String description() {
        switch(this) {
            case TRIANGLE:
                return "Треугольник";
            case CIRCLE:
                return "Круг";
            case RECTANGLE:
                return "Прямоугольник";
            case SQUARE:
                return "Квадрат";
            default:
                return "Неизвестная фигура";
        }
    }

    /**
     * Returns the shape type from string
     *
     * @param string from which is needed to get shape type
     * @return GeometricShapeType
     */
    public static GeometricShapeType value(String string) {
        if (string == null)
            return null;
        String stringLowerCase = string.toLowerCase();
        switch (stringLowerCase) {
            case "triangle": return GeometricShapeType.TRIANGLE;
            case "circle": return GeometricShapeType.CIRCLE;
            case "rectangle": return GeometricShapeType.RECTANGLE;
            case "square": return GeometricShapeType.SQUARE;
            default: return null;
        }
    }
}
