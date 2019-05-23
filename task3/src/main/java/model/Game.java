package model;

import java.util.Date;

public class Game {

    private Date start;

    private GameDifficulty difficulty;

    private GameStatus status;

    private GameField gameField;

    private long playIntervalCacheLength;

    private Game() {
        this.difficulty = difficulty;
        this.start = new Date();
    }

    public Game(GameDifficulty difficulty) {
        this();
        gameField = new GameField(difficulty.getWidth(), difficulty.getHeight(), difficulty.getNumberOfMines());
    }

    public Game(int width, int height, int minesCount) {
        this();
        gameField = new GameField(width, height, minesCount);
    }

    public void pause() {
        Date now = new Date();
        playIntervalCacheLength += now.getTime() - start.getTime();
    }

    public void resume() {
        start = new Date();
    }


}
