package ru.idea.test.core.collections;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamTest {

    @SuppressWarnings("RedundantOperationOnEmptyContainer")
    @Test
    public void shouldNotExecuteFilter_WhenListIsEmpty() {
        List<String> strings = Collections.emptyList();
        List<String> res = strings
                .stream()
                .filter(soutPredicate(true))
                .collect(Collectors.toList());
    }

    @Test
    public void shouldExecuteFilter_WhenListNotEmpty() {
        List<String> strings = List.of("A","B","C");
        List<String> res = strings
                .stream()
                .filter(soutPredicate(true))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("SameParameterValue")
    private  static <T> Predicate<T> soutPredicate(boolean result){
        System.err.println("Create Predicate<T> from factory method 'soutPredicate'");
        return t -> {
            System.out.println("Test in " + t);
            return result;
        };
    }
}
