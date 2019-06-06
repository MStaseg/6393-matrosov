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

public class GameFieldController extends JPanel implements GameCellsObserver, GameStatusObserver {

    private static final int CELL_SIZE = 20;

    private int width;
    private int height;

    Cell cells[][];

    boolean leftButtonHold = false;
    boolean rightButtonHold = false;

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
                cell.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int button = e.getButton();
                        if (button == 1) {
                            handleCellOpening(e);
                        } else if (button == 3) {
                            handleSettingFlag(e);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        int button = e.getButton();
                        if (button == 1) {
                            leftButtonHold = true;
                        } else if (button == 3 && leftButtonHold) {
                            rightButtonHold = true;
                            startForceCheck(e);
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        int button = e.getButton();
                        if (button == 1 && rightButtonHold) {
                            leftButtonHold = false;
                            rightButtonHold = false;
                            endForceCheck(e);
                        } else if (button == 3 && leftButtonHold && rightButtonHold) {
                            rightButtonHold = false;
                            endForceCheck(e);
                        } else if (button == 1) {
                            leftButtonHold = false;
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                cells[i][j] = cell;
                add(cell);
            }
        }
    }

    private void handleCellOpening(MouseEvent e) {
        Coordinates c = getEventButtonCoordinates(e);
        GameManager.getInstance().getGame().openCell(c);
    }

    private void handleSettingFlag(MouseEvent e) {
        Coordinates c = getEventButtonCoordinates(e);
        GameManager.getInstance().getGame().setFlag(c);
    }

    private void startForceCheck(MouseEvent e) {
        Coordinates c = getEventButtonCoordinates(e);
        List<Coordinates> coordinates = getAroundClosedButtons(c.getY(), c.getX());

        for (Coordinates coordinate: coordinates) {
            Cell cell = cells[coordinate.getY()][coordinate.getX()];
            cell.emulatePressOn();
        }
    }

    private void endForceCheck(MouseEvent e) {
        Coordinates c = getEventButtonCoordinates(e);
        List<Coordinates> coordinates = getAroundClosedButtons(c.getY(), c.getX());

        for (Coordinates coordinate: coordinates) {
            Cell cell = cells[coordinate.getY()][coordinate.getX()];
            cell.emulatePressOff();
        }

        GameManager.getInstance().getGame().forceCheck(c);
    }

    private Coordinates getEventButtonCoordinates(MouseEvent e) {
        if (!(e.getSource() instanceof Cell)) {
            return null;
        }

        Cell cell = (Cell) e.getSource();
        int x = cell.getX() / CELL_SIZE;
        int y = cell.getY() / CELL_SIZE;
        return new Coordinates(x, y);
    }

    private ImageIcon getImageIcon(String name) {
        String fileName = "/images/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        Image newImage = icon.getImage().getScaledInstance(width, height,  Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon(newImage);
        return icon;
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
