package ru.cft.focusstart.matrosov;

import ru.cft.focusstart.matrosov.io.*;
import ru.cft.focusstart.matrosov.model.*;
import ru.cft.focusstart.matrosov.parser.FileParser;

public class Application {
    public static void main(String[] args) {
        try {
            ApplicationParameters.getInstance().setInputParameters(args);
            Application.run();
        } catch (IllegalArgumentException e) {
            System.out.println("Параметры программы заданы в некорректном формате." +
                    " Нужно задать имя исходного файла");
        }
    }

    public static void run() {
        ApplicationParameters parameters = ApplicationParameters.getInstance();
        try {
            Geometric2DShape shape = FileParser.parseGeometric2DShape(parameters.getInputFilePath());
            DataOutputPrinter.print(shape, parameters.getOutputFilePath());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getLocalizedMessage());
        } finally {
            System.out.println("Работа программы завершена");
        }
    }
}
