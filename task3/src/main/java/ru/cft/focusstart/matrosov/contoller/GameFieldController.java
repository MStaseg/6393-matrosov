package ru.cft.focusstart.matrosov.contoller;

import ru.cft.focusstart.matrosov.model.*;
import ru.cft.focusstart.matrosov.model.GameManager;
import ru.cft.focusstart.matrosov.observer.GameCellsObserver;
import ru.cft.focusstart.matrosov.observer.GameStatusObserver;
import ru.cft.focusstart.matrosov.util.Coordinates;
import ru.cft.focusstart.matrosov.view.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller represents the instance of game fields as table with the cells and appropriate actions
 */
public class GameFieldController extends JPanel implements GameCellsObserver, GameStatusObserver {

    private static final int CELL_SIZE = 20;

    private int width;
    private int height;

    private Cell[][] cells;

    private boolean leftButtonHold = false;
    private boolean rightButtonHold = false;

    /**
     * Initiates an instance of game field with existing game width and height. Fills with closed cells first.
     *
     * @param width of the game
     * @param height of the game
     */
    GameFieldController(int width, int height) {
        this.width = width;
        this.height = height;

        setLayout(new GridLayout(height, width));
        setPreferredSize(new Dimension(width * CELL_SIZE,
                height * CELL_SIZE));

        fillGameField();

        GameManager.getInstance().getGame().getGameField().addCellsObserver(this);
        GameManager.getInstance().getGame().addStatusObserver(this);
    }

    private void fillGameField() {
        cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell(CellType.CLOSED, CELL_SIZE);
                cell.setCoordinates(new Coordinates(j, i));
                addCellMouseListener(cell);
                cells[i][j] = cell;
                add(cell);
            }
        }
    }

    private void handleCellOpening(Cell cell) {
        GameManager.getInstance().getGame().openCell(cell.getCoordinates());
    }

    private void handleSettingFlag(Cell cell) {
        GameManager.getInstance().getGame().setFlag(cell.getCoordinates());
    }

    private void startForceCheck(Cell cell) {
        Coordinates c = cell.getCoordinates();

        List<Coordinates> coordinates = getAroundClosedButtons(c.getY(), c.getX());
        for (Coordinates coordinate: coordinates) {
            Cell nearbyCell = cells[coordinate.getY()][coordinate.getX()];
            nearbyCell.emulatePressOn();
        }
    }

    private void endForceCheck(Cell cell) {
        Coordinates c = cell.getCoordinates();

        List<Coordinates> coordinates = getAroundClosedButtons(c.getY(), c.getX());
        for (Coordinates coordinate: coordinates) {
            Cell nearbyCell = cells[coordinate.getY()][coordinate.getX()];
            nearbyCell.emulatePressOff();
        }

        GameManager.getInstance().getGame().forceCheck(c);
    }

    private void addCellMouseListener(Cell cell) {
        cell.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                int button = e.getButton();
                if (button == 3 && leftButtonHold || button == 1 && rightButtonHold) {
                    startForceCheck(cell);
                }

                if (button == 1) {
                    leftButtonHold = true;
                } else if (button == 3) {
                    rightButtonHold = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int button = e.getButton();
                if (leftButtonHold && rightButtonHold) {
                    endForceCheck(cell);
                } else {
                    if (button == 1) {
                        handleCellOpening(cell);
                    } else if (button == 3) {
                        handleSettingFlag(cell);
                    }
                }

                if (button == 1) {
                    leftButtonHold = false;
                } else if (button == 3) {
                    rightButtonHold = false;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    @Override
    public void onCellsChanged(List<CellMessage> refreshingCells) {
        refreshingCells.forEach( value -> {
            this.cells[value.getCoordinates().getY()][value.getCoordinates().getX()].setType(value.getType());
        });
    }

    private List<Coordinates> getAroundClosedButtons(int x, int y) {
        List<Coordinates> result = new LinkedList<>();

        for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, width - 1); i++) {
            for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, height - 1); j++) {
                if (i == x && j == y || cells[i][j].getType() != CellType.CLOSED) {
                    continue;
                }
                result.add(new Coordinates(j, i));
            }
        }

        return result;
    }

    @Override
    public void onStatusChanged(GameStatus status) {
        if (status != GameStatus.PLAYING) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    cells[i][j].setEnabled(false);
                }
            }
        }
    }
}
