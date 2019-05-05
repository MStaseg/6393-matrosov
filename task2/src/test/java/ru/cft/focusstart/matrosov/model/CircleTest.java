package ru.cft.focusstart.matrosov.model;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

public class CircleTest {

    @Test
    public void getArea() {
        Circle circle = new Circle(2.0);
        assertEquals(4.0 * Math.PI, circle.getArea(), 0.0);
    }

    @Test
    public void getPerimeter() {
        Circle circle = new Circle(2.0);
        assertEquals(4.0 * Math.PI, circle.getArea(), 0.0);
    }

    @Test
    public void getType() {
        Circle circle = new Circle(1.0);
        assertEquals("CIRCLE", circle.getType().name());
    }

    @Test
    public void getParameters() {
        List<GeometricShapeProperty> expected = new ArrayList<>();
        expected.add(new GeometricShapeProperty("radius", 2.0));
        expected.add(new GeometricShapeProperty("diameter", 4.0));

        Circle circle = new Circle(2.0);
        assertArrayEquals(expected.toArray(), circle.getParameters().toArray());
    }

    @Test
    public void equalsReflection() {
        Circle circle = new Circle(5.0);
        assertEquals(circle, circle);
    }

    @Test
    public void equalsSymmetry() {
        Circle firstCircle = new Circle(5.0);
        Circle secondCircle = new Circle(5.0);

        boolean equalsFirst = firstCircle.equals(secondCircle);
        boolean equalsSecond = secondCircle.equals(firstCircle);

        assertEquals(equalsFirst, equalsSecond);
    }

    @Test
    public void equalsTransitivity() {
        Circle firstCircle = new Circle(5.0);
        Circle secondCircle = new Circle(5.0);
        Circle thirdCircle = new Circle(5.0);

        boolean equalsFirst = firstCircle.equals(secondCircle);
        boolean equalsSecond = secondCircle.equals(thirdCircle);
        boolean equalsThird = firstCircle.equals(thirdCircle);

        assertEquals(equalsFirst && equalsSecond, equalsThird);
    }
}
