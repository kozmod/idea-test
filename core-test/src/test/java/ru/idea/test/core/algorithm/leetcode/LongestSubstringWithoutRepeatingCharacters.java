package ru.idea.test.core.algorithm.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    @Test
    public void shouldExecute() {
        assertEquals(3, lengthOfLongestSubstring("dvdf"));
        assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, lengthOfLongestSubstring(" "));
        assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }

    private static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        Collection<Character> chars = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (chars.contains(c)) {
                if (maxLength < chars.size()) {
                    maxLength = chars.size();
                }
                removeBefore(chars, c);
            }
            chars.add(c);
        }
        return Math.max(maxLength, chars.size());
    }

    private static void removeBefore(Collection<Character> collection, char c) {
        AtomicBoolean lastRemove = new AtomicBoolean(true);
        for (Iterator<Character> it = collection.iterator(); it.hasNext(); ) {
            final Character ch = it.next();
            if (lastRemove.get() && !ch.equals(c)) {
                it.remove();
            }
            if (lastRemove.get() && ch.equals(c)) {
                it.remove();
                lastRemove.set(false);
            }
        }
    }
}
