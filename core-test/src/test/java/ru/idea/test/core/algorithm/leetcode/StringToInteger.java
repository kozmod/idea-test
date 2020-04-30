package ru.idea.test.core.algorithm.leetcode;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
 * Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible,
 * and interprets them as a numerical value.
 * <p>
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 * <p>
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 * <p>
 * If no valid conversion could be performed, a zero value is returned.
 * <p>
 * Note:
 * <p>
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
 * If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * <p>
 * Example 1:
 * <p>
 * Input: "42"
 * Output: 42
 * <p>
 * Example 3:
 * <p>
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 * <p>
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 * digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 * <p>
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 * Thefore INT_MIN (−231) is returned.
 */

//todo: need to finish
public class StringToInteger {
    @Test
    public void shouldPrintInt() {
        assertEquals(-88827, myAtoi("    -88827   5655  U"));
        assertEquals(0, myAtoi(" +0 123"));
        assertEquals(0, myAtoi("-  333"));
        assertEquals(1, myAtoi("  +1   "));
        assertEquals(Integer.MAX_VALUE, myAtoi("20000000000000000000"));
        assertEquals(123, myAtoi("000123"));
        assertEquals(-43, myAtoi("    -43"));
        assertEquals(0, myAtoi("-+1"));
        assertEquals(0, myAtoi("+-2"));
        assertEquals(-43, myAtoi(" -43-"));
        assertEquals(4193, myAtoi("4193 with words"));
        assertEquals(-2147483648, myAtoi("-91283472332"));
        assertEquals(0, myAtoi("-"));
        assertEquals(0, myAtoi("ask 9999"));
    }

    public int myAtoi(String str) {
        StringBuilder sb = new StringBuilder();
        boolean isAppend = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ' ') {
                if (isAppend) {
                    break;
                }
                continue;
            }
            if (ch == '+') {
                if (sb.length() > 0 || (i + 1 > str.length() - 1) || !isDigit(str.charAt(i + 1))) {
                    break;
                }
                continue;
            }
            if (isDigit(ch)) {
                sb.append(ch);
                isAppend = true;
            } else if (ch == '-') {
                if (sb.length() == 0) {
                    if ((i + 1 > str.length() - 1) || !isDigit(str.charAt(i + 1))) {
                        return 0;
                    }
                    sb.append(ch);
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return toRes(sb.toString());
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private int toRes(String s) {
        if (s.length() == 0 || (s.length() == 1 && s.charAt(0) == '-')) {
            return 0;
        }
        BigInteger res = new BigInteger(s);
        if (res.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) <= -1) {
            return Integer.MIN_VALUE;
        }
        if (res.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) >= 1) {
            return Integer.MAX_VALUE;
        }
        return res.intValue();
    }
}
