package ru.idea.test.core.concurrent.future;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

public class FutureTest {

    @Test
    public void shouldExecuteFutureTaskByThread() throws ExecutionException, InterruptedException {
        var ex = "Hello, World!";
        Callable<String> task = () -> ex;
        FutureTask<String> future = new FutureTask<>(task);
        new Thread(future).start();
        Assert.assertEquals(ex, future.get());
    }

    @Test
    public void shouldExecuteFutureTaskByService() throws ExecutionException, InterruptedException {
        var ex = "Hello, World!";
        Callable<String> task = () -> ex;
        FutureTask<String> futureTask = new FutureTask<>(task);
        Executors.newFixedThreadPool(1).submit(futureTask);
        Assert.assertEquals(ex, futureTask.get());
    }
}
