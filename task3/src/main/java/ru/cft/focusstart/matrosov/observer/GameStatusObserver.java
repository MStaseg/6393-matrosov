package ru.cft.focusstart.matrosov.observer;

import ru.cft.focusstart.matrosov.model.GameStatus;

public interface GameStatusObserver {
    void onStatusChanged(GameStatus status);
}
