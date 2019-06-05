package ru.cft.focusstart.matrosov.util;

import javax.swing.*;
import java.awt.*;

public class ImageUtils {
    public static ImageIcon getImageIcon(String name) {
        String fileName = "/images/" + name + ".png";
        ImageIcon icon = new ImageIcon(ImageUtils.class.getResource(fileName));
        return icon;
    }

    public static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image newImage = icon.getImage().getScaledInstance(width, height,  Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon(newImage);
        return icon;
    }
}
