package ru.idea.test.core.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 * <p>
 * Example 1:
 * <p>
 * Input: 121
 * Output: true
 * Example 2:
 * <p>
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 * <p>
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 * Follow up:
 * <p>
 * Could you solve it without converting the integer to a string?
 */
public class PalindromeNumber {

    @Test
    public void shouldExNotUseString() {
        assertTrue(isPalindrome(0));
        assertTrue(isPalindrome(1));
        assertTrue(isPalindrome(121));
        assertTrue(isPalindrome(1221));
        assertTrue(isPalindrome(12221));

        assertFalse(isPalindrome(-121));
        assertFalse(isPalindrome(12231));
        assertFalse(isPalindrome(12233331));
    }

    public boolean isPalindrome(int x) {
        return x >= 0 && isEqual(x, (int) (Math.log10(x) + 1));
    }

    boolean isEqual(int num, int len) {
        if(len < 1){
            return true;
        }
        int hDivisor = (int)Math.pow(10, len -1);
        int tmpR = num % 10;
        int tl = num % hDivisor;
        int tmpL = (num - tl)/hDivisor;
        if (tmpR != tmpL) {
            return false;
        }
        num = (num - (hDivisor * tmpL))/10;
        return isEqual(num, len - 2);
    }

    //Could you solve it without converting the integer to a string? -> Y
    @Test
    public void shouldUseString() {
        assertTrue(isPalindrome_2(0));
        assertTrue(isPalindrome_2(1));
        assertTrue(isPalindrome_2(121));
        assertTrue(isPalindrome_2(1221));
        assertTrue(isPalindrome_2(12221));

        assertFalse(isPalindrome_2(-121));
        assertFalse(isPalindrome_2(12231));
        assertFalse(isPalindrome_2(12233331));

    }

    public boolean isPalindrome_2(int x) {
        if(x < 0){
            return false;
        }
        char[] chars = Integer.toString(x).toCharArray();
        if(chars.length <= 1){
            return true;
        }
        for (int i = 0, j= chars.length - 1; i <= j; i++, j--) {
            if(chars[i] != chars[j]){
                return false;
            }
        }
        return true;
    }
}
