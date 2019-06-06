package ru.cft.focusstart.matrosov.view;

import javax.swing.*;
import java.awt.*;

public class StateLabel extends JTextField {
    private static int height = 26;

    public StateLabel() {
        setSize(new Dimension(height * 2, height));

        setFont(new Font("DialogInput", Font.BOLD, 26));
        setBackground(new Color(31, 33,37));

        setEnabled(false);
        setDisabledTextColor(Color.RED);
    }

}
