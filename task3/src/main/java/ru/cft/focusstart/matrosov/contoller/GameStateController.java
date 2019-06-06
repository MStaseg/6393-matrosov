package ru.cft.focusstart.matrosov.contoller;

import ru.cft.focusstart.matrosov.model.Game;
import ru.cft.focusstart.matrosov.model.GameManager;
import ru.cft.focusstart.matrosov.observer.MinesLeftObserver;
import ru.cft.focusstart.matrosov.observer.TimePastObserver;
import ru.cft.focusstart.matrosov.view.MainStateButton;
import ru.cft.focusstart.matrosov.view.StateLabel;

import javax.swing.*;
import java.awt.*;

public class GameStateController extends JPanel implements MinesLeftObserver, TimePastObserver {
    private final static Dimension TEXT_FIELD_SIZE = new Dimension(52, 30);

    private StateLabel minesCountLabel;
    private StateLabel timerLabel;

    private MainStateButton mainButton;

    GameStateController() {

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        minesCountLabel = new StateLabel();
        timerLabel = new StateLabel();
        mainButton = new MainStateButton();

        timerLabel.setText("000");
        timerLabel.setPreferredSize(TEXT_FIELD_SIZE);
        minesCountLabel.setPreferredSize(TEXT_FIELD_SIZE);
        timerLabel.setHorizontalAlignment(JTextField.RIGHT);
        minesCountLabel.setHorizontalAlignment(JTextField.LEFT);
        minesCountLabel.setText(String.format("%03d", GameManager.getInstance().getGame().getMinesCount()));

        setLayout(new BorderLayout());

        add(minesCountLabel, BorderLayout.LINE_START);

        JPanel mainButtonContainer = new JPanel();
        mainButtonContainer.setLayout(new GridBagLayout());
        mainButtonContainer.add(mainButton);

        add(mainButtonContainer, BorderLayout.CENTER);
        add(timerLabel, BorderLayout.LINE_END);

        mainButton.addActionListener(e -> this.startNewGame());

        setEnabled(false);

        Game game = GameManager.getInstance().getGame();
        game.addStatusObserver(mainButton);
        game.getGameField().addMinesLeftObserver(this);
        game.addTimeObserver(this);
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

    @Override
    public void timePastChanged(int seconds) {
        seconds = Math.min(999, seconds);
        timerLabel.setText(String.format("%03d", seconds));
    }
}
