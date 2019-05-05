package ru.cft.focusstart.matrosov.model;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class RectangleTest {

    @Test
    public void getArea() {
        Rectangle rect = new Rectangle(3.0, 4.0);
        assertEquals(12.0, rect.getArea(), 0.0);
    }

    @Test
    public void getPerimeter() {
        Rectangle rect = new Rectangle(3, 4);
        assertEquals(14.0, rect.getPerimeter(), 0.0);
    }

    @Test
    public void getParameters() {
        List<GeometricShapeProperty> expected = new ArrayList<>();

        expected.add(new GeometricShapeProperty("width", 4.0));
        expected.add(new GeometricShapeProperty("height", 3.0));
        expected.add(new GeometricShapeProperty("diagonal", 5.0));

        Rectangle rect = new Rectangle(3, 4);
        assertArrayEquals(expected.toArray(), rect.getParameters().toArray());
    }

    @Test
    public void getType() {
        Rectangle rect = new Rectangle(3, 4);
        assertEquals("RECTANGLE", rect.getType().name());
    }

    @Test
    public void equalsReflection() {
        Rectangle rect = new Rectangle(3, 4);
        assertEquals(rect, rect);
    }

    @Test
    public void equalsSymmetry() {
        Rectangle firstRect = new Rectangle(3, 4);
        Rectangle secondRect = new Rectangle(3, 4);

        boolean equalsFirst = firstRect.equals(secondRect);
        boolean equalsSecond = secondRect.equals(firstRect);

        assertEquals(equalsFirst, equalsSecond);
    }

    @Test
    public void equalsTransitivity() {
        Rectangle firstRect = new Rectangle(3, 4);
        Rectangle secondRect = new Rectangle(3, 4);
        Rectangle thirdRect = new Rectangle(3, 4);

        boolean equalsFirst = firstRect.equals(secondRect);
        boolean equalsSecond = secondRect.equals(thirdRect);
        boolean equalsThird = firstRect.equals(thirdRect);

        assertEquals(equalsFirst && equalsSecond, equalsThird);
    }
}