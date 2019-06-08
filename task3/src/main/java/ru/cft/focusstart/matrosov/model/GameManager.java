package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.IllegalGameParametersException;
import ru.cft.focusstart.matrosov.observer.GameInstanceObserver;

import java.util.*;

/**
 * Class-singleton to manage and persist the current game instance
 */
public class GameManager {

    private static GameManager instance;

    private Game game;

    private List<GameInstanceObserver> gameInstanceObservers;

    private GameManager() {
        gameInstanceObservers = new ArrayList<>();
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
        this.game = new Game(difficulty);
        gameInstanceObservers.forEach(GameInstanceObserver::onNewGame);
    }

    public void startNewGame(int width, int height, int minesCount) throws IllegalGameParametersException {
        this.game = new Game(width, height, minesCount);
        gameInstanceObservers.forEach(GameInstanceObserver::onNewGame);
    }

    public void addGameInstanceObserver(GameInstanceObserver observer) {
        gameInstanceObservers.add(observer);
    }

    public void removeGameInstanceObserver(GameInstanceObserver observer) {
        gameInstanceObservers.remove(observer);
    }
}
