package ru.idea.test.core.object;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

@Ignore
public class GCFinalizationTest {

    private static class BigObject {
        char[] tmp = new char[10_000_000];
    }

    private static class LongFinalize {
        protected void finalize() throws Throwable {
            System.out.println("LongFinalize finalizer");
            TimeUnit.SECONDS.sleep(10);
        }
    }

    @Test
    public void shouldNormallyCollectByGC_withBigObject() {
        startCreatingObjects(BigObject::new);
    }

    @Test
    public void shouldSlowCollectByGC_withLongFinalize() {
        startCreatingObjects(LongFinalize::new);
    }

    @Test
    public void shouldSlowExecuteFinalize() {
        startCreatingObjects(BigObject::new, LongFinalize::new);
    }

    @Test
    public void shouldNormallyCollectByGC__whenBigObject_HasEmpty_finalize() {
        startCreatingObjects(
                () -> new BigObject() {
                    protected void finalize() {
                    }
                },
                LongFinalize::new
        );
    }

    @Test
    public void shouldTrowOutOfMemoryError_whenBigObject_HasNotEmpty_finalize() {
        try {
            new LongFinalize();
            startCreatingObjects(
                    () -> new BigObject() {
                        protected void finalize() {
                            tmp[0] = 1;
                        }
                    }
            );
        } catch (Error error) {
            Assert.assertEquals(OutOfMemoryError.class, error.getClass());
            error.printStackTrace();
        }
    }

    private static void startCreatingObjects(Supplier<?>... suppliers) {
        startCreatingObjects(() -> false, suppliers);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private static void startCreatingObjects(BooleanSupplier runFinalizationSupplier, Supplier<?>... suppliers) {
        int i = 0;
        while (true) {
            for (Supplier<?> objectSupplier : suppliers) {
                Object o = objectSupplier.get();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i++ % 100 == 0) {
                System.out.println("Total: " + Runtime.getRuntime().totalMemory() + "; free: " + Runtime.getRuntime().freeMemory());
            }
            if (runFinalizationSupplier.getAsBoolean()) {
                System.runFinalization(); // create "SecondaryFinalizer" - thread for finalize methods queue
            }
        }
    }
}
