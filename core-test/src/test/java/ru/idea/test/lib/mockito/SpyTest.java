package ru.idea.test.lib.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Objects;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpyTest {

    @Spy
    private final ClassToSpying classToSpying = new ClassToSpying();

    @Test
    public void shouldWorkWithoutOverriding() {
        var inA = "A";
        var inB = "B";
        var res = inA + inB;
        assertEquals(res, classToSpying.concat(inA, inB));
    }

    @Test
    public void shouldWorkWithOverriding() {
        var inA = "A";
        var inB = "B";
        var res = inA + inB;
        when(classToSpying.concat(inA, inA)).thenReturn(res);
        assertEquals(res, classToSpying.concat(inA, inB));
        assertEquals(res, classToSpying.concat(inA, inA));
        assertNotEquals(res, classToSpying.concat(inB, inA));
    }
}


class ClassToSpying {

    String concat(String inA, String inB) {
        Objects.requireNonNull(inA);
        Objects.requireNonNull(inB);
        return inA.concat(inB);
    }
}
