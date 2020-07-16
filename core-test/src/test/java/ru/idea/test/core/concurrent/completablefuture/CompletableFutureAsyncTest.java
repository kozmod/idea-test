package ru.idea.test.core.concurrent.completablefuture;

import org.junit.Test;
import ru.idea.test.utils.ConcurrentUtils;
import ru.idea.test.utils.TestStringUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;

public class CompletableFutureAsyncTest {

    private static final int SLEEPING_TIME = 5;

    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldRunAsync() throws ExecutionException, InterruptedException {
        final Thread mainThread = Thread.currentThread();
        final String someString = TestStringUtils.randomAlphabeticString(10);
        final AtomicReference<String> res = new AtomicReference<>();
        assertNull(res.get());

        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                    assertNotEquals(mainThread, Thread.currentThread());
                    ConcurrentUtils.sleep(SLEEPING_TIME);
                    res.set(someString);
                }
        );
        assertNull(cf.get()); //blocking wile cf execute
        assertEquals(someString, res.get());
    }

    @Test
    public void shouldRunAsyncWithCallback() throws ExecutionException, InterruptedException {
        final Thread mainThread = Thread.currentThread();
        final String someString = TestStringUtils.randomAlphabeticString(10);
        final AtomicReference<String> res = new AtomicReference<>();
        final AtomicReference<Thread> cfThread = new AtomicReference<>();
        assertNull(res.get());
        assertNull(cfThread.get());

        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                    assertNotEquals(mainThread, Thread.currentThread());
                    ConcurrentUtils.sleep(SLEEPING_TIME);
                    res.set(someString);
                    cfThread.set(Thread.currentThread());
                }
        );
        cf = cf.thenAccept(object -> {
            assertNotEquals(mainThread, Thread.currentThread());
            assertEquals(cfThread.get(), Thread.currentThread());
            assertNull(object);
        });

        CompletableFuture<String> cfString = cf.thenApply(voidObj -> someString);
        assertNull(cf.get()); //blocking wile cf execute
        assertEquals(someString, res.get());
        assertNotNull(someString, cfString.get());//blocking wile cfString execute
    }

    @Test
    public void shouldSupplyAsync() throws ExecutionException, InterruptedException {
        final String res = TestStringUtils.randomAlphabeticString(10);
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            ConcurrentUtils.sleep(SLEEPING_TIME);
            return res;
        });
        assertEquals(res, cf.get()); // blocking .get()
    }

    @Test
    public void shouldSupplyAsyncWithCallback() throws ExecutionException, InterruptedException {
        final Thread mainThread = Thread.currentThread();
        final String res = TestStringUtils.randomAlphabeticString(10);
        final AtomicReference<Thread> cfThread = new AtomicReference<>();
        assertNull(cfThread.get());

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            assertNotEquals(mainThread, Thread.currentThread());
            cfThread.set(Thread.currentThread());
            ConcurrentUtils.sleep(SLEEPING_TIME);
            return res;
        });
        cf = cf.thenApply(object -> {
            assertNotEquals(mainThread, Thread.currentThread());
            assertEquals(cfThread.get(), Thread.currentThread());
            assertEquals(res, object);
            return object;
        });
        assertEquals(res, cf.get()); // blocking .get()
    }
}
