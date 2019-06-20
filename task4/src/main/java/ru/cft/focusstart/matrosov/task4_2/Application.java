package ru.cft.focusstart.matrosov.task4_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class Application {

    private static final int CONSUMER_COUNT = (int) (System.nanoTime() % 10 + 5);
    private static final int PRODUCER_COUNT = (int) (System.nanoTime() % 10 + 5);

    private static final int STORAGE_SIZE = (int) (System.nanoTime() % 10 + 5);

    private static final int DELAY = 5000;

    public static void main(String[] args) {
        BlockingQueue<Product> queue = new ArrayBlockingQueue<>(STORAGE_SIZE, false);

        ExecutorService producers = Executors.newFixedThreadPool(PRODUCER_COUNT);
        for (int i = 0; i < PRODUCER_COUNT; i++) {
            producers.submit(new Producer(DELAY, queue));
        }

        ExecutorService consumers = Executors.newFixedThreadPool(CONSUMER_COUNT);
        for (int i = 0; i < CONSUMER_COUNT; i++) {
            consumers.submit(new Consumer(DELAY, queue));
        }
    }
}
