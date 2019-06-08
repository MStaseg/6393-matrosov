package ru.cft.focusstart.matrosov.view;

import ru.cft.focusstart.matrosov.model.GameStatus;
import ru.cft.focusstart.matrosov.observer.GameStatusObserver;
import ru.cft.focusstart.matrosov.util.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Class represents the main game state button view
 */
public class MainStateButton extends JButton implements GameStatusObserver {
    private static final int SIZE = 26;

    /**
     * Creates an instance of the button. Sets the default state to PLAYING
     */
    public MainStateButton() {
        setStatus(GameStatus.PLAYING);
        setPreferredSize(new Dimension(SIZE,SIZE));
    }

    /**
     * Changes button's icon according the new game state
     *
     * @param status new status of the game
     */
    private void setStatus(GameStatus status) {
        String iconPath = "main_playing";
        switch (status){
            case WON:
                iconPath = "main_victory";
                break;
            case FAILED:
                iconPath = "main_loose";
                break;
            default:
                break;
        }

        ImageIcon icon = ImageUtils.getImageIcon(iconPath);
        setIcon(icon);
    }

    @Override
    public void onStatusChanged(GameStatus status) {
        setStatus(status);
    }
}
