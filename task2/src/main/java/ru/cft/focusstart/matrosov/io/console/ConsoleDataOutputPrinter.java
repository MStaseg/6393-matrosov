package ru.cft.focusstart.matrosov.io.console;

import ru.cft.focusstart.matrosov.ApplicationParameters;
import ru.cft.focusstart.matrosov.model.Geometric2DShape;
import ru.cft.focusstart.matrosov.model.GeometricShapeProperty;

import java.io.*;
import java.util.*;

public class ConsoleDataOutputPrinter {
    /**
     * Prints the shape description into console
     *
     * @param shape Geometric2DShape
     * @throws IOException
     */
    public static void print(Geometric2DShape shape) throws IOException {
        if (shape == null)
            return;

        System.out.println("Тип фигуры: " + shape.type().description());
        System.out.println("Периметр: " + shape.perimeter());
        System.out.println("Площадь: " + shape.area());

        List<GeometricShapeProperty> properties = shape.parameters();
        Map<String, String> dictionary = ApplicationParameters.getInstance().dictionary();
        Iterator<GeometricShapeProperty> iterator = properties.iterator();

        while (iterator.hasNext()) {
            GeometricShapeProperty property = iterator.next();
            String key = dictionary.get(property.getName());
            if (key != null) {
                 System.out.println(key + ": " + property.getValue());
            }
        }
    }
}
