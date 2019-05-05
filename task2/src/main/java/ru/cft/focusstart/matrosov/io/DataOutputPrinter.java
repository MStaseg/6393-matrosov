package ru.cft.focusstart.matrosov.io;

import ru.cft.focusstart.matrosov.ApplicationParameters;
import ru.cft.focusstart.matrosov.exception.DataOutputException;
import ru.cft.focusstart.matrosov.model.Geometric2DShape;
import ru.cft.focusstart.matrosov.model.GeometricShapeProperty;

import java.io.*;
import java.util.List;

public final class DataOutputPrinter {

    private DataOutputPrinter() {}

    /**
     * Method takes the instance of geometric shape and prints in out to the output that was set in
     * application parameters
     *
     * @param shape Geometric2DShape that will be printed
     */
    public static void print(Geometric2DShape shape) throws DataOutputException {
        String outputPath = ApplicationParameters.getInstance().getOutputFilePath();
        if (outputPath == null) {
            DataOutputPrinter.print(shape, System.out);
        } else {
            DataOutputPrinter.print(shape, outputPath);
        }
    }

    /**
     * Method prints out the geometric shape params to the file with path. If file is not exists creates one.
     *
     * @param shape an instance of geometric shape to be printed
     * @param outputPath to the result file
     * @throws DataOutputException if got trouble with getting access to the file or other IO exceptions
     */
    private static void print(Geometric2DShape shape, String outputPath) throws DataOutputException {
        File outputFile = new File(outputPath);
        try {
            outputFile.createNewFile();
        } catch (SecurityException e) {
            throw new DataOutputException("Невозможно создать файл из за настроек безопасности", e);
        } catch (IOException e) {
            throw new DataOutputException("Ошибка потока вывода", e);
        }

        try(OutputStream fileOS = new FileOutputStream(outputFile, false)) {
            PrintStream printStream = new PrintStream(fileOS);
            DataOutputPrinter.print(shape, printStream);
        } catch (FileNotFoundException e) {
            throw new DataOutputException("Файл " + outputPath + " не найден", e);
        } catch (IOException e) {
            throw new DataOutputException("Ошибка потока вывода", e);
        }
    }

    /**
     * Method uses an instance of PrintStream to use it println method and pass the existing shape to the output
     *
     * @param shape to be printed
     * @param printer any instance of PrintStream
     */
    private static void print(Geometric2DShape shape, PrintStream printer) {
        ApplicationParameters parameters = ApplicationParameters.getInstance();

        String shapeDescription = shape.getType().getDescription();
        printer.println(parameters.getDictionaryValueByKey("shapeType") +
                ": " + parameters.getDictionaryValueByKey(shapeDescription));
        printer.println(parameters.getDictionaryValueByKey("perimeter") + ": " + shape.getPerimeter());
        printer.println(parameters.getDictionaryValueByKey("area") + ": " + shape.getArea());

        List<GeometricShapeProperty> properties = shape.getParameters();
        for (GeometricShapeProperty property: properties) {
            String key = parameters.getDictionaryValueByKey(property.getName());
            printer.println(key + ": " + property.getValue());
        }
    }
}
