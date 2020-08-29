package ru.idea.test.lib.mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.HashMap;
import java.util.Map;

public class BehaviorTest {

    private final static String EXCEPTION_MSG = "custom exception";
    private final static String A = "A", B = "B";
    private final static Map<String, String> argsReturnVal = new HashMap<>();

    static {
        argsReturnVal.put(A, B);
        argsReturnVal.put(B, A);
    }

    private static final Answer<String> throwAnswer = a ->
            argsReturnVal.computeIfAbsent(
                    a.getArgument(0),
                    s -> {
                        throw new IllegalArgumentException(EXCEPTION_MSG);
                    }
            );

    @Mock
    private Checker<String> checker;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(checker.check(Mockito.anyString())).thenAnswer(throwAnswer);
    }

    @Test
    public void testThrow() {
        Assert.assertEquals(B, checker.check(A));
        Assert.assertEquals(A, checker.check(B));
        try {
            checker.check("X-X-X");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals(EXCEPTION_MSG, ex.getMessage());
        }
    }

    private interface Checker<T> {
        String check(T in);
    }
}
