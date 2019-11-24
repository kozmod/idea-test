package ru.idea.test.core.concurrent.executor;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorCompletionServiceTest {

    private List<? extends Callable<String>> tasks;

    @Before
    public void init() {
        tasks = List.of(
                new SleepingCallable("quick", 1),
                new SleepingCallable("slow", 2)
        );
    }

    @Test
    @Ignore
    public void shouldWork() throws InterruptedException, ExecutionException {
        final ExecutorService executor = Executors.newFixedThreadPool(2);
        final ExecutorCompletionService<String> service = new ExecutorCompletionService<>(executor);
        tasks.forEach(service::submit);

        for (int i = 0; i < tasks.size(); i++) {
            //block while any task dos not finished
            final Future<String> future = service.take();
            //future.get() is nt block
            System.out.println(future.get());
        }
    }

    private static class SleepingCallable implements Callable<String> {

        final String name;
        final long period;

        SleepingCallable(final String name, final long period) {
            this.name = name;
            this.period = period;
        }

        public String call() throws InterruptedException {
            System.out.println("call: " + name);
            TimeUnit.SECONDS.sleep(period);
            return name;
        }
    }
}
