package ru.idea.test.core.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class GenericTest {

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "rawtypes"})
    @Test
    public void should_assert_class_with_generic() {
        List<Integer> ints = new ArrayList<>();
        Class<? extends List> k = ints.getClass();
        assertSame(k, ArrayList.class);
    }

    @SuppressWarnings({"unused", "UnnecessaryLocalVariable", "ConstantConditions"})
    @Test(expected = ArrayStoreException.class)
    public void should_throw_runtime_error_when_use_string_array_as_0bject_array() {
        String[] strings = {"a", "b", "c"};
        Object[] objects = strings;
        objects[0] = 777;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    public void wild_card_test() {
        Integer one = 1;
        Integer two = 2;
        Integer three = 2;

        List<Integer> ints = new ArrayList<>();
        ints.add(one);
        ints.add(two);
        List<? extends Number> nums = ints;
        //nums.add(3.14);                    // fixme can not write to <? extends ...>
        Number num = nums.get(0);
        assertEquals(one, num);

        List<? super Integer> nums2 = ints;
        //Integer x = nums2.get(0);          // fixme can not get element from <? super ...>, extend of "Object"
        nums2.add(three);
        Object o = nums2.get(2);
        assertEquals(three, o);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    public void wild_card_test_improved() {
        List<B> bs = new ArrayList<>();
        bs.add(new B());
        List<? extends A> exAs = bs;
        A a = exAs.get(0);
        //B b = exAs.get(0); // fixme can't get B

        List<? super B> supBs_b = bs;
        List<? super C> supBs_c = bs;

        supBs_b.add(new B());
        supBs_b.add(new C());

        //supBs_c.add(new B()); //fixme can't add B
        supBs_c.add(new C());
    }

    static class A {
    }

    static class B extends A {
    }

    static class C extends B {
    }
}
