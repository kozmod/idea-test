package ru.idea.test.core.algoritm.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p>
 * You may assume no duplicates in the array.
 * ///////////////////////////////
 * Example 1:
 * <p>
 * Input: [1,3,5,6], 5
 * Output: 2
 * ///////////////////////////////
 * Example 2:
 * <p>
 * Input: [1,3,5,6], 2
 * Output: 1
 * ///////////////////////////////
 * Example 3:
 * <p>
 * Input: [1,3,5,6], 7
 * Output: 4
 * ///////////////////////////////
 * Example 4:
 * <p>
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class SearchInsertPosition {

    @Test
    public void shouldEx_Binary() {
        assertEquals(0, searchInsert(new int[]{1}, 1));
        assertEquals(2, searchInsert(new int[]{1, 3, 5, 6}, 5));
        assertEquals(0, searchInsert(new int[]{1, 3, 5, 6}, 0));
        assertEquals(1, searchInsert(new int[]{1, 3, 5, 6}, 2));
        assertEquals(4, searchInsert(new int[]{1, 3, 5, 6}, 7));
    }

    public int searchInsert(int[] nums, int target) {
        return binarySearchWithPredict(nums, target, 0, nums.length - 1);
    }

    public static int binarySearchWithPredict(int[] sortedArray, int key, int low, int high) {
        Integer index = null;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid] < key) {
                low = mid + 1;
                index = mid;
            } else if (sortedArray[mid] > key) {
                high = mid - 1;
                index = mid;
            } else if (sortedArray[mid] == key) {
                return mid;
            }
        }
        if (index != null && index == high) {
            return ++index;
        }
        return index == null ? 0 : index;
    }

    @Test
    public void shouldEx_Linearly() {
        assertEquals(0, searchInsertLinearly(new int[]{1}, 1));
        assertEquals(2, searchInsertLinearly(new int[]{1, 3, 5, 6}, 5));
        assertEquals(0, searchInsertLinearly(new int[]{1, 3, 5, 6}, 0));
        assertEquals(1, searchInsertLinearly(new int[]{1, 3, 5, 6}, 2));
        assertEquals(4, searchInsertLinearly(new int[]{1, 3, 5, 6}, 7));
    }

    public int searchInsertLinearly(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target || nums[i] > target) {
                return i;
            }
        }
        return nums.length;
    }
}
