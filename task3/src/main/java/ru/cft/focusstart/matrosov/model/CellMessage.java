package ru.cft.focusstart.matrosov.model;

import ru.cft.focusstart.matrosov.util.Coordinates;

public class CellMessage {
    private Coordinates coordinates;
    private CellType type;

    CellMessage(Coordinates coordinates, CellType type) {
        this.coordinates = coordinates;
        this.type = type;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public CellType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CellMessage{" +
                "coordinates=" + coordinates +
                ", type=" + type +
                '}';
    }
}
