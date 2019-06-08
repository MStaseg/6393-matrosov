package ru.cft.focusstart.matrosov.view;

import javax.swing.*;
import java.awt.*;

/**
 * Class represents the game state labels. Uses JTextField as basic class with disabling editing.
 */
public class StateLabel extends JTextField {
    private static final Color BACKGROUND_COLOR = new Color(31, 33,37);

    public StateLabel() {
        setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR));

        setFont(new Font("DialogInput", Font.BOLD, 25));
        setBackground(BACKGROUND_COLOR);

        setEnabled(false);
        setDisabledTextColor(Color.RED);
    }
}
