package model;

public enum GameDifficulty {
    EASY(10, 9, 9), NORMAL(40, 16, 16), PROFESSIONAL(99, 30, 16);

    private int numberOfMines;
    private int width;
    private int height;

    GameDifficulty(int numberOfMines, int width, int height) {
        this.numberOfMines = numberOfMines;
        this.width = width;
        this.height = height;
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
}
