package ru.idea.test.core.concurrent.map;

import org.junit.Test;
import ru.idea.test.utils.TestUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConcurrentSkipListMapTest {

    private static final Comparator<LocalDateTime> LOCAL_DATE_TIME_COMPARATOR = Comparator.comparing(Function.identity());
    private static final Supplier<String> randomDoubleStringSupplier = () -> Double.toString(ThreadLocalRandom.current().nextDouble());

    @Test
    public void should() throws InterruptedException {
        ConcurrentNavigableMap<LocalDateTime, String> map = new ConcurrentSkipListMap<>(LOCAL_DATE_TIME_COMPARATOR);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Supplier<Runnable> runnableSuppler = () -> () -> map.put(LocalDateTime.now(), randomDoubleStringSupplier.get());

        TestUtils.fori(10).accept(() -> executorService.execute(runnableSuppler.get()));

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("----  Map ---");
        System.out.println(map);
    }
}
