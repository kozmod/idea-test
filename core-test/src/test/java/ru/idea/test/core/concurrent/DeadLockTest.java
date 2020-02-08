package ru.idea.test.core.concurrent;

import org.junit.Test;
import ru.idea.test.core.ConcurrentUtils;
import ru.idea.test.core.utils.CheckedRunnable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * jsp - вывод id процессов jvm
 * jstack id - выводит стек с потоками процесса
 * <p>
 * jconsole - UI для консоли
 */
public class DeadLockTest {

    @Test
    public void shouldDeadLock_useSynchronizedBlock() throws InterruptedException {
        Object r1 = new Object();
        Object r2 = new Object();
        Thread t1 = ConcurrentUtils.startNewThread(() -> tryDeadLock(r1, r2));
        Thread t2 = ConcurrentUtils.startNewThread(() -> tryDeadLock(r2, r1));

        t1.join();
        t2.join();
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    private static void tryDeadLock(Object res1, Object res2) {
        synchronized (res1) {
            CheckedRunnable.wrap(() -> TimeUnit.SECONDS.sleep(1)).run();
            synchronized (res2) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    private static void lock(Object res1, Object res2) {
        Lock l = new ReentrantLock();
        l.lock();
        try {
            CheckedRunnable.wrap(() -> TimeUnit.SECONDS.sleep(1)).run();
            System.out.println(Thread.currentThread().getName());
        } finally {
            l.unlock();
        }
    }

    private static void tryLock(Object res1, Object res2) {
        Lock l = new ReentrantLock();
        l.lock();
        try {
            CheckedRunnable.wrap(() -> TimeUnit.SECONDS.sleep(1)).run();
            System.out.println(Thread.currentThread().getName());
        } finally {
            l.unlock();
        }
    }
}
