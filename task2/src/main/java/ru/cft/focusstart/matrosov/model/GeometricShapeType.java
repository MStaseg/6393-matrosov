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
                return "Треугольник";
            case RECTANGLE:
                return "Треугольник";
            case SQUARE:
                return "Треугольник";
            default:
                return "Неизвестная фигура";
        }
    }
}
