package ru.cft.focusstart.matrosov.model;

/**
 * Class describing a structure of single shape property element
 */
public class GeometricShapeProperty {

    private GeometricShapeParameter parameter;
    private double value;

    GeometricShapeProperty(GeometricShapeParameter parameter, double value) {
        if (parameter == null)
            throw new IllegalArgumentException();
        this.parameter = parameter;
        this.value = value;
    }

    public GeometricShapeParameter getParameter() {
        return parameter;
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
        return this.value == property.value && this.parameter.equals(property.parameter);
    }

    @Override
    public String toString() {
        return "GeometricShapeProperty[parameter = " + parameter + ", value = " + value + "]";
    }
}
