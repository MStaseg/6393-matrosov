package ru.cft.focusstart.matrosov.contoller;

import ru.cft.focusstart.matrosov.model.GameDifficulty;
import ru.cft.focusstart.matrosov.model.stat.StatisticElement;
import ru.cft.focusstart.matrosov.model.stat.StatisticManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Controller represents a modal window with table of users statistic
 */
public class GameStatisticController  extends JDialog {

    private final String[] columnsHeader = new String[] {"Имя", "Результат"};

    /**
     * Creates an window for difficulty asked
     *
     * @param difficulty of the game
     */
    GameStatisticController(GameDifficulty difficulty) {
        getContentPane().setLayout(new BorderLayout());
        setResizable(false);
        setTitle(difficulty.getName());

        List<StatisticElement> elements = StatisticManager.getInstance().getStatistic(difficulty);
        if (elements.size() > 0) {
            configureTable(elements);
        } else {
            addEmptyLabel();
        }

        pack();
    }

    private void configureTable(List<StatisticElement> elements) {
        String[][] displayElements = new String[elements.size()][2];

        int i = 0;
        for(StatisticElement element: elements) {
            displayElements[i][0] = element.getUserName();
            displayElements[i][1] = String.format("%d", element.getResult());
            i++;
        }

        JTable resultsTable = new JTable(displayElements, columnsHeader){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultsTable.setRowHeight(20);
        resultsTable.setEnabled(false);

        add(resultsTable);
    }

    private void addEmptyLabel() {
        JLabel label = new JLabel();
        label.setText("Результатов нет");
        label.setFont(new Font("System", Font.ITALIC, 12));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(label);
    }
}
