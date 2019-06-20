package ru.cft.focusstart.matrosov.task4_2;

import java.util.concurrent.atomic.AtomicLong;

class Product {

    private static AtomicLong idCounter = new AtomicLong(0);

    private long id;

    Product() {
        this.id = idCounter.incrementAndGet();
    }

    long getId() {
        return id;
    }
}
