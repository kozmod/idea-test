package ru.idea.test.core;

import java.util.concurrent.TimeUnit;

public final class ConcurrentUtils {

    private ConcurrentUtils() {
    }

    public static Thread startNewThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
        return thread;
    }

    public static Runnable sleepingRunnable(int sleepingSeconds, Runnable runnable) {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(sleepingSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runnable.run();
        };
    }
}
