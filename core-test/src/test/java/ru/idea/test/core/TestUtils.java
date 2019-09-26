package ru.idea.test.core;

import java.util.function.Consumer;

public final class TestUtils {

    private TestUtils() {
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static Consumer<Runnable> fori(long quantity) {
        return runnable -> {
            for (int i = 0; i < quantity; i++) {
                runnable.run();
            }
        };
    }

    public static void printExecutionTime(Runnable task) {
        System.err.println(executionTime(task));
    }

    public static long executionTime(Runnable task) {
        final long start = System.currentTimeMillis();
        task.run();
        return System.currentTimeMillis() - start;
    }
}
