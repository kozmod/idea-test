package ru.idea.test.core.algoritm.sort;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static ru.idea.test.core.algoritm.AlgorithmUtils.newShuffledArray;

public class MergeSortComparatorTest {

    @Test
    public void positiveTest() {
        String[] expected = {"A", "B", "C", "D", "E", "F", "J", "K"};
        String[] actual = newShuffledArray(expected);
        String[] res = mergeSort(actual, String::compareTo);

        assertArrayEquals(expected, res);
        System.out.println(Arrays.toString(res));
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] mergeSort(T[] array, Comparator<T> comparator) {
        final var length = array.length;
        if (array.length < 2) {
            return array;
        }
        final Class<?> entityClass = array[0].getClass();
        final var mid = length / 2;
        final T[] left = (T[]) Array.newInstance(entityClass, mid);
        final T[] right = (T[]) Array.newInstance(entityClass, length - mid);

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, length - mid);

        final var resLeft = mergeSort(left, comparator);
        final var resRight = mergeSort(right, comparator);

        return merge(resLeft, resRight, entityClass, comparator);
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] merge(T[] left, T[] right, Class<?> entityClass, Comparator<T> comparator) {
        var res = (T[]) Array.newInstance(entityClass, left.length + right.length);
        int l = 0, r = 0, rs = 0;
        while (l < left.length && r < right.length) {
            int cr = comparator.compare(left[l], right[r]);
            if (cr <= 0) {
                res[rs++] = left[l++];
            } else {
                res[rs++] = right[r++];
            }
        }
        while (l < left.length) {
            res[rs++] = left[l++];
        }
        while (r < right.length) {
            res[rs++] = right[r++];
        }
        return res;
    }
}
