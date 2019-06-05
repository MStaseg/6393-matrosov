package ru.cft.focusstart.matrosov.view;

import ru.cft.focusstart.matrosov.model.CellType;
import ru.cft.focusstart.matrosov.util.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    private int size;
    private CellType type;

    public Cell(CellType type, int size) {
        this.size = size;
        setType(type);
    }

    public CellType getType() {
        return type;
    }

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

    public void emulatePressOn() {
        if (type != CellType.CLOSED) {
            return;
        }

        ImageIcon icon = prepareIcon(CellType.EMPTY);
        setIcon(icon);
    }

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
