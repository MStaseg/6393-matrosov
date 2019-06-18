package ru.cft.focusstart.matrosov.task4_1;

/**
 *  Class-bean for represent the single multithreading task part. Contains start point and finish point for the task
 */
class TaskStep {
    private int start;
    private int finish;

    TaskStep(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }

    int getStart() {
        return start;
    }

    int getFinish() {
        return finish;
    }
}
