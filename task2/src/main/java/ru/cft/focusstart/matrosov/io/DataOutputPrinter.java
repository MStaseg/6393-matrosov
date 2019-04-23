package ru.cft.focusstart.matrosov.io;

import ru.cft.focusstart.matrosov.io.file.FileDataOutputPrinter;
import ru.cft.focusstart.matrosov.model.Geometric2DShape;

import java.io.IOException;

public class DataOutputPrinter {
    public static void print(Geometric2DShape shape, String fileName) {
        try {
            FileDataOutputPrinter.print(shape, fileName);
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getLocalizedMessage());
        }
    }
}
