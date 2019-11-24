package ru.idea.test.core.concurrent;

import org.junit.Before;
import org.junit.Test;
import ru.idea.test.core.ConcurrentUtils;
import ru.idea.test.core.DecimalFormatFactory;
import ru.idea.test.core.TestUtils;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SuppressWarnings({"AccessStaticViaInstance", "NonAtomicOperationOnVolatileField"})
public final class VolatileTest {

    private static volatile int volatileVar;
    private AtomicInteger atomicVar = new AtomicInteger();

    private static void incrementVar() {
        volatileVar++;
    }

    private static synchronized void synchronizedIncrementVar() {
        volatileVar++;
    }

    @Before
    public void incrementVarZeroing() {
        this.volatileVar = 0;
    }


    @Test
    public void shouldIncrementVolatile() throws InterruptedException {
        final int iterationQuantity = 500_000;
        Thread t1 = ConcurrentUtils.startNewThread(() -> TestUtils.fori(iterationQuantity).accept(VolatileTest::incrementVar));
        Thread t2 = ConcurrentUtils.startNewThread(() -> TestUtils.fori(iterationQuantity).accept(VolatileTest::incrementVar));

        t1.join();
        t2.join();

        System.out.println(DecimalFormatFactory.defaultInteger().format(volatileVar));
        assertNotEquals(iterationQuantity * 2, volatileVar);
    }

    @Test
    public void shouldIncrementVolatile_userSynchronizedMethod() throws InterruptedException {
        final int iterationQuantity = 500_000;
        Thread t1 = ConcurrentUtils.startNewThread(() -> TestUtils.fori(iterationQuantity).accept(VolatileTest::synchronizedIncrementVar));
        Thread t2 = ConcurrentUtils.startNewThread(() -> TestUtils.fori(iterationQuantity).accept(VolatileTest::synchronizedIncrementVar));

        t1.join();
        t2.join();

        System.out.println(DecimalFormatFactory.defaultInteger().format(volatileVar));
        assertEquals(iterationQuantity * 2, volatileVar);
    }
}
