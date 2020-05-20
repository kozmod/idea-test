package ru.idea.test.core.concurrent.queue;

import org.junit.Assert;
import org.junit.Test;
import ru.idea.test.utils.ConcurrentUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueTest {

    private static final String A = "a";
    private static final String B = "B";

    @Test
    public void synchronousQueueTest() throws InterruptedException {
        BlockingQueue<String> q = new SynchronousQueue<>();
        ConcurrentUtils.submit(
                ConcurrentUtils.sleepingRunnable(2, () -> q.put(A))
        );
        ConcurrentUtils.submitChecked(
                () -> Assert.assertEquals(A, q.take())
        ).join();
    }

    @Test
    public void arrayBlockingQueueTest() throws InterruptedException {
        BlockingQueue<String> q = new ArrayBlockingQueue<>(2);
        ConcurrentUtils.submit(
                ConcurrentUtils.sleepingRunnable(1, () -> q.put(A))
        );
        ConcurrentUtils.submit(
                ConcurrentUtils.sleepingRunnable(2, () -> q.put(B))
        );
        ConcurrentUtils.submitChecked(
                () -> {
                    Assert.assertEquals(A, q.take());
                    Assert.assertEquals(B, q.take());
                }
        ).join();
    }
}
