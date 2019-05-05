package ru.cft.focusstart.matrosov.model;

import java.util.*;

/**
 * Public interface for geometric shapes
 *
 */
public interface Geometric2DShape {

    /**
     * The area figure occupies in geometric terms
     * @return double area value
     */
    double getArea();

    /**
     * Sum of all figure sides
     * @return double perimeter value
     */
    double getPerimeter();

    /**
     * Method return the list of current shape's getParameters
     *
     * @return List of shape getParameters
     */
    List<GeometricShapeProperty> getParameters();

    GeometricShapeType getType();
}
