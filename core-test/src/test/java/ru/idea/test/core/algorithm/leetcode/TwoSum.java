package ru.idea.test.core.algorithm.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

/**
 * up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * <p>
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {

    @Test
    public void shouldFindIndexPair() {
        assertArrayEquals(new int[]{0, 1}, useMap(new int[]{2, 7, 11, 15}, 9));
        assertArrayEquals(new int[]{0, 3}, useMap(new int[]{0, 4, 3, 0}, 0));
    }

    private static int[] useMap(int[] nums, int target) {
        check(nums, target);

        Map<Integer, Integer> map = new HashMap<>(nums.length, 1.1f);
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (map.containsKey(current)) {
                return new int[]{map.get(current), i};
            } else {
                int res = target - current;
                map.put(res, i);

            }
        }
        return new int[]{-1, -1};
    }

    private static void check(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException();
        }
    }
}
