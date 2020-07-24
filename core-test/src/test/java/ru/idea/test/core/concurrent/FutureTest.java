package ru.idea.test.core.concurrent;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

public class FutureTest {

    private static final Supplier<Callable<String>> callable = () -> () -> {
        TimeUnit.SECONDS.sleep(1);
        return "RESULT";
    };

    @Test
    public void shouldCall() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> feature = executorService.submit(callable.get());
        System.out.println(feature.isDone());
//        executorService.shutdownNow();
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(feature.isDone());
        System.out.println(feature.get());
//        executorService.submit(callable.get());
    }
}
