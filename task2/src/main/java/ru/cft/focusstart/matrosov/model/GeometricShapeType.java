package ru.cft.focusstart.matrosov.model;

/**
 * Enumeration for geometric figure types
 */
public enum GeometricShapeType {

    TRIANGLE("triangle", 3), CIRCLE("circle", 1), RECTANGLE("rectangle", 2), SQUARE("square", 1);

    private final String name;
    private final int paramExpected;

    GeometricShapeType(String name, int paramExpected) {
        this.name = name;
        this.paramExpected = paramExpected;
    }

    public String getName() {
        return name;
    }

    public int getParamExpected() {
        return paramExpected;
    }
}
