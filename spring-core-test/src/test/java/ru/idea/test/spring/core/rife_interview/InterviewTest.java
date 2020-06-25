package ru.idea.test.spring.core.rife_interview;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigInteger;

public class InterviewTest {

    @Test
    public void shouldLoadAnyConfigAndGetFoo() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnyInterviewConfig.class);
    }

    public class TestClass extends Thread {
        @Override
        public void run() {
            System.out.println("run");
            throw new RuntimeException();
        }

    }

    @Test
    public void name2() {
        try {
            new TestClass().start();
            System.out.println("main");
        } catch (RuntimeException e) {
            System.out.println("catch");
        }
    }

    @Test
    public void name() {
        BigInteger a = BigInteger.valueOf(239);
        a.add(BigInteger.valueOf(239));
        BigInteger b = BigInteger.valueOf(42);
        b.subtract(BigInteger.valueOf(42));
        System.out.println(a.divide(b));
    }
}
