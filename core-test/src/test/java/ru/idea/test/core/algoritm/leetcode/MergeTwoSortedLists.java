package ru.idea.test.core.algoritm.leetcode;

import org.junit.Test;

import java.util.StringJoiner;

//TODO: need finish
public class MergeTwoSortedLists {

    @Test
    public void shouldEx() {

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode currentL1;
        ListNode currentL2;
        ListNode res;
        if (l1.val < l2.val) {
            currentL1 = l2;
            currentL2 = l1;
        } else {
            currentL1 = l1;
            currentL2 = l2;
        }
        ListNode currentRes = res = new ListNode(currentL1.val);
        currentL1 = currentL1.next;
        while (currentL1 != null) {
            if (currentL1.val < currentL2.val) {
                currentRes.next = new ListNode(currentL1.val);
                currentL1 = currentL1.next;
            } else if (currentL1.val == currentL2.val) {
                currentRes.next = new ListNode(currentL1.val);
                currentL1 = currentL1.next;
                currentRes.next = new ListNode(currentL2.val);
                currentL2 = currentL2.next;
            } else {

            }
        }
        return res;
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        private static ListNode of(int... ints) {
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

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
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
}
