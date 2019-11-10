package ru.idea.test.core.collections.map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.function.Function;

public class NavigableMapTest {

    @SuppressWarnings("FieldCanBeLocal")
    private LocalDateTime point;

    @Before
    public void cleanPoint(){
        point = null;
    }

    @Ignore
    @Test
    public void shouldReturn_HeadMap_AndTaleMap() {
        NavigableMap<LocalDateTime, String> map = new TreeMap<>(Comparator.comparing(Function.identity()));
        map.put(LocalDateTime.now(), "a");
        map.put(LocalDateTime.now().minusMinutes(1), "b");
        map.put(point = LocalDateTime.now().minusMinutes(2), "c");
        map.put(LocalDateTime.now().minusMinutes(3), "d");
        map.put(LocalDateTime.now().minusMinutes(4), "f");

        System.out.println("----  Map ---");
        System.out.println(map);
        System.out.println("---- Head ---");
        System.out.println(map.tailMap(point));
        System.out.println("---- Tail ---");
        System.out.println(map.headMap(point));
    }
}
