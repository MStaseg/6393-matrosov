package ru.cft.focusstart.matrosov.model;

public enum GameDifficulty {
    EASY(10, 9, 9, "Новичок"), NORMAL(40, 16, 16, "Любитель"), PROFESSIONAL(99, 30, 16, "Профессионал");

    private int numberOfMines;
    private int width;
    private int height;
    private String name;

    GameDifficulty(int numberOfMines, int width, int height, String name) {
        this.numberOfMines = numberOfMines;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }
}
