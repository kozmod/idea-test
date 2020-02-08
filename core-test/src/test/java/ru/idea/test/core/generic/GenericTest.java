package ru.idea.test.core.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;

public class GenericTest {

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "rawtypes"})
    @Test
    public void should() {
        List<Integer> ints = new ArrayList<>();
        Class<? extends List> k = ints.getClass();
        assertSame(k, ArrayList.class);
    }
}
