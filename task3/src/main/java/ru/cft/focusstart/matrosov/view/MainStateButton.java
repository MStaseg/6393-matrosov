package ru.cft.focusstart.matrosov.view;

import ru.cft.focusstart.matrosov.model.GameStatus;
import ru.cft.focusstart.matrosov.observer.GameStatusObserver;
import ru.cft.focusstart.matrosov.util.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class MainStateButton extends JButton implements GameStatusObserver {
    private static int size = 26;

    public MainStateButton() {
        setStatus(GameStatus.PLAYING);
        setPreferredSize(new Dimension(size,size));
    }

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
        System.out.println(icon.getImage());
        //icon = ImageUtils.resizeImageIcon(icon, size, size);
        setIcon(icon);
    }

    @Override
    public void onStatusChanged(GameStatus status) {
        setStatus(status);
    }
}
