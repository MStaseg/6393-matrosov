package ru.cft.focusstart.matrosov.view;

import javax.swing.*;
import java.awt.*;

public class StateLabel extends JTextField {
    //private static int height = 26;
    private static Color backgroundColor = new Color(31, 33,37);

    public StateLabel() {
        setBorder(BorderFactory.createLineBorder(backgroundColor));

        setFont(new Font("DialogInput", Font.BOLD, 25));
        setBackground(backgroundColor);

        setEnabled(false);
        setDisabledTextColor(Color.RED);
    }
}
