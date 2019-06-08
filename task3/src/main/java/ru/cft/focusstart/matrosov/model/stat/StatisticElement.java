package ru.cft.focusstart.matrosov.model.stat;

import ru.cft.focusstart.matrosov.model.GameDifficulty;

/**
 * Class-bean for statistic element structure
 */
public class StatisticElement {
    private String userName;
    private int result;
    GameDifficulty difficulty;

    StatisticElement(String userName, int result, GameDifficulty difficulty) {
        this.userName = userName;
        this.result = result;
        this.difficulty = difficulty;
    }

    public String getUserName() {
        return userName;
    }

    public int getResult() {
        return result;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "StatisticElement{" +
                "userName='" + userName + '\'' +
                ", result=" + result +
                ", difficulty=" + difficulty +
                '}';
    }
}
