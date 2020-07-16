package ru.idea.test.core.concurrent.queue;

import org.junit.Assert;
import org.junit.Test;
import ru.idea.test.core.utils.CheckedRunnable;
import ru.idea.test.utils.ConcurrentUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {

    private static final String A = "a";

    @Test
    public void synchronousQueueTest() throws InterruptedException {
        BlockingQueue<String> q = new SynchronousQueue<>();
        ConcurrentUtils.submit(
                ConcurrentUtils.sleepingRunnable(2, () -> {
                    Assert.assertEquals(0, q.size());
                    q.put(A);
                    Assert.assertEquals(0, q.size());
                })
        );
        ConcurrentUtils.submit(
                CheckedRunnable.wrap(() -> {
                    Assert.assertEquals(0, q.size());
                    Assert.assertEquals(A, q.take());
                    Assert.assertEquals(0, q.size());
                })
        ).join();
    }
}
