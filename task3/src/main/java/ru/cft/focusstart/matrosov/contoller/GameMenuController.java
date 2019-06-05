package ru.cft.focusstart.matrosov.contoller;

import ru.cft.focusstart.matrosov.exception.IllegalGameParametersException;
import ru.cft.focusstart.matrosov.model.Game;
import ru.cft.focusstart.matrosov.model.GameDifficulty;
import ru.cft.focusstart.matrosov.model.GameManager;

import javax.swing.*;
import java.awt.*;

public class GameMenuController extends JMenuBar {

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
        JMenuItem specialItem = new JMenuItem("Особый...");
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
        fileMenu.add(specialItem);

        fileMenu.addSeparator();

        fileMenu.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));

        add(fileMenu);
    }

    private void createRecordsMenu() {
        JMenu newMenu = new JMenu("Рекорды");

        JMenuItem txtFileItem = new JMenuItem("Новичок");
        newMenu.add(txtFileItem);

        JMenuItem imgFileItem = new JMenuItem("Профессионал");
        newMenu.add(imgFileItem);

        JMenuItem folderItem = new JMenuItem("Любитель");
        newMenu.add(folderItem);

        add(newMenu);
    }

    private void startNewGame(GameDifficulty difficulty) {
            GameManager.getInstance().startNewGame(difficulty);
    }

    private void startNewGame() {
        Game game = GameManager.getInstance().getGame();
        GameManager.getInstance().startNewGame(game.getWidth(), game.getHeight(), game.getMinesCount());
    }
}
