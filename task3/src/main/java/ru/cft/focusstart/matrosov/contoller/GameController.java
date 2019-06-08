package ru.cft.focusstart.matrosov.contoller;

import ru.cft.focusstart.matrosov.exception.IllegalGameParametersException;
import ru.cft.focusstart.matrosov.model.Game;
import ru.cft.focusstart.matrosov.model.GameDifficulty;
import ru.cft.focusstart.matrosov.model.GameManager;
import ru.cft.focusstart.matrosov.model.stat.StatisticManager;
import ru.cft.focusstart.matrosov.observer.AskUserNameObserver;
import ru.cft.focusstart.matrosov.observer.GameInstanceObserver;
import ru.cft.focusstart.matrosov.util.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Controller represents main screen. Lifecycle of this controller is equal to lifecycle of the app.
 */
public class GameController extends JFrame implements GameInstanceObserver, AskUserNameObserver {

    private GameFieldController fieldController;
    private GameMenuController menuController;
    private GameStateController stateController;

    /**
     * Initiates the game panel. Starts new game with default difficulty. Adds cell panel, menu and state panel.
     *
     */
    public GameController() {
        GameManager.getInstance().startNewGame(GameDifficulty.EASY);
        initMenu();
        initState();
        initFrame();
        loadGameField();
        GameManager.getInstance().addGameInstanceObserver(this);
        StatisticManager.getInstance().addStatusObserver(this);
    }

    private void initMenu() {
        menuController = new GameMenuController();
        setJMenuBar(menuController);
    }

    private void initState() {
        if (stateController != null) {
            remove(stateController);
        }
        stateController = new GameStateController();
        add(stateController, BorderLayout.NORTH);
    }

    private void initFrame() {
        setVisible(true);
        pack();
        setTitle("Сапер");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setIconImage(ImageUtils.getImageIcon("icon").getImage());
    }

    private void loadGameField() {
        Game game = GameManager.getInstance().getGame();

        if (fieldController != null) {
            fieldController.removeAll();
            remove(fieldController);
        }

        try {
            fieldController = new GameFieldController(game.getWidth(), game.getHeight());
            add(fieldController, BorderLayout.SOUTH);
        } catch (IllegalGameParametersException e) {
            System.out.println("Ошибка при создании игры" + e.getMessage());
        }

        initState();
        pack();
    }

    @Override
    public void onNewGame() {
        loadGameField();
    }

    @Override
    public void onUserNameAsk() {
        GameAlertViewController alertController = new GameAlertViewController();
        alertController.setVisible(true);
        alertController.setLocationRelativeTo(this);
    }
}
