package ru.idea.test.core.generic;

import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class GenericTest {

    @Test
    public void should() {
        List<Integer> ints = new ArrayList<>();
        Class<? extends List> k = ints.getClass();
        assert k == ArrayList.class;

    }

    static <T extends Printer & Scaner> void doThx(List<T> list){
        list.forEach(ps ->{
            ps.scan("aaa");
            ps.print();
        });

    }

    static class PrSc implements Printer, Scaner{

        private List<String> scannedData = new ArrayList<>();

        @Override
        public void print() {
            scannedData.forEach(System.out::println);
        }

        @Override
        public String scan(String value) {
            scannedData.add(Objects.requireNonNull(value));
            return value;
        }
    }


    interface Printer{
         void print();
    }

    interface Scaner{
        String scan(String value);
    }

}
