package ru.cft.focusstart.matrosov.io.file;

import ru.cft.focusstart.matrosov.ApplicationParameters;
import ru.cft.focusstart.matrosov.io.DataOutputPrinter;
import ru.cft.focusstart.matrosov.model.*;

import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.*;

public abstract class FileDataOutputPrinter {
    /**
     * Prints the shape description into file
     *
     * @param shape Geometric2DShape
     * @throws IOException
     */
    public static void print(Geometric2DShape shape, String outputPath) throws IOException {
        if (shape == null || outputPath == null)
            return;

        File outputFile = new File(outputPath);
        outputFile.createNewFile();
        FileOutputStream outputFS = new FileOutputStream(outputFile, false);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputFS));

        String newLine = System.getProperty("line.separator");

        bw.write("Тип фигуры: " + shape.type().description());
        bw.write(newLine + newLine + "Периметр: " + shape.perimeter());
        bw.write(newLine + newLine + "Площадь: " + shape.area());

        List<GeometricShapeProperty> properties = shape.parameters();
        Map<String, String> dictionary = ApplicationParameters.getInstance().dictionary();
        Iterator<GeometricShapeProperty> iterator = properties.iterator();

        while (iterator.hasNext()) {
            GeometricShapeProperty property = iterator.next();
            String key = dictionary.get(property.getName());
            if (key != null)
                bw.write(newLine + newLine + key + ": " + property.getValue());
        }

        bw.flush();
        bw.close();
    }
}
