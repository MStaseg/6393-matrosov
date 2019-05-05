package ru.cft.focusstart.matrosov.model;

/**
 * Class describing a structure of single shape property element
 */
public class GeometricShapeProperty {

    private String name;
    private double value;

    GeometricShapeProperty(String name, double value) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GeometricShapeProperty))
            return false;

        GeometricShapeProperty property = (GeometricShapeProperty)obj;
        return this.value == property.value && this.name.equals(property.name);
    }

    @Override
    public String toString() {
        return "GeometricShapeProperty[name = " + name + ", value = " + value + "]";
    }
}
