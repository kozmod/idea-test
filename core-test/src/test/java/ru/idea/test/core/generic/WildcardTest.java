package ru.idea.test.core.generic;

import org.junit.Test;

import java.util.function.Function;

public class WildcardTest {

    @Test
    public void name() throws Exception {
        Function<String, ? extends A<? extends Number>> f1 = s -> {
            new B<>().apply("a");
            return new B<>();
        };
        Function<String, A<? extends Number>> f2 = s -> {
            new B<>().apply("a");
            return new B<>();
        };

        Function<? extends A<? extends Number>, String> f3  = a -> {
            return "";
        };

        Function<A<? extends Number>, String> f4  = a -> {
            return "";
        };

        Number n = 1;
        A<? extends Number> a1 = f1.apply("A");
        A<? extends Number> a2 = f2.apply("A");
//        a1.apply(n);
//        f3.apply(f1.apply("A"));
//        f3.apply(f2.apply("A"));
        f4.apply(f1.apply("A"));
        f4.apply(f2.apply("A"));

        try{
            throw new IllegalArgumentException();
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    static <T extends A<? extends Number>> void apply(T v){ }


    static class B<T> extends A<T>{ }

    static class A<T> {

        void apply(T t){
        }
    }
}
