package ru.cft.focusstart.matrosov.io;

import ru.cft.focusstart.matrosov.io.console.ConsoleDataOutputPrinter;
import ru.cft.focusstart.matrosov.io.file.FileDataOutputPrinter;
import ru.cft.focusstart.matrosov.model.Geometric2DShape;

import java.io.IOException;

public class DataOutputPrinter {
    /**
     * Pass the geometric shape to the output
     *
     * @param shape Geometric2DShape
     * @param fileName name of the file
     */
    public static void print(Geometric2DShape shape, String fileName) {
        if (fileName == null) {
            print(shape);
            return;
        }

        try {
            FileDataOutputPrinter.print(shape, fileName);
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getLocalizedMessage());
        }
    }

    /**
     * Pass the geometric shape to the console
     *
     * @param shape Geometric2DShape
     */
    public static void print(Geometric2DShape shape) {
        try {
            ConsoleDataOutputPrinter.print(shape);
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getLocalizedMessage());
        }
    }
}
