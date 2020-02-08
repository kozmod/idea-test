package ru.idea.test.core.algoritm.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * <p>
 * Example 1:
 * <p>
 * Input: "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: "(]"
 * Output: false
 * Example 4:
 * <p>
 * Input: "([)]"
 * Output: false
 * Example 5:
 * <p>
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses {

    @Test
    public void shouldEx() {
        assertTrue(isValid("()"));
        assertTrue(isValid("()[]{}"));
        assertTrue(isValid("{[]}"));

        assertFalse(isValid("(]"));
        assertFalse(isValid("([)]"));

        System.out.println(BRACE_START);
        System.out.println(BRACE_END);
        System.out.println(CURLY_BRACE_START);
        System.out.println(CURLY_BRACE_END);
        System.out.println(SQUARE_BRACE_START);
        System.out.println(SQUARE_BRACE_END);
    }

    private static final int BRACE_START = '(';
    private static final int BRACE_END = ')';
    private static final int CURLY_BRACE_START = '{';
    private static final int CURLY_BRACE_END = '}';
    private static final int SQUARE_BRACE_START = '[';
    private static final int SQUARE_BRACE_END = ']';

    public boolean isValid(String s) {
        int counterB = 0;
        int counterCB = 0;
        int counterSB = 0;
        char previous = 0;
        for (char cc : s.toCharArray()) {
            switch (cc) {
                case BRACE_START:
                    counterB++;
                    break;
                case CURLY_BRACE_START:
                    counterCB++;
                    break;
                case SQUARE_BRACE_START:
                    counterSB++;
                    break;
                case BRACE_END:
                    counterB--;
                    break;
                case CURLY_BRACE_END:
                    counterCB--;
                    break;
                case SQUARE_BRACE_END:
                    counterSB--;
                    break;
            }
            if (counterB < 0 || counterCB < 0 || counterSB < 0) {
                return false;
            } else if (previous != 0 && previous != cc) {
                if (BRACE_START == cc + 1
                        || BRACE_END == cc - 1
                        || CURLY_BRACE_START == cc + 2
                        || CURLY_BRACE_END == cc - 2
                        || SQUARE_BRACE_START == cc + 2
                        || SQUARE_BRACE_END == cc + 2) {
                    continue;
                }
                return false;
            }
            previous = cc;
        }
        return counterB == 0 && counterCB == 0 && counterSB == 0;
    }
}
