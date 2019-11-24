package ru.idea.test.core.bigdecimal;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {

    @Test
    public void shouldPrint() {
        BigDecimal bdA = new BigDecimal(123.99);
        BigDecimal bdB = BigDecimal.valueOf(123.99);
        System.out.println(bdA);
        System.out.println(bdB);
    }
}
