package ru.idea.test.core.stream;

import org.junit.Test;

import java.util.List;
import java.util.Spliterator;

public class SpliteratorTest {

    @Test
    public void shouldSplit() {
        List<String> strings = List.of("A", "B", "C");

        Spliterator<String> s1 = strings.spliterator();
        Spliterator<String> s2 = s1.trySplit();

        s1.forEachRemaining(System.out::println);
        System.out.println("========");
        s2.forEachRemaining(System.out::println);
    }
}
