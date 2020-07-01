package ru.idea.test.core.concurrent.executor.forkjoinpull;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ParallelStreamApiTest {

    @Test
    public void shouldExecuteParallelInTestWorker() {
        System.getProperties().setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");
        var res = IntStream.range(0, 3)
                .parallel()
                .peek(it -> System.err.printf("Thread [%s] peek: %d\n",
                        Thread.currentThread().getName(), it))
                .sum();
        Assert.assertEquals(3, res);
    }

    @Test
    public void shouldExecuteParallelInOtherFJP() throws InterruptedException {
        System.getProperties().setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");
        var custom = new ForkJoinPool(1);
        custom.submit(() -> {
            var res = IntStream.range(0, 3)
                    .parallel()
                    .peek(it -> System.err.printf("Thread [%s] peek: %d\n", Thread.currentThread().getName(), it))
                    .sum();
            Assert.assertEquals(3, res);
        });
        custom.shutdown();
        custom.awaitTermination(5, TimeUnit.SECONDS);
    }
}
