package ru.cft.focusstart.matrosov.task4_1;

import java.util.*;
import java.util.concurrent.*;

public class Application {
    private static int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors() * 2 + 1;
    private static int TOTAL = 1_000_000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        calculateForMultiThreadWithPool();
    }

    /**
     * Method calculates the result with only one thread and shows the result
     */
    private static void calculateForSingleThread() {
        long start = System.currentTimeMillis();
        long sum = 0;

        for (int i = 1; i <= TOTAL; i ++) {
            sum += MathUtils.isPrimeNumber(i) ? i : 0;
        }

        double timeSpent = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("Sum = " + sum + ", timeSpent = " + timeSpent + " seconds");
    }

    /**
     * Method calculates the result with multi threads than shows the result
     */
    private static void calculateForMultiThreadWithPool() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        long start = System.currentTimeMillis();

        List<Future<Long>> futures = new ArrayList<>();
        List<TaskStep> parts = MultithreadingUtils.splitTaskToParts(TOTAL, NUMBER_OF_THREADS);

        for(TaskStep part: parts) {
            futures.add(CompletableFuture.supplyAsync(() -> {
                long sum = 0;
                for (int k = part.getStart(); k <= part.getFinish(); k ++) {
                    sum += MathUtils.isPrimeNumber(k) ? k : 0;
                }
                return sum;
            }, pool));
        }

        long value = 0;
        for (Future<Long> future : futures) {
            value += future.get();
        }

        double timeSpent = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("Sum = " + value + ", timeSpent = " + timeSpent + " seconds");

        pool.shutdown();
    }
}
