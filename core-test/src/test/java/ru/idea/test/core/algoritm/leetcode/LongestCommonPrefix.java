package ru.idea.test.core.algoritm.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * Example 1:
 * <p>
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * <p>
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 * <p>
 * All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix {

    @Test
    public void shouldEx() {
        assertEquals("fl", longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        assertEquals("", longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        assertEquals("", longestCommonPrefix(new String[]{"dog", "racecar", ""}));
        assertEquals("", longestCommonPrefix(new String[]{}));
        assertEquals("", longestCommonPrefix(new String[]{""}));
        assertEquals("a", longestCommonPrefix(new String[]{"a"}));
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int index = 0; ; ) {
            Character c = null;
            for (int i = 0; i < strs.length; i++) {
                char[] chars = strs[i].toCharArray();
                if (chars.length <= index) {
                    return chars.length == 0 ? "" : sb.toString();
                }
                if (c == null) {
                    c = chars[index];
                    continue;
                }
                if (c != chars[index]) {
                    return sb.toString();
                }
            }
            sb.append(c);
            c = null;
            index++;
        }
    }
}
