package ru.cft.focusstart.matrosov.model;

import java.util.Map;

/**
 * Public interface for geometric fugures
 *
 */
public interface Geometric2DShape {

    /**
     * The area figure occupies in geometric terms
     * @return double
     */
    public double area();

    /**
     * Sum of all figure sides
     * @return double
     */
    public double perimeter();

    /**
     * Method return the list of current shape's parameters
     *
     * @return Map<String, Double> key-value map of shape parameters
     */
    public Map<String, Double> parameters();


    /**
     * Return the enum tupe od current shape
     *
     * @return GeometricShapeType
     */
    GeometricShapeType type();
}
