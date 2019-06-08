package ru.cft.focusstart.matrosov.util;

import javax.swing.*;
import java.awt.*;

/**
 * Static utilities for work with java's image class
 */
public class ImageUtils {
    private static final String IMAGE_DIRECTORY = "/images/";
    /**
     * Method tries to find the image in local images directory
     *
     * @param name of the image without file extension
     * @return created image icon
     */
    public static ImageIcon getImageIcon(String name) {
        String fileName = IMAGE_DIRECTORY + name + ".png";
        return new ImageIcon(ImageUtils.class.getResource(fileName));
    }

    /**
     * Method resize an ImageIcon to the size given
     *
     * @param icon an instance of ImageIcon
     * @param width that should be the resulting image
     * @param height that should be the resulting image
     * @return an new instance of ImageIcon with size given
     */
    public static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image newImage = icon.getImage().getScaledInstance(width, height,  Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon(newImage);
        return icon;
    }
}
