package ru.idea.test.core.concurrent.completablefuture;

import org.junit.Assert;
import org.junit.Test;
import ru.idea.test.utils.ConcurrentUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {

    @Test
    public void name() throws ExecutionException, InterruptedException {
        var exp = "complete from other thread";
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        ConcurrentUtils.submit(
                ConcurrentUtils.sleepingRunnable(
                        3,
                        () -> completableFuture.complete(exp)
                )
        );
        String result = completableFuture.get(); //block here
        Assert.assertEquals(exp,result);
    }
}
