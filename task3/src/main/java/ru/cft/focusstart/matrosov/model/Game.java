package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.GameStatisticException;
import ru.cft.focusstart.matrosov.exception.IllegalGameParametersException;
import ru.cft.focusstart.matrosov.model.stat.StatisticManager;
import ru.cft.focusstart.matrosov.observer.GameStatusObserver;
import ru.cft.focusstart.matrosov.observer.TimePastObserver;
import ru.cft.focusstart.matrosov.util.Coordinates;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

/**
 * Class representing an instance of Mine Sweeper game
 *
 */
public class Game {

    private long start;

    private GameDifficulty difficulty;

    private GameStatus status;

    private GameField gameField;

    private List<GameStatusObserver> statusObservers;
    private List<TimePastObserver> timeObservers;

    private Timer gameTimer;

    /**
     * Creates an instance with difficulty given
     *
     * @param difficulty of the game
     * @throws IllegalGameParametersException if difficulty params is not correct
     */
    Game(GameDifficulty difficulty) throws IllegalGameParametersException {
        this(difficulty.getWidth(), difficulty.getHeight(), difficulty.getNumberOfMines());
        this.difficulty = difficulty;
    }

    /**
     * Basic constructor. Creates an instance with width, height and mines count
     *
     * @param width of the game greater 9
     * @param height of the game greater than 9
     * @param minesCount greater than width * height - 1
     * @throws IllegalGameParametersException if params are not valid with the limitations
     */
    Game(int width, int height, int minesCount) throws IllegalGameParametersException {
        this.status = GameStatus.PLAYING;
        statusObservers = new ArrayList<>();
        timeObservers = new ArrayList<>();
        gameField = new GameField(width, height, minesCount);
        this.start = System.currentTimeMillis();
        startGameTimer();
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
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

    /**
     * Opens the cell by giving coordinates and set new status for the game as a result. Initiate the cell observer
     * to get opened cell and all empty cells around.
     *
     * @param c coordinates of the cell
     */
    public void openCell(Coordinates c) {
        if (status != GameStatus.PLAYING) {
            return;
        }

        this.status = gameField.openCell(c);
        refreshStatus();
    }

    /**
     * Marks the cell as flagged
     *
     * @param c coordinates of the cell
     */
    public void setFlag(Coordinates c) {
        if (status != GameStatus.PLAYING) {
            return;
        }

        gameField.setFlag(c);
    }

    /**
     * Force-cell checking method. Uses GameField as delegate. The method checks the flag number near the cell.
     * Than it compares it to the mines count near the cell. If this number are the same it starts to open every
     * cell near the given. If one of the flag is set incorrectly - the game will finish by touching the mine.
     * Else the observer will get a new opened cell list.
     *
     * @param c coordinates of the cell
     */
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
            if (difficulty != null) {
                long millis = System.currentTimeMillis() - start;
                try {
                    StatisticManager.getInstance().putStatisticElement(difficulty, (int)millis / 1000);
                } catch (GameStatisticException e) {
                    System.out.println("Ошибка при добавлении статистики: " + e.getMessage());
                }

            }
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
