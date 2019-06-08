package ru.cft.focusstart.matrosov.contoller;

import ru.cft.focusstart.matrosov.model.Game;
import ru.cft.focusstart.matrosov.model.GameDifficulty;
import ru.cft.focusstart.matrosov.model.GameManager;
import ru.cft.focusstart.matrosov.observer.MinesLeftObserver;
import ru.cft.focusstart.matrosov.observer.TimePastObserver;
import ru.cft.focusstart.matrosov.view.MainStateButton;
import ru.cft.focusstart.matrosov.view.StateLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Controller represents top state panel for the game
 */
public class GameStateController extends JPanel implements MinesLeftObserver, TimePastObserver {
    private final static Dimension TEXT_FIELD_SIZE = new Dimension(52, 30);

    private StateLabel minesCountLabel;
    private StateLabel timerLabel;

    /**
     * Creates an instance of state controller. Adds mines count and time past labels. Adds main state button.
     */
    GameStateController() {
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setEnabled(false);

        minesCountLabel = new StateLabel();
        timerLabel = new StateLabel();
        MainStateButton mainButton = new MainStateButton();

        mainButton.addActionListener(e -> this.startNewGame());

        timerLabel.setText("000");
        timerLabel.setPreferredSize(TEXT_FIELD_SIZE);
        timerLabel.setHorizontalAlignment(JTextField.RIGHT);

        minesCountLabel.setPreferredSize(TEXT_FIELD_SIZE);
        minesCountLabel.setHorizontalAlignment(JTextField.LEFT);
        minesCountLabel.setText(String.format("%03d", GameManager.getInstance().getGame().getMinesCount()));

        setLayout(new BorderLayout());

        add(minesCountLabel, BorderLayout.LINE_START);

        JPanel mainButtonContainer = new JPanel();
        mainButtonContainer.setLayout(new GridBagLayout());
        mainButtonContainer.add(mainButton);
        add(mainButtonContainer, BorderLayout.CENTER);

        add(timerLabel, BorderLayout.LINE_END);

        Game game = GameManager.getInstance().getGame();
        game.addStatusObserver(mainButton);
        game.getGameField().addMinesLeftObserver(this);
        game.addTimeObserver(this);
    }

    private void startNewGame() {
        Game game = GameManager.getInstance().getGame();
        GameDifficulty difficulty = game.getDifficulty();
        if (difficulty == null) {
            GameManager.getInstance().startNewGame(game.getWidth(), game.getHeight(), game.getMinesCount());
        } else {
            GameManager.getInstance().startNewGame(difficulty);
        }
    }

    @Override
    public void minesLeftChanged(int left) {
        left = Math.max(-99, left);
        left = Math.min(999, left);
        minesCountLabel.setText(String.format("%03d", left));
    }

    @Override
    public void timePastChanged(int seconds) {
        seconds = Math.min(999, seconds);
        timerLabel.setText(String.format("%03d", seconds));
    }
}
