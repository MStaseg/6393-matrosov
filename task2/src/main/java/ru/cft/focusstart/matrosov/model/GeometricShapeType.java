package ru.cft.focusstart.matrosov.model;

/**
 * Enumeration for geometric figure types
 */
public enum GeometricShapeType {

    TRIANGLE("triangle", 3), CIRCLE("circle", 1), RECTANGLE("rectangle", 2), SQUARE("square", 1);

    private final String description;
    private final int paramExpected;

    GeometricShapeType(String description, int paramExpected) {
        this.description = description;
        this.paramExpected = paramExpected;
    }

    public String getDescription() {
        return description;
    }

    public int getParamExpected() {
        return paramExpected;
    }
}
