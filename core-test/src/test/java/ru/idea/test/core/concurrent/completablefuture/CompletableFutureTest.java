package ru.idea.test.core.concurrent.completablefuture;

import org.junit.Assert;
import org.junit.Test;
import ru.idea.test.utils.ConcurrentUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {

    @Test
    public void shouldCompleteFromOtherThread() throws ExecutionException, InterruptedException {
        var exp = "complete from other thread";
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        ConcurrentUtils.submit(
                ConcurrentUtils.sleepingRunnable(
                        1,
                        () -> completableFuture.complete(exp)
                )
        );
        String result = completableFuture.get(); //block here
        Assert.assertEquals(exp, result);
    }

    @Test
    public void shouldRunAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Running asynchronous task in parallel");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        });
        Void result = future.get(); //block here
        Assert.assertNull(result);
    }

    @Test
    public void shouldSupplyAsync() throws ExecutionException, InterruptedException {
        final var exp = "This is the result of the asynchronous computation";
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return exp;
        });
        String result = future.get(); //block here
        Assert.assertEquals(exp, result);
    }


    @Test
    public void shouldThenApply() throws ExecutionException, InterruptedException {
        var expWorld = "World";
        var expHello = "Hello";
        var thenRes = expHello + expWorld;
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return expWorld;
        });
        CompletableFuture<String> thenCompletableFuture
                = completableFuture.thenApply(r -> expHello + r);
        String result = thenCompletableFuture.get(); //block here
        Assert.assertEquals(thenRes, result);
    }

    @Test
    public void shouldThenAccept() throws ExecutionException, InterruptedException {
        var expWorld = "World";
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> expWorld);
        CompletableFuture<Void> thenCompletableFuture
                = completableFuture.thenAccept(value -> System.out.println("Hello " + value));
        Void res = thenCompletableFuture.get(); //block here
        Assert.assertNull(res);
    }

    @Test
    public void shouldThenRun() throws ExecutionException, InterruptedException {
        var expWorld = "XXX";
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> expWorld);
        CompletableFuture<Void> thenCompletableFuture
                = completableFuture.thenRun(() -> System.out.println("Example with thenRun()."));
        Void res = thenCompletableFuture.get(); //block here
        Assert.assertNull(res);
    }
}
