package ru.idea.test.core.collections.map;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class LinkedHashMapTest {

    @Test
    public void shouldDeleteLessUsingElement_InMap() {
        Map<Integer, String> map = new CacheMap<>(5);
        map.put(null, "0");
        map.put(0, null);
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");
        assertThat(map.keySet(), contains(1, 2, 3, 4, 5));

        map.get(1);
        map.get(2);
        map.get(3);
        map.get(4);
        assertThat(map.keySet(), contains(5, 1, 2, 3, 4));

        map.put(6, "F");
        assertThat(map.keySet(), contains(1, 2, 3, 4, 6));
    }

    @Test
    public void shouldDeleteLessUsingElement_InSet() {
        Set<String> set = Collections.newSetFromMap(new CacheMap<>(5));
        set.add(null);
        set.add("A");
        set.add("B");
        set.add("C");
        set.add("D");
        set.add("E");
        assertThat(set, contains("A", "B", "C", "D", "E"));

        set.add("F");
        assertThat(set, contains("B", "C", "D", "E", "F"));
    }

    private static final class CacheMap<K, V> extends LinkedHashMap<K, V> {

        private final int max;

        CacheMap(int initialCapacity) {
            super(initialCapacity, 1.1f, true);
            this.max = initialCapacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return this.size() > max;
        }
    }
}
