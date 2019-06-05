package ru.cft.focusstart.matrosov.contoller;

import ru.cft.focusstart.matrosov.exception.IllegalGameParametersException;
import ru.cft.focusstart.matrosov.model.Game;
import ru.cft.focusstart.matrosov.model.GameDifficulty;
import ru.cft.focusstart.matrosov.model.GameManager;
import ru.cft.focusstart.matrosov.observer.GameInstanceObserver;

import javax.swing.*;
import java.awt.*;

public class GameController extends JFrame implements GameInstanceObserver {

    private GameFieldController fieldController;
    private GameMenuController menuController;

    public GameController() {
        initMenu();
        initFrame();
        loadGameField();
        GameManager.getInstance().addGameInstanceObserver(this);
    }

    private void initMenu() {
        menuController = new GameMenuController();
        setJMenuBar(menuController);
    }

    private void initFrame() {
        setVisible(true);
        pack();
        setTitle("Сапер");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
    }

    private void loadGameField() {
        Game game = GameManager.getInstance().getGame();
        if (game == null) {
            return;
        }

        if (fieldController != null) {
            fieldController.removeAll();
            remove(fieldController);
        }

        try {
            fieldController = new GameFieldController(game.getWidth(), game.getHeight());
            add(fieldController);
        } catch (IllegalGameParametersException e) {
            System.out.println("Ошибка при создании игры" + e.getMessage());
        }

        pack();
    }

    @Override
    public void onNewGame() {
        loadGameField();
    }
}
