package ru.cft.focusstart.matrosov.task4_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private static long idCounter = 0;

    private long id;
    private int delay;
    private BlockingQueue<Product> queue;

    private DateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private Logger logger = LoggerFactory.getLogger(Consumer.class);

    Producer(int delay, BlockingQueue<Product> queue) {
        this.id = ++idCounter;
        this.delay = delay;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(delay);
                Product product = new Product();

                logger.info(dateFormatter.format(new Date())
                        + " (Производитель #" + id + ") "
                        +"ID " + product.getId()
                        + ": ресурс произведен");

                queue.put(product);

                logger.info(dateFormatter.format(new Date())
                        + " (Производитель #" + id + ") "
                        +"ID " + product.getId()
                        + ": ресурс помещен на склад");
            }
        } catch (InterruptedException e) {
            System.out.println("Поток " + Thread.currentThread().getName() + " был прерван по причине: " + e.getMessage());
        }
    }
}
