package ru.idea.test.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    public static void printlnExecutionTime(Runnable task) {
        System.err.println(executionTime(task));
    }

    public static long executionTime(Runnable task) {
        final long start = System.currentTimeMillis();
        task.run();
        return System.currentTimeMillis() - start;
    }

    public static long fileLineQuantity(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            long lineNumber = 0;
            while (br.readLine() != null) {
                lineNumber++;
            }
            return lineNumber;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
