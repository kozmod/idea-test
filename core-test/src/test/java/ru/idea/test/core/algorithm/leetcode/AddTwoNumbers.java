package ru.idea.test.core.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.StringJoiner;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {

    @Test
    public void shouldExecute() {
        ListNode lnA = listNode(2, 4, 3);
        ListNode lnB = listNode(5, 6, 4);
        Assert.assertEquals(
                "[7,0,8]",
                toString(addTwoNumbers(lnA, lnB))
        );

        lnA = listNode(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
        lnB = listNode(5, 6, 4);
        Assert.assertEquals(
                "[6,6,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]",
                toString(addTwoNumbers(lnA, lnB))
        );
    }

    private static ListNode listNode(int... ints) {
        ListNode ln = null;
        ListNode last = null;
        for (int i = 0; i < ints.length; i++) {
            if (ln == null) {
                last = ln = new ListNode(ints[i]);
            } else {
                last.next = new ListNode(ints[i]);
                last = last.next;
            }
        }
        return ln;
    }

    private static String toString(ListNode ln) {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        ListNode current = ln;
        while (current != null) {
            sj.add(Integer.toString(current.val));
            current = current.next;
        }
        return sj.toString();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return toLn(toBI(l1, 0).add(toBI(l2, 0)));
    }

    static BigInteger toBI(ListNode listNode, int deep) {
        BigInteger val = BigInteger.valueOf(listNode.val).multiply(
                BigInteger.valueOf(10).pow(deep)
        );
        if (listNode.next == null) {
            return val;
        }
        BigInteger res = toBI(listNode.next, deep + 1);
        return val.add(res);
    }

    static ListNode toLn(BigInteger res) {
        String temp = res.toString();
        ListNode ln = null;
        ListNode last = null;
        for (int i = temp.length() - 1; i >= 0; i--) {
            if (ln == null) {
                ln = new ListNode(temp.charAt(i) - '0');
                last = ln;
            } else {
                last.next = new ListNode(temp.charAt(i) - '0');
                last = last.next;
            }
        }
        return ln;
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    private static final class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

}
