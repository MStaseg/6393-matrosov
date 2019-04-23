package ru.cft.focusstart.matrosov.model;

/**
 * Class describing a structure of single shape property element
 */
public class GeometricShapeProperty {

    private String name;
    private double value;

    public GeometricShapeProperty(String name, double value) {
        if (name == null)
            throw new IllegalArgumentException();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}
