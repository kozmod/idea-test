package ru.idea.test.core.algoritm.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Note:
 *
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
 * For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 */

//TODO: leetcode test -> Time Limit Exceeded
public class DivideTwoIntegers {

    @Test
    public void shouldEx() {
        assertEquals(3, divide(10,3));
        assertEquals(-2, divide(7,-3));
        assertEquals(2147483647, divide(-2147483648,-1));
        assertEquals(-2147483648, divide(-2147483648,1));
        assertEquals(3, divide(-6,-2));
    }

    public int divide(int dividend, int divisor) {
        byte additional = 0;
        byte negative = 1;
        if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)){
            negative = -1;
        }
        if(dividend <= Integer.MIN_VALUE){
            dividend = Integer.MAX_VALUE;
            if(negative < 0){
                additional++;
            }
        } else {
            dividend = Math.abs(dividend);
        }
        if((divisor = Math.abs(divisor)) == 1){
            return (dividend + additional)* negative;
        }
        int res = 0;
        while (dividend >= divisor){
            dividend -= divisor;
            ++res;
        }
        return (res + additional) * negative;
    }
}
