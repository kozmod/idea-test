package ru.idea.test.core.concurrent.completablefuture;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import ru.idea.test.utils.ConcurrentUtils;

import java.io.IOException;
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

    @Test
    public void shouldRunMultipleFutureParallel() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        combinedFuture.get(); //block here

        Assert.assertTrue(future1.isDone());
        Assert.assertTrue(future2.isDone());
        Assert.assertTrue(future3.isDone());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldHandleInnerException() throws ExecutionException, InterruptedException {
        String name = null;
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((value, throwable) -> {
            Assert.assertNotNull(throwable);
            Assert.assertThat(throwable, Matchers.instanceOf(RuntimeException.class));
            return value != null
                    ? value
                    : "Hello, Stranger!";
        });
        Assert.assertEquals("Hello, Stranger!", completableFuture.get());
    }

    @Test
    public void shouldCompleteExceptionallyFromOtherThread() {
        var ioExMsg = "Calculation failed!";
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(new IOException(ioExMsg));
        completableFuture.handle((val,throwable) -> {
            Assert.assertNull(val);
            Assert.assertNotNull(throwable);
            Assert.assertThat(throwable, Matchers.instanceOf(IOException.class));
            System.err.println("CALL");
            return "some res";
        });
        completableFuture.handle((val,throwable) -> {
            System.err.println("CALL 2");
            Assert.assertNotNull(val); //throw AssertionError in other thread
            System.err.println("NOT CALL");
            return "some res";
        });
        try {
            completableFuture.get(); // ExecutionException
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertNotNull(ex);
            Assert.assertThat(ex, Matchers.instanceOf(ExecutionException.class));
            Assert.assertThat(ex.getCause(), Matchers.instanceOf(IOException.class));
            Assert.assertEquals(ioExMsg, ex.getCause().getMessage());
        }
    }
}
