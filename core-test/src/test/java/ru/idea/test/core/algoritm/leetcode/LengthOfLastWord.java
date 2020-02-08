package ru.idea.test.core.algoritm.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word (last word means the last appearing word if we loop from left to right) in the string.
 *
 * If the last word does not exist, return 0.
 *
 * Note: A word is defined as a maximal substring consisting of non-space characters only.
 *
 * Example:
 *
 * Input: "Hello World"
 * Output: 5
 */
public class LengthOfLastWord {

    @Test
    public void shouldEx() {
        assertEquals(5,lengthOfLastWord("Hello World"));
        assertEquals(5,lengthOfLastWord("Hello World "));
        assertEquals(0,lengthOfLastWord(""));
        assertEquals(0,lengthOfLastWord("     "));

    }

    private static final char SPACE = ' ';

    public int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int length = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            char current = chars[i];
            if(current == SPACE && length == 0){
                continue;
            } else if(current == SPACE){
                break;
            }
            length++;
        }
        return length;
    }

}
