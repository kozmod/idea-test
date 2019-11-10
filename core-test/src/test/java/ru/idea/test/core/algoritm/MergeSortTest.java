package ru.idea.test.core.algoritm;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static ru.idea.test.core.algoritm.AlgorithmUtils.newShuffledArray;

public class MergeSortTest {

    @Test
    public void positiveTest() {
        int[] expected = {1, 2, 3, 4, 5, 6, 7};
        int[] actual = newShuffledArray(expected);

        MergeSortTest.mergeSort(actual, actual.length);
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(actual));
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        final var mid = n / 2;
        final var l = new int[mid];
        final var r = new int[n - mid];

        System.arraycopy(a, 0, l, 0, mid);
        System.arraycopy(a, mid, r, 0, n - mid);

        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        }
        while (i < left) a[k++] = l[i++];

        while (j < right) a[k++] = r[j++];
    }
}
