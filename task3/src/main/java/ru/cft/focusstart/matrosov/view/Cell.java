package ru.cft.focusstart.matrosov.view;

import ru.cft.focusstart.matrosov.model.CellType;
import ru.cft.focusstart.matrosov.util.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Class represents single cell view in game field
 */
public class Cell extends JButton {

    private int size;
    private CellType type;

    /**
     * Creates the cell with the type given
     *
     * @param type of the cell
     * @param size of the cell greater than 0
     */
    public Cell(CellType type, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер ячейки должен быть больше нуля");
        }

        this.size = size;
        setType(type);
    }

    public CellType getType() {
        return type;
    }

    /**
     * Method changes the type of the cells. Also changes the icons of the cell.
     *
     * @param type of the cell
     */
    public void setType(CellType type) {
        this.type = type;

        ImageIcon icon = prepareIcon(type);
        setIcon(icon);
        setPreferredSize(new Dimension(size, size));
        setDisabledIcon(icon);

        icon = prepareIcon(CellType.EMPTY);
        setPressedIcon(icon);

        switch (type) {
            case CLOSED:
                setEnabled(true);
                break;
            default:
                setEnabled(false);
        }
    }

    /**
     * Emulates the cell pressing by changing the cell icon
     */
    public void emulatePressOn() {
        if (type != CellType.CLOSED) {
            return;
        }

        ImageIcon icon = prepareIcon(CellType.EMPTY);
        setIcon(icon);
    }

    /**
     * Switches off the cell pressing emulation
     */
    public void emulatePressOff() {
        if (type != CellType.CLOSED) {
            return;
        }

        ImageIcon icon = prepareIcon(CellType.CLOSED);
        setIcon(icon);
    }

    private ImageIcon prepareIcon(CellType type) {
        ImageIcon icon = ImageUtils.getImageIcon(type.getImagePath());
        return ImageUtils.resizeImageIcon(icon, size, size);
    }
}
