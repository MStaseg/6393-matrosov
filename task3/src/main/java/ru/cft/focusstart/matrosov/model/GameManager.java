package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.IllegalGameParametersException;
import ru.cft.focusstart.matrosov.observer.GameInstanceObserver;

import java.io.IOException;
import java.util.*;

public class GameManager {

    private static GameManager instance;

    private Game game;

    private Set<GameInstanceObserver> gameInstanceObservers;

    private GameManager() {
        gameInstanceObservers = new HashSet<>();
    }

    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public Game getGame() {
        return game;
    }

    public void startNewGame(GameDifficulty difficulty) throws IllegalGameParametersException {
        startNewGame(difficulty.getWidth(), difficulty.getHeight(), difficulty.getNumberOfMines());
    }

    public void startNewGame(int width, int height, int minesCount) throws IllegalGameParametersException {
        this.game = new Game(width, height, minesCount);
        gameInstanceObservers.forEach(observer -> observer.onNewGame());
    }

    public void addGameInstanceObserver(GameInstanceObserver observer) {
        gameInstanceObservers.add(observer);
    }

    public void removeGameInstanceObserver(GameInstanceObserver observer) {
        gameInstanceObservers.remove(observer);
    }
}
