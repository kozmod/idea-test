package ru.idea.test.core.concurrent;

import org.junit.Test;
import ru.idea.test.core.ConcurrentUtils;

public class WaitNotifyTest {

    private static final int PRODUCT_LIMIT = 10;
    private static final int PRODUCT_QUANTITY = 100;

    @Test
    public void shouldWork() throws InterruptedException {
        final Store store = new Store(PRODUCT_LIMIT);
        final ProductSupplier producer = new ProductSupplier(store, PRODUCT_QUANTITY);
        final ProductConsumer consumer = new ProductConsumer(store, PRODUCT_QUANTITY);
        ConcurrentUtils.startNewThread(producer);
        ConcurrentUtils.startNewThread(consumer).join();
    }
}

final class Store {
    private final int productLimit;
    private int productQuantity = 0;

    Store(int productLimit) {
        this.productLimit = productLimit;
    }

    synchronized void get() throws InterruptedException {
        while (productQuantity < 1) {
            this.wait();
        }
        productQuantity--;
        System.err.println("Consumer got 1 product. Products are left: " + productQuantity);
        this.notify();
    }

    synchronized void put() throws InterruptedException {
        while (productQuantity >= productLimit) {
            this.wait();
        }
        productQuantity++;
        System.err.println("Supplier put 1 product.Quantity of products is " + productQuantity);
        this.notify();
    }
}

final class ProductSupplier implements Runnable {

    private Store store;
    private final int needSupply;

    ProductSupplier(Store store, int needSupply) {
        this.store = store;
        this.needSupply = needSupply;
    }

    public void run() {
        for (int i = 1; i <= needSupply; i++) {
            try {
                store.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

final class ProductConsumer implements Runnable {

    private final Store store;
    private final int needGet;

    ProductConsumer(Store store, int needGet) {
        this.store = store;
        this.needGet = needGet;
    }

    public void run() {
        for (int i = 1; i <= needGet; i++) {
            try {
                store.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}