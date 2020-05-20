package ru.idea.test.core.concurrent.flow;

import org.junit.Before;
import org.junit.Test;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static ru.idea.test.utils.ConcurrentUtils.sleepingRunnable;

public class FlowTest {

    private List<String> items;

    @Before
    public void init() {
        items = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    @Test
    public void whenSubscribeToIt_thenShouldConsumeAll() throws InterruptedException {
        // init
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        publisher.subscribe(new CustomSubscriber<>());

        // act
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        items.forEach(s -> executorService.submit(
                sleepingRunnable(
                        ThreadLocalRandom.current().nextInt(1, 5),
                        () -> publisher.submit(s)
                )
        ));
        executorService.shutdown();
        executorService.awaitTermination(20, TimeUnit.SECONDS);
        publisher.close();
    }

    @Test
    public void whenSubscribeAndTransformElements_thenShouldConsumeAll() throws InterruptedException {
        // init
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        TransformProcessor<String, String> transformProcessor = new TransformProcessor<>(in -> in.concat(" - transformed"));
        publisher.subscribe(transformProcessor);
        transformProcessor.subscribe(new CustomSubscriber<>());

        // act
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        items.forEach(s -> executorService.submit(
                sleepingRunnable(
                        ThreadLocalRandom.current().nextInt(1, 5),
                        () -> publisher.submit(s)
                )
        ));
        executorService.shutdown();
        executorService.awaitTermination(20, TimeUnit.SECONDS);
        publisher.close();
    }
}
