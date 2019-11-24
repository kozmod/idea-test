package ru.idea.test.core.functional.style;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionalInterfaceTest {

    @Test
    public void functionChaneTest() {
        Function<Long, String> lsf = Object::toString;
        System.out.println(
                lsf.andThen(i -> i + "aaaa")
                        .andThen(i -> i.equalsIgnoreCase("aaa"))
                        .apply(13L)
        );
    }

    @Test
    public void shouldSameFromFactoryMethod_AsLambda() {
        Consumer<String> anyConsumerA = asLambdaPrintStringConsumer();
        Consumer<String> anyConsumerB = asLambdaPrintStringConsumer();


        Assert.assertSame(anyConsumerA, anyConsumerB);
    }

    @Test
    public void shouldNotSameFromFactoryMethod_AsMethodRef() {
        Consumer<String> anyConsumerA = asMethodRefPrintStringConsumer();
        Consumer<String> anyConsumerB = asMethodRefPrintStringConsumer();

        Assert.assertNotSame(anyConsumerA, anyConsumerB);
    }

    @Test
    public void shouldSameFromFactoryMethod_AsMethodRef() {
        Consumer<String> anyConsumerA = asMethodRefFromStaticMethodStringConsumer();
        Consumer<String> anyConsumerB = asMethodRefFromStaticMethodStringConsumer();

        Assert.assertSame(anyConsumerA, anyConsumerB);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void shouldNotSameFromLambda() {
        Consumer<String> anyConsumerA = s -> System.out.println(s);
        Consumer<String> anyConsumerB = s -> System.out.println(s);

        Assert.assertNotSame(anyConsumerA, anyConsumerB);
    }

    @SuppressWarnings("Convert2MethodRef")
    private Consumer<String> asLambdaPrintStringConsumer() {
        return x -> System.out.println(x);
    }

    private Consumer<String> asMethodRefPrintStringConsumer() {
        return System.out::println;
    }

    public static Consumer<String> asMethodRefFromStaticMethodStringConsumer() {
        return FunctionalInterfaceTest::print;
    }

    public static void print(String string) {
        System.out.println(string);
    }
}
