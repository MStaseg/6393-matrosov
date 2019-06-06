package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.IllegalGameParametersException;
import ru.cft.focusstart.matrosov.observer.GameStatusObserver;
import ru.cft.focusstart.matrosov.observer.TimePastObserver;
import ru.cft.focusstart.matrosov.util.Coordinates;

import java.util.HashSet;
import java.util.Set;
import javax.swing.Timer;

/**
 * Class representing an instance of Mine Sweeper game
 *
 */
public class Game {

    private long start;

    private GameStatus status;

    private GameField gameField;

    private Set<GameStatusObserver> statusObservers;
    private Set<TimePastObserver> timeObservers;

    private Timer gameTimer;

    Game(int width, int height, int minesCount) throws IllegalGameParametersException {
        this.status = GameStatus.PLAYING;
        statusObservers = new HashSet<>();
        timeObservers = new HashSet<>();
        gameField = new GameField(width, height, minesCount);
        this.start = System.currentTimeMillis();
        startGameTimer();
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

    private void startGameTimer() {
        gameTimer = new Timer(1000, e -> {
            long millis = System.currentTimeMillis() - start;
            timeObservers.forEach(observer -> observer.timePastChanged((int)millis / 1000));
        });
        gameTimer.start();
    }

    private void refreshStatus() {
        if (status == GameStatus.FAILED) {
            gameField.prepareLooseField();
            gameTimer.stop();
        } else if (status == GameStatus.WON) {
            gameField.prepareVictoryField();
            gameTimer.stop();
        }

        statusObservers.forEach(observer -> observer.onStatusChanged(status));
    }

    public void addStatusObserver(GameStatusObserver observer) {
        statusObservers.add(observer);
    }

    public void removeStatusObserver(GameStatusObserver observer) {
        statusObservers.remove(observer);
    }

    public void addTimeObserver(TimePastObserver observer) {
        timeObservers.add(observer);
    }

    public void removeTimeObserver(TimePastObserver observer) {
        timeObservers.remove(observer);
    }

    @Override
    public String toString() {
        return "Game{" +
                "start=" + start +
                ", status=" + status +
                '}';
    }
}
