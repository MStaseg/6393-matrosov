package ru.cft.focusstart.matrosov;

import ru.cft.focusstart.matrosov.io.file.FileDataOutputPrinter;
import ru.cft.focusstart.matrosov.model.*;
import ru.cft.focusstart.matrosov.parser.FileParser;

import java.io.*;

public class Application {
    public static void main(String[] args) {
//        if (args.length < 2) {
//            System.out.println("Не указаны имена исходного и результирующего файла");
//        }
//        System.out.println("Hello task 2");
        String filePath = "C:\\Users\\Елена\\Desktop\\1.txt";
        String outputFilePath = "C:\\Users\\Елена\\Desktop\\2.txt";
        try {
            Geometric2DShape shape = FileParser.parseGeometric2DShape(filePath);
            FileDataOutputPrinter.print(shape, outputFilePath);
        } catch (IOException e) {
            System.out.println("Problem with writing to file");
        }

    }
}
