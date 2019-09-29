package ru.idea.test.core.function;

import org.junit.Test;

import java.util.function.Function;

public class FuctionalInterfaceTest {

    @Test
    public void functionChaneTest() {
        Function<Long, String> lsf = Object::toString;
        System.out.println(lsf.andThen(i -> i + "aaaa").andThen(i -> i.equalsIgnoreCase("aaa")).apply(13L));
    }
}
