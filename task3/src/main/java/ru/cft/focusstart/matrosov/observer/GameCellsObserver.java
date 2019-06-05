package ru.cft.focusstart.matrosov.observer;

import ru.cft.focusstart.matrosov.model.CellMessage;

import java.util.List;

public interface GameCellsObserver {
    void onCellsChanged(List<CellMessage> refreshingCells);
}
