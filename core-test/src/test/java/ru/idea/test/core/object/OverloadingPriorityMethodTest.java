package ru.idea.test.core.object;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OverloadingPriorityMethodTest {

    @SuppressWarnings("WrapperTypeMayBePrimitive")
    @Test
    public void name() {
        short shortValue = 1;
        Short boxShortValue = 1;
        assertEquals(2, apply(shortValue));
        assertEquals(2, apply(boxShortValue));

        int intValue = 1;
        Integer boxIntValue = 1;
        assertEquals(3, apply(intValue));
        assertEquals(4, apply(boxIntValue));
    }

    private static int apply(byte b){
        return 1;
    }

    private static int apply(short b){
        return 2;
    }

    private static int apply(long b){
        return 3;
    }

    private static int apply(Integer b){
        return 4;
    }

}
