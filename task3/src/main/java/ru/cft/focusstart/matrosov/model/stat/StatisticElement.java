package ru.cft.focusstart.matrosov.model.stat;

import ru.cft.focusstart.matrosov.model.GameDifficulty;

public class StatisticElement {
    private String userName;
    private int result;
    GameDifficulty difficulty;

    StatisticElement(String userName, int result, GameDifficulty difficulty) {
        this.userName = userName;
        this.result = result;
    }

    public String getUserName() {
        return userName;
    }

    public int getResult() {
        return result;
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "StatisticElement{" +
                "userName='" + userName + '\'' +
                ", result=" + result +
                '}';
    }
}
