package ru.cft.focusstart.matrosov.contoller;

import ru.cft.focusstart.matrosov.model.Game;
import ru.cft.focusstart.matrosov.model.GameDifficulty;
import ru.cft.focusstart.matrosov.model.GameManager;

import javax.swing.*;

/**
 * Controller represent the top menu with all items and actions for all of them
 */
class GameMenuController extends JMenuBar {

    GameMenuController() {
        super();
        createGameMenu();
        createRecordsMenu();
    }

    private void createGameMenu() {
        JMenu fileMenu = new JMenu("Игра");

        JMenuItem newGameItem = new JMenuItem("Новая игра");
        JMenuItem beginnerItem = new JMenuItem("Новичок");
        JMenuItem middleItem = new JMenuItem("Любитель");
        JMenuItem professionalItem = new JMenuItem("Профессионал");
        JMenuItem exitItem = new JMenuItem("Выйти");

        fileMenu.add(newGameItem);
        newGameItem.addActionListener(e -> this.startNewGame());

        fileMenu.addSeparator();

        fileMenu.add(beginnerItem);
        beginnerItem.addActionListener(e -> this.startNewGame(GameDifficulty.EASY));
        fileMenu.add(middleItem);
        middleItem.addActionListener(e -> this.startNewGame(GameDifficulty.NORMAL));
        fileMenu.add(professionalItem);
        professionalItem.addActionListener(e -> this.startNewGame(GameDifficulty.PROFESSIONAL));

        fileMenu.addSeparator();

        fileMenu.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));

        add(fileMenu);
    }

    private void createRecordsMenu() {
        JMenu newMenu = new JMenu("Рекорды");

        JMenuItem beginnerStatItem = new JMenuItem("Новичок");
        beginnerStatItem.addActionListener(e -> this.showStatistic(GameDifficulty.EASY));
        newMenu.add(beginnerStatItem);

        JMenuItem normalStatItem = new JMenuItem("Любитель");
        normalStatItem.addActionListener(e -> this.showStatistic(GameDifficulty.NORMAL));
        newMenu.add(normalStatItem);

        JMenuItem proStatItem = new JMenuItem("Профессионал");
        proStatItem.addActionListener(e -> this.showStatistic(GameDifficulty.PROFESSIONAL));
        newMenu.add(proStatItem);

        add(newMenu);
    }

    private void startNewGame(GameDifficulty difficulty) {
            GameManager.getInstance().startNewGame(difficulty);
    }

    private void startNewGame() {
        Game game = GameManager.getInstance().getGame();
        GameManager.getInstance().startNewGame(game.getWidth(), game.getHeight(), game.getMinesCount());
    }

    private void showStatistic(GameDifficulty difficulty) {
        GameStatisticController statisticController = new GameStatisticController(difficulty);
        statisticController.setVisible(true);
        statisticController.setLocationRelativeTo(this);
    }
}
