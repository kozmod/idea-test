package ru.idea.test.utils;

import ru.idea.test.core.utils.CheckedRunnable;

import java.util.concurrent.TimeUnit;

public final class ConcurrentUtils {

    private ConcurrentUtils() {
    }

    public static Thread submit(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
        return thread;
    }

    public static Thread submitChecked(CheckedRunnable runnable) {
        Thread thread = new Thread(runnable.runnable());
        thread.start();
        return thread;
    }

    public static Runnable sleepingRunnable(int sleepingSeconds, CheckedRunnable runnable) {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(sleepingSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runnable.runnable().run();
        };
    }

    public static void sleep(int sleepingSeconds) {
        try {
            TimeUnit.SECONDS.sleep(sleepingSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
