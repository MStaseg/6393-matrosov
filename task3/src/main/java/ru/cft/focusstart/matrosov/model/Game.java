package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.IllegalGameParametersException;
import ru.cft.focusstart.matrosov.observer.GameCellsObserver;
import ru.cft.focusstart.matrosov.observer.GameStatusObserver;
import ru.cft.focusstart.matrosov.util.Coordinates;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class representing an instance of Mine Sweeper game
 *
 */
public class Game {

    private long start;

    private GameStatus status;

    private GameField gameField;

    private Set<GameStatusObserver> statusObservers;

    private Game() {
        this.start = System.currentTimeMillis();
        this.status = GameStatus.PLAYING;
        statusObservers = new HashSet<>();
    }

    Game(int width, int height, int minesCount) throws IllegalGameParametersException {
        this();
        gameField = new GameField(width, height, minesCount);
    }

    public GameField getGameField() {
        return gameField;
    }

    public int getWidth() {
        return gameField.getWidth();
    }

    public int getHeight() {
        return gameField.getHeight();
    }

    public int getMinesCount() {
        return  gameField.getMinesCount();
    }

    public void openCell(Coordinates c) {
        if (status != GameStatus.PLAYING) {
            return;
        }

        this.status = gameField.openCell(c);
        refreshStatus();
    }

    public void setFlag(Coordinates c) {
        if (status != GameStatus.PLAYING) {
            return;
        }

        gameField.setFlag(c);
    }

    public void forceCheck(Coordinates c) {
        if (status != GameStatus.PLAYING) {
            return;
        }

        this.status = gameField.forceCheck(c);
        refreshStatus();
    }

    private void refreshStatus() {
        if (status == GameStatus.FAILED) {
            gameField.prepareLooseField();
        } else if (status == GameStatus.WON) {
            gameField.prepareVictoryField();
        }

        statusObservers.forEach(observer -> observer.onStatusChanged(status));
    }

    public void addStatusObserver(GameStatusObserver observer) {
        statusObservers.add(observer);
    }

    public void removeStatusObserver(GameStatusObserver observer) {
        statusObservers.remove(observer);
    }

    @Override
    public String toString() {
        return "Game{" +
                "start=" + start +
                ", status=" + status +
                '}';
    }
}
