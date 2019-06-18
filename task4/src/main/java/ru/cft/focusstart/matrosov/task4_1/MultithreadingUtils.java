package ru.cft.focusstart.matrosov.task4_1;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingUtils {
    /**
     * Method gets the full number of steps to do and splits it according the number of threads several parts each one
     * for one thread
     *
     * @param totalSteps how many iterations must be done
     * @param threadCount number of threads to work with
     * @return list or task steps with same size that thread count
     */
    public static List<TaskStep> splitTaskToParts(int totalSteps, int threadCount) {
        int basicStep = totalSteps / threadCount;
        List<TaskStep> parts = new ArrayList<>();

        for (int i = 0; i < threadCount - 1; i++) {
            parts.add(new TaskStep(i * basicStep, (i + 1) * basicStep));
        }

        parts.add(new TaskStep((threadCount - 1) * basicStep, totalSteps));
        return parts;
    }
}
