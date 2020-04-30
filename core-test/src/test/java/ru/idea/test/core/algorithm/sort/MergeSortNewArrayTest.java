package ru.idea.test.core.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static ru.idea.test.core.algorithm.AlgorithmUtils.newShuffledArray;

public class MergeSortNewArrayTest {

    @Test
    public void positiveTest() {
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] actual = newShuffledArray(expected);
        int[] res = mergeSort(actual);

        assertArrayEquals(expected, res);
        System.out.println(Arrays.toString(res));
    }

    private static int[] mergeSort(int[] array) {
        final var length = array.length;
        if (array.length < 2) {
            return array;
        }
        final var mid = length / 2;
        final var left = new int[mid];
        final var right = new int[length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, length - mid);

        final var resLeft = mergeSort(left);
        final var resRight = mergeSort(right);

        return merge(resLeft, resRight);
    }

    private static int[] merge(int[] left, int[] right) {
        var res = new int[left.length + right.length];
        var l = 0;
        var r = 0;
        var rs = 0;

        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
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
