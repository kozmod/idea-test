package ru.idea.test.core.algoritm.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class Implement_strStr {

    @Test
    public void shouldEx() {
        assertEquals(1, strStr("ppi", "pi"));
        assertEquals(9, strStr("mississippi", "pi"));
        assertEquals(0, strStr("aaa", "aaa"));
        assertEquals(2, strStr("hello", "ll"));
        assertEquals(-1, strStr("aaaa", "bba"));
        assertEquals(0, strStr("aaaa", ""));
        assertEquals(-1, strStr("", "xxx"));
        assertEquals(4, strStr("mississippi", "issip"));
        assertEquals(-1, strStr("mississippi", "issipi"));
    }

    public int strStr(String haystack, String needle) {
        int needleLth;
        if(haystack.equals(needle) || (needleLth = needle.length()) == 0 ){
            return 0;
        }
        int haystackLth = haystack.length();
        int res = -1;
        if(haystackLth == 0 || needleLth > haystackLth){
            return res;
        }
        char needleSch = needle.charAt(0);

        for (int i = 0; i < haystackLth; i++) {
            if(haystack.charAt(i) == needleSch){
                res = i;
                if(needleLth == 1){
                    return res;
                }
                if((i + needleLth) <= haystackLth){
                    for (int j = 1, k = i + 1; j < needleLth + 1; j++, k++) {
                        if(j == needleLth){
                            return res;
                        }
                        if(haystack.charAt(k) != needle.charAt(j)){
                            break;
                        }
                    }
                }
                res = -1;
            }
        }
        return res;
    }
}
