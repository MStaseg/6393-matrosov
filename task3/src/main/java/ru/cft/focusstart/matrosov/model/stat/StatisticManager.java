package ru.cft.focusstart.matrosov.model.stat;

import ru.cft.focusstart.matrosov.model.GameDifficulty;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticManager {

    private static StatisticManager instance;

    private String userName;

    private List<StatisticElement> results;

    private StatisticManager() {}

    public static synchronized StatisticManager getInstance() {
        if (instance == null) {
            instance = new StatisticManager();
        }
        return instance;
    }

    public void putStatisticElement(GameDifficulty difficulty, int result) {
        StatisticElement element = new StatisticElement(userName, result, difficulty);
        results.add(element);
    }

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

    public void setUserName(String userName) {
        if (userName.length() > 32) {
            this.userName = userName.substring(0, 31);
        } else {
            this.userName = userName;
        }
    }
}
