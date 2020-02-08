package ru.idea.test.core.algoritm.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZigZagConversion {

    @Test
    public void shouldEx() {
        String s1 = "PAYPALISHIRING";
        int n = 3;
        assertEquals("PAHNAPLSIIGYIR", convert(s1, n));

        s1 = "AB";
        n = 1;
        assertEquals("AB", convert(s1, n));
    }

    public String convert(String s, int numRows) {
        if (numRows < 2 || s.length() <= numRows) {
            return s;
        }
        char[] res = new char[s.length()];
        int index = 0;
        for (int i = 0; i < numRows; i++) {
            res[index++] = s.charAt(i);
            int tmp = i;
            while (tmp < s.length()) {
                tmp += 2 * (numRows - 1);
                if (i != 0 && i != numRows - 1 && tmp - 2 * i < s.length()) {
                    res[index++] = s.charAt(tmp - 2 * i);
                }
                if (tmp < s.length()) {
                    res[index++] = s.charAt(tmp);
                }
            }
        }
        return new String(res);
    }
}
