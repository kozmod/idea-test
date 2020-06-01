package ru.idea.test.core;

import org.junit.Test;
import ru.idea.test.utils.TestStringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MTest {

    @Test
    public void should_1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(30);
        System.out.println("Start: " + LocalDateTime.now());
        ArrayList<String> stings = new ArrayList<>(1_000_000);
        for (int i = 0; i < 1_000_000; i++) {
            stings.add(TestStringUtils.randomAlphabeticString(50));
        }
        System.out.println("1");
        for (int i = 0; i < 1_000_000; i++) {
            stings.add(TestStringUtils.randomAlphabeticString(50));
        }
        System.out.println("2");
        for (int i = 0; i < 1_000_000; i++) {
            stings.add(TestStringUtils.randomAlphabeticString(50));
        }
        System.out.println("3");
        for (int i = 0; i < 1_000_000; i++) {
            stings.add(TestStringUtils.randomAlphabeticString(50));
        }
        System.out.println("4");
        System.out.println("Stop");
        postAction(stings);
    }

    @Test
    public void should_2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(30);
        System.out.println("Start: " + LocalDateTime.now());
        ArrayList<Integer> stings = new ArrayList<>(1_000_000);
        for (int i = 0; i < 1_000_000; i++) {
            stings.add(ThreadLocalRandom.current().nextInt());
        }
        System.out.println("1");
        for (int i = 0; i < 2_000_000; i++) {
            stings.add(ThreadLocalRandom.current().nextInt());
        }
        System.out.println("2");
        for (int i = 0; i < 3_000_000; i++) {
            stings.add(ThreadLocalRandom.current().nextInt());
        }
        System.out.println("3");
        for (int i = 0; i < 4_000_000; i++) {
            stings.add(ThreadLocalRandom.current().nextInt());
        }
        System.out.println("4");
        System.out.println("Stop:"+ LocalDateTime.now());
        postAction(stings);
    }

    private static void postAction(ArrayList<?> list) throws InterruptedException {
        TimeUnit.SECONDS.sleep(30);

        list.clear();
        list.trimToSize();
        System.out.println("Clear:"+ LocalDateTime.now());
        TimeUnit.SECONDS.sleep(30);

        list = null;
        System.out.println("Set null:"+ LocalDateTime.now());
        TimeUnit.SECONDS.sleep(30);


        System.out.println("GC:"+ LocalDateTime.now());
        System.gc();

        TimeUnit.MINUTES.sleep(10);
    }
}
