package ru.idea.test.core.concurrent;

import org.junit.Ignore;
import org.junit.Test;
import ru.idea.test.core.ConcurrentUtils;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class RunnableAndThreadLocalTest {

    @Test
    @Ignore
    public void shouldIncrementSimpleRunnableVar() throws InterruptedException {
        final SimpleRunnable task = new SimpleRunnable(0);

        Thread t1 = ConcurrentUtils.startNewThread(task);
        Thread t2 = ConcurrentUtils.startNewThread(task);

        t1.join();
        t2.join();

        System.out.println(task.var);
        assertEquals(2, task.var);
    }

    @Test
    @Ignore
    public void shouldIncrementThreadLocalRunnableVar() throws InterruptedException {
        final ThreadLocalSleepingRunnable task = new ThreadLocalSleepingRunnable();

        Thread t1 = ConcurrentUtils.startNewThread(task);
        Thread t2 = ConcurrentUtils.startNewThread(task);

        t1.join();
        t2.join();
    }

    @Test
    @Ignore
    public void shouldStopRunnable() throws InterruptedException {
        final StoppableRunnable task = new StoppableRunnable(1);

        Thread t1 = ConcurrentUtils.startNewThread(task);
        Thread t2 = ConcurrentUtils.startNewThread(task);

        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.doStop();

        t1.join();
        t2.join();

        assertTrue(task.isStopped());
    }
}

class SimpleRunnable implements Runnable {
    int var;

    SimpleRunnable(int var) {
        this.var = var;
    }

    @Override
    public void run() {
        var++;
    }
}

class ThreadLocalSleepingRunnable implements Runnable {
    private final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    @Override
    public void run() {
        threadLocal.set((int) (Math.random() * 100D));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadLocal.get());
    }
}

class StoppableRunnable implements Runnable {

    private boolean doStop;

    private final long stopDelay;

    StoppableRunnable(long stopDelay) {
        this.stopDelay = stopDelay;
    }

    synchronized void doStop() {
        doStop = true;
    }

    private synchronized boolean keepRunning() {
        return !doStop;
    }

    boolean isStopped() {
        return doStop;
    }

    @Override
    public void run() {
        while (keepRunning()) {
            // keep doing what this thread should do.
            System.out.println("Running: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(stopDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}