package ru.cft.focusstart.matrosov.task4_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private static long idCounter = 0;

    private long id;
    private int delay;
    private BlockingQueue<Product> queue;

    private Logger logger = LoggerFactory.getLogger(Consumer.class);

    Consumer(int delay, BlockingQueue<Product> queue) {
        this.id = ++idCounter;
        this.delay = delay;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Product product = queue.take();
                logger.info("[Потребитель #{}] id={}: ресурс забран со склада", id, product.getId());
                Thread.sleep(delay);
                logger.info("[Потребитель #{}] id={}: ресурс потреблен", id, product.getId());
            }
        } catch (InterruptedException e) {
            System.out.println("Поток " + Thread.currentThread().getName()
                    + " был прерван по причине: " + e.getMessage());
        }
    }
}
