package ru.idea.test.core.concurrent;

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RunnableAndThreadLocalTest {

    @Test
    @Ignore
    public void shouldIncrementSimpleRunnableVar() throws InterruptedException {
        final ConcurrentUtils.SimpleRunnable task = new ConcurrentUtils.SimpleRunnable(0);

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
        final ConcurrentUtils.ThreadLocalRunnable task = new ConcurrentUtils.ThreadLocalRunnable();

        Thread t1 = ConcurrentUtils.startNewThread(task);
        Thread t2 = ConcurrentUtils.startNewThread(task);

        t1.join();
        t2.join();
    }

    @Test
    @Ignore
    public void shouldStopRunnable() throws InterruptedException {
        final ConcurrentUtils.StoppableRunnable task = new ConcurrentUtils.StoppableRunnable(1);

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
