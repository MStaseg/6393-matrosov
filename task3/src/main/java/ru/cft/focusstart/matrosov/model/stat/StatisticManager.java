package ru.cft.focusstart.matrosov.model.stat;

import ru.cft.focusstart.matrosov.exception.GameStatisticException;
import ru.cft.focusstart.matrosov.model.GameDifficulty;
import ru.cft.focusstart.matrosov.observer.AskUserNameObserver;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class-singleton represents a manager that works with game statistics and persists history
 */
public class StatisticManager {

    private static StatisticManager instance;

    private String userName;
    private boolean nameAsked = false;
    private StatisticElement waitingElement;

    private List<StatisticElement> results;

    private List<AskUserNameObserver> userNameObservers;

    private StatisticManager() {
        results = new ArrayList<>();
        userNameObservers = new ArrayList<>();
    }

    public static synchronized StatisticManager getInstance() {
        if (instance == null) {
            instance = new StatisticManager();
        }
        return instance;
    }

    /**
     * Saves new statistic element to the store. If the manager never asked the name of the user it will persist
     * temporary element and send observer's event to ask the name. Else it will only save the data to the store.
     * Only works with game with existing difficulty. Won't work with custom game parameters.
     *
     * @param difficulty of the game
     * @param result integer value that represent resulting time of the game
     */
    public void putStatisticElement(GameDifficulty difficulty, int result) throws GameStatisticException {
        if (difficulty == null) {
            throw new GameStatisticException("Вносить элементы статистики можно только для заданной сложности игры");
        }

        StatisticElement element = new StatisticElement(userName, result, difficulty);
        if (!nameAsked) {
            nameAsked = true;
            waitingElement = element;
            userNameObservers.forEach(AskUserNameObserver::onUserNameAsk);
        } else {
            results.add(element);
        }
    }

    /**
     * Returns the best 10 results for the difficulty
     *
     * @param difficulty of the game
     * @return list of the results
     */
    public List<StatisticElement> getStatistic(GameDifficulty difficulty) {
        return results.stream()
                .filter(element -> element.difficulty == difficulty)
                .sorted(Comparator.comparingInt(StatisticElement::getResult))
                .limit(10)
                .collect(Collectors.toList());
    }

    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name for the manager. All next games statistic will save under this name.
     *
     * @param userName name of the player
     */
    public void setUserName(String userName) {
        if (userName == null || userName.equals("")) {
            userName = "unnamed";
        }

        if (userName.length() > 32) {
            this.userName = userName.substring(0, 31).trim();
        } else {
            this.userName = userName;
        }

        if (waitingElement != null) {
            waitingElement.setUserName(this.userName);
            results.add(waitingElement);
            waitingElement = null;
        }
    }

    public void addStatusObserver(AskUserNameObserver observer) {
        userNameObservers.add(observer);
    }

    public void removeStatusObserver(AskUserNameObserver observer) {
        userNameObservers.remove(observer);
    }
}
