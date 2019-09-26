package ru.idea.test.core.utils;

@FunctionalInterface
public interface CheckedRunnable {

    void run() throws Exception;

    static Runnable wrap(CheckedRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
