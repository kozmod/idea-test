package ru.idea.test.core.collections.stream;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OperationOrderStreamTest {

    @Test
    public void shouldPrintInConsistent_1() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void shouldPrintInConsistent_2() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
    }

    @Test
    public void shouldPrintInConsistent_3() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter_1: " + s);
                    return true;
                })
                .filter(s -> {
                    System.out.println("filter_2: " + s);
                    return s.startsWith("A");
                })
                .filter(s -> {
                    System.out.println("filter_3: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }


    @Test
    public void shouldPrintInConsistent_4() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }


    @Test
    public void shouldPrintInConsistent_5_SORT() {
        Stream.of("d2", "a2", "b1", "b3", "c", "a3")
                .filter(s -> {
                    System.out.println("filter_1: " + s);
                    return true;
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter_2: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    @Test
    public void shouldPrintInConsistent_6_flatMap() {
        List<List<String>> stringsLists = List.of(
                List.of("a1", "b1", "c1"),
                List.of(),
                List.of("a2", "b2", "c2"),
                List.of()
        );
        stringsLists.stream()
                .filter(l -> {
                    System.out.println("filter_1: " + l);
                    return !l.isEmpty();
                })
                .flatMap(l -> {
                    System.out.println("flatMap: " + l);
                    return l.stream();
                })
                .filter(s -> {
                    System.out.println("filter_2: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }


    /**
     * 'distinct()' on stream from set not execute, because Set-SPLITERATOR has DISTINCT-characteristic
     */
    @Test
    public void should_NotExecute_DISTINCT_onSetStream() {
        Set.of("a", "b", "c")
                .stream()
                .filter(i -> {
                    System.out.println("filter_1: " + i);
                    return true;
                })
                .distinct() // distinct not execute,
                .filter(i -> {
                    System.out.println("filter_3: " + i);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    @Test
    @Ignore(" Block thread !!! ")
    public void shouldBlockIndistinctMethod() {
        IntStream.iterate(0, i -> (i + 1) % 2) //0,1,0,1,0,1 .....
                .distinct()//0,1
                .filter(i -> {
                    System.out.println("filter_2: " + i);
                    return true;
                })
                .limit(10) //1=0,2=1,3=........
                .filter(i -> {
                    System.out.println("filter_3: " + i);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }
}
