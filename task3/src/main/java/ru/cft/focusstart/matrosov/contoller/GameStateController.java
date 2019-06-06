package ru.cft.focusstart.matrosov.contoller;

import ru.cft.focusstart.matrosov.model.Game;
import ru.cft.focusstart.matrosov.model.GameManager;
import ru.cft.focusstart.matrosov.observer.MinesLeftObserver;
import ru.cft.focusstart.matrosov.view.MainStateButton;
import ru.cft.focusstart.matrosov.view.StateLabel;

import javax.swing.*;
import java.awt.*;

public class GameStateController extends JPanel implements MinesLeftObserver {
    private StateLabel minesCountLabel;
    private StateLabel timerLabel;

    private MainStateButton mainButton;

    GameStateController() {

        minesCountLabel = new StateLabel();
        timerLabel = new StateLabel();
        mainButton = new MainStateButton();

        timerLabel.setText("000");
        minesCountLabel.setText(String.format("%03d", GameManager.getInstance().getGame().getMinesCount()));

        setLayout(new BorderLayout());

        add(minesCountLabel, BorderLayout.LINE_START);
        add(mainButton, BorderLayout.CENTER);
        add(timerLabel, BorderLayout.LINE_END);

        mainButton.addActionListener(e -> this.startNewGame());

        setEnabled(false);

        Game game = GameManager.getInstance().getGame();
        game.addStatusObserver(mainButton);
        game.getGameField().addMinesLeftObserver(this);
    }

    private void startNewGame() {
        Game game = GameManager.getInstance().getGame();
        GameManager.getInstance().startNewGame(game.getWidth(), game.getHeight(), game.getMinesCount());
    }

    @Override
    public void minesLeftChanged(int left) {
        left = Math.max(-99, left);
        left = Math.min(999, left);
        minesCountLabel.setText(String.format("%03d", left));
    }
}
