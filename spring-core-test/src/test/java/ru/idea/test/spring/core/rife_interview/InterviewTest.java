package ru.idea.test.spring.core.rife_interview;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class InterviewTest {

    @Test
    public void shouldLoadInRightOrder() throws IOException {
        var old = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true);
        System.setOut(ps);
        try (baos; ps) {
            new AnnotationConfigApplicationContext(AnyInterviewConfig.class);
        }
        assertEquals("1234", baos.toString());
        System.setOut(old);
    }


    @Test
    public void shouldThrowException() {
        class ThrowExceptionThreadClass extends Thread {
            @Override
            public void run() {
                System.err.println("RUN");
                throw new RuntimeException("Uncatchable exception from another thread ");
            }
        }
        try {
            new ThrowExceptionThreadClass().start();
            System.err.println("MAIN");
        } catch (RuntimeException e) {
            System.err.println("CATCH");
        }
        System.err.println("END");
    }

    @Test
    public void shouldThrowException_2() {
        Thread t = new Thread(() -> {
            throw new RuntimeException("re");
        });
        try {
            t.start();
            System.out.println("MAIN");
        } catch (Exception ex) {
            System.out.println("CATCH");
        }
        System.out.println("END");
    }

    @Test
    public void shouldThrowException_3() {
        Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread th, Throwable ex) {
                System.err.println("Uncaught exception: " + ex);
            }
        };
        Thread t = new Thread(() -> {
            throw new RuntimeException("re");
        });
        t.setUncaughtExceptionHandler(h);
        try {
            t.start();
            System.out.println("MAIN");
        } catch (Exception ex) {
            System.out.println("CATCH");
        }
        System.out.println("END");
    }



    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void shouldReturnWithRoundDown() {
        BigInteger a = BigInteger.valueOf(239);
        a.add(BigInteger.valueOf(239));
        BigInteger b = BigInteger.valueOf(42);
        b.subtract(BigInteger.valueOf(42));
        assertEquals(new BigInteger("5"), a.divide(b));
    }
}
