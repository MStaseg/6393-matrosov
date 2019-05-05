package ru.cft.focusstart.matrosov;

import ru.cft.focusstart.matrosov.exception.*;
import ru.cft.focusstart.matrosov.io.*;
import ru.cft.focusstart.matrosov.model.*;
import ru.cft.focusstart.matrosov.parser.FileParser;

import javax.xml.bind.DataBindingException;

public class Application {
    public static void main(String[] args) {
        try {
            ApplicationParameters.getInstance().setInputParameters(args);
            Application.run();
        } catch (ApplicationParametersException e) {
            System.out.println("Некорректные параметры программы. " + e.getMessage());
        }
    }

    private static void run() {
        ApplicationParameters parameters = ApplicationParameters.getInstance();
        try {
            Geometric2DShape shape = FileParser.parseGeometric2DShape(parameters.getInputFilePath());
            DataOutputPrinter.print(shape);
        } catch (ParsingException e) {
            System.out.println("Ошибка при парсинге исходного файла. " + e.getMessage());
        } catch (ShapeFormatException e) {
            System.out.println("Ошибка при создании фигуры: " + e.getMessage());
        } catch (DataBindingException e) {
            System.out.println("Ошибка при выводе. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка: " + e.getMessage());
        } finally {
            System.out.println("Работа программы завершена");
        }
    }
}
