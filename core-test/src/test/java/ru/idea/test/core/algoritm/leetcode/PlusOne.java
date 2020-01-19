package ru.idea.test.core.algoritm.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * <p>
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 * <p>
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class PlusOne {

    @Test
    public void shEx() {
        int[] d = {1, 2, 3, 4};
        assertArrayEquals(new int[]{1, 2, 3, 5}, plusOne(d));

        d = new int[]{1, 2, 3, 9};
        assertArrayEquals(new int[]{1, 2, 4, 0}, plusOne(d));

        d = new int[]{9};
        assertArrayEquals(new int[]{1, 0}, plusOne(d));
    }

    public int[] plusOne(int[] digits) {
        return set(digits, digits.length - 1);
    }

    private int[] set(int[] d, int pos) {
        if (pos >= 0) {
            int tmp = d[pos] + 1;
            if (tmp % 10 == 0) {
                d[pos] = 0;
                return set(d, pos - 1);
            }
                d[pos] = tmp;
                return d;
        } else {
            int[] newArray = new int[d.length + 1];
            System.arraycopy(d, 0, newArray, 1, d.length);
            newArray[0] = 1;
            return newArray;
        }
    }
}
