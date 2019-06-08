package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.exception.IllegalGameParametersException;
import ru.cft.focusstart.matrosov.observer.GameCellsObserver;
import ru.cft.focusstart.matrosov.observer.MinesLeftObserver;
import ru.cft.focusstart.matrosov.util.Coordinates;

import java.util.*;

public class GameField {

    private int width;
    private int height;

    private int minesCount;

    private CellType[][] cells;

    private Set<Coordinates> flagCoordinates;
    private Set<Coordinates> openedCoordinates;

    private List<GameCellsObserver> cellsObservers;
    private List<MinesLeftObserver> minesLeftObservers;

    GameField(int width, int height, int minesCount) throws IllegalGameParametersException {
        if (width < 9 || height < 9) {
            throw new IllegalGameParametersException("Минимальные значения ширины и высоты игры равны 9");
        } else if (minesCount >= width * height) {
            throw new IllegalGameParametersException("Слишком много мин для поля заданной ширины");
        }

        this.width = width;
        this.height = height;
        this.minesCount = minesCount;

        cells = new CellType[height][width];

        flagCoordinates = new HashSet<>();
        openedCoordinates = new HashSet<>();

        cellsObservers = new ArrayList<>();
        minesLeftObservers = new ArrayList<>();

        generateInnerGameField();

        minesLeftObservers.forEach(observer -> observer.minesLeftChanged(minesCount));
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    int getMinesCount() {
        return minesCount;
    }

    public void addCellsObserver(GameCellsObserver observer) {
        cellsObservers.add(observer);
    }

    public void removeCellsObserver(GameCellsObserver observer) {
        cellsObservers.remove(observer);
    }

    public void addMinesLeftObserver(MinesLeftObserver observer) {
        minesLeftObservers.add(observer);
    }

    public void removeMinesLeftObserver(MinesLeftObserver observer) {
        minesLeftObservers.remove(observer);
    }

    private void generateInnerGameField() {
        List<Coordinates> possibleMines = new ArrayList<>(width * height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = CellType.EMPTY;
                possibleMines.add(new Coordinates(j, i));
            }
        }

        Random rand = new Random();
        for (int i = 0; i < minesCount; i++) {
            Coordinates coordinates = possibleMines.remove(rand.nextInt(possibleMines.size() - 1));
            placeMine(coordinates);
        }
    }

    private void placeMine(Coordinates c) {
        cells[c.getY()][c.getX()] = CellType.BOMB;
        increaseMinesCountAround(c);
    }

    private void increaseMinesCountAround(Coordinates c) {
        List<Coordinates> coordinates = getAroundCoordinates(c);

        for (Coordinates item: coordinates) {
            cells[item.getY()][item.getX()]
                    = cells[item.getY()][item.getX()].getNextNumberCell();
        }
    }

    GameStatus openCell(Coordinates c) {
        if (openedCoordinates.contains(c) || flagCoordinates.contains(c)) {
            return GameStatus.PLAYING;
        }

        CellType currentType = cells[c.getY()][c.getX()];
        if (currentType == CellType.BOMB) {
            cells[c.getY()][c.getX()] = CellType.BOMBED;
            return  GameStatus.FAILED;
        }

        List<CellMessage> refreshCells = new ArrayList<>();
        List<Coordinates> opened = new ArrayList<>();

        if (currentType == CellType.EMPTY) {
            openEmptyCell(c, opened);
        } else {
            openNumberedCell(c, opened);
        }

        for (Coordinates coordinate: opened) {
            refreshCells.add(new CellMessage(coordinate, cells[coordinate.getY()][coordinate.getX()]));
        }
        cellsObservers.forEach(observer -> observer.onCellsChanged(refreshCells));

        if (openedCoordinates.size() + minesCount == width * height) {
            return GameStatus.WON;
        }

        return GameStatus.PLAYING;
    }

    void setFlag(Coordinates c) {
        if (openedCoordinates.contains(c)) {
            return;
        }

        CellType type;
        if (flagCoordinates.contains(c)) {
            flagCoordinates.remove(c);
            type = CellType.CLOSED;
        } else {
            flagCoordinates.add(c);
            type = CellType.FLAGGED;
        }

        List<CellMessage> refreshCells = new ArrayList<>();
        refreshCells.add(new CellMessage(c, type));
        cellsObservers.forEach(observer -> observer.onCellsChanged(refreshCells));

        minesLeftObservers.forEach(observer -> observer.minesLeftChanged(minesCount - flagCoordinates.size()));
    }

    GameStatus forceCheck(Coordinates c) {
        CellType type = cells[c.getY()][c.getX()];
        List<Coordinates> coordinates = getAroundCoordinates(c);
        int flagCount = 0;

        for (Coordinates coordinate: coordinates) {
            if (flagCoordinates.contains(coordinate)) {
                flagCount++;
            }
        }

        if (flagCount == type.getNumberOfMinesAround()) {
            for (Coordinates coordinate: coordinates) {
                if (!openedCoordinates.contains(coordinate)) {
                    GameStatus status = openCell(coordinate);
                    if (status == GameStatus.FAILED || status == GameStatus.WON) {
                        return status;
                    }
                }
            }
        }

        return GameStatus.PLAYING;
    }

    void prepareLooseField() {
        List<CellMessage> refreshCells = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                CellType currentType = cells[i][j];
                Coordinates c = new Coordinates(j, i);
                if (currentType != CellType.BOMB && flagCoordinates.contains(c)) {
                    refreshCells.add(new CellMessage(c, CellType.NO_BOMB));
                } else if (currentType == CellType.BOMB || currentType == CellType.BOMBED) {
                    refreshCells.add(new CellMessage(c, cells[i][j]));
                }
            }
        }

        cellsObservers.forEach(observer -> observer.onCellsChanged(refreshCells));
    }

    void prepareVictoryField() {
        List<CellMessage> refreshCells = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                CellType currentType = cells[i][j];
                if (currentType == CellType.BOMB) {
                    Coordinates c = new Coordinates(j, i);
                    refreshCells.add(new CellMessage(c, CellType.FLAGGED));
                }
            }
        }

        cellsObservers.forEach(observer -> observer.onCellsChanged(refreshCells));
    }

    private void openNumberedCell(Coordinates c, List<Coordinates> opened) {
        openedCoordinates.add(c);
        opened.add(c);

        List<Coordinates> around = getAroundCoordinates(c);
        for(Coordinates coordinate: around) {
            if (openedCoordinates.contains(coordinate)) {
                continue;
            }

            CellType currentType = cells[coordinate.getY()][coordinate.getX()];
            if (currentType == CellType.EMPTY) {
                openedCoordinates.add(coordinate);
                opened.add(coordinate);
                openEmptyCell(coordinate, opened);
            }
        }
    }

    private void openEmptyCell(Coordinates c, List<Coordinates> opened) {
        if (openedCoordinates.contains(c)) {
            openedCoordinates.add(c);
            opened.add(c);
        }

        List<Coordinates> around = getAroundCoordinates(c);
        int closedCount = 0;
        for(Coordinates coordinate: around) {
            if (openedCoordinates.contains(coordinate)) {
                continue;
            }

            closedCount++;
            openedCoordinates.add(coordinate);
            opened.add(coordinate);
        }

        if (closedCount == 0) {
            return;
        }

        for(Coordinates coordinate: around) {
            CellType currentType = cells[coordinate.getY()][coordinate.getX()];
            if (currentType == CellType.EMPTY) {
                openEmptyCell(coordinate, opened);
            }
        }
    }

    private List<Coordinates> getAroundCoordinates(Coordinates c) {
        List<Coordinates> result = new LinkedList<>();

        for (int i = Math.max(c.getY() - 1, 0); i <= Math.min(c.getY() + 1, height - 1); i++) {
            for (int j = Math.max(c.getX() - 1, 0); j <= Math.min(c.getX() + 1, width - 1); j++) {
                if (i == c.getY() && j == c.getX()) {
                    continue;
                }
                result.add(new Coordinates(j, i));
            }
        }

        return result;
    }
}
