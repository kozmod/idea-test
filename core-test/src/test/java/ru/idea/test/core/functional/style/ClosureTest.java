package ru.idea.test.core.functional.style;

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ClosureTest {

    @Test
    @Ignore
    public void shouldExClosure() {
        Function<Integer, Integer> pos = adder();
        Function<Integer, Integer> neg = adder();
        for (int i = 0; i < 10; i++) {
            System.out.println(
                    "pos: " + pos.apply(i) + ", neg: " + neg.apply(-2 * i)
            );
        }

    }

    private Function<Integer, Integer> adder() {
        final AtomicInteger sum = new AtomicInteger(0);
        return sum::addAndGet;
    }
}
