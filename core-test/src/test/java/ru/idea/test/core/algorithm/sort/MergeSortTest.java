package ru.idea.test.core.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static ru.idea.test.core.algorithm.AlgorithmUtils.newShuffledArray;

public class MergeSortTest {

    @Test
    public void positiveTest() {
        int[] expected = {1, 2, 3, 4, 5, 6, 7};
        int[] actual = newShuffledArray(expected);

        MergeSortTest.mergeSort(actual, actual.length);
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(actual));
    }

    public static void mergeSort(int[] array, int length) {
        if (length < 2) {
            return;
        }
        final var mid = length / 2;
        final var left = new int[mid];
        final var right = new int[length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, length - mid);

        mergeSort(left, mid);
        mergeSort(right, length - mid);

        merge(array, left, right, mid, length - mid);
    }

    public static void merge(int[] source, int[] left, int[] mid, int leftSize, int rightSize) {
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if (left[i] <= mid[j])
                source[k++] = left[i++];
            else
                source[k++] = mid[j++];
        }
        while (i < leftSize) source[k++] = left[i++];

        while (j < rightSize) source[k++] = mid[j++];
    }
}
