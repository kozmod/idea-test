package ru.idea.test.core.algoritm.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * <p>
 * Example:
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * <p>
 * Given n will always be valid.
 */
public class RemoveNthNodeFromEndOfList {

    @Test
    public void shouldExecute() {
        ListNode ln = ListNode.of(1, 2, 3, 4, 5);
        int n = 2;
        assertEquals("[1,2,3,5]", toString(removeNthFromEnd(ln, n)));

        ln = ListNode.of(1, 2);
        n = 1;
        assertEquals("[1]", toString(removeNthFromEnd(ln, n)));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> nodes = new HashMap<>();
        int quantity = addToMap(nodes, head, 1);
        int delReq = quantity - n + 1;
        nodes.remove(delReq);

        ListNode res = null;
        ListNode last = null;
        for (int i = 1; i <= quantity; i++) {
            if (i == delReq) {
                continue;
            }
            if (res == null) {
                last = res = nodes.get(i);
                continue;
            }
            last.next = nodes.get(i);
            last = last.next;
        }
        return res;
    }

    private static int addToMap(Map<Integer, ListNode> nodes, ListNode fn, int num) {
        nodes.put(num, fn);
        if (fn.next != null) {
            ListNode tmp = fn.next;
            fn.next = null;
            return addToMap(nodes, tmp, num + 1);
        }
        return num;
    }

    @Test
    public void shouldExecute_2() {
        ListNode ln = ListNode.of(1, 2, 3, 4, 5);
        int n = 2;
        assertEquals("[1,2,3,5]", toString(removeNthFromEnd_2(ln, n)));

        ln = ListNode.of(1, 2);
        n = 1;
        assertEquals("[1]", toString(removeNthFromEnd_2(ln, n)));
    }

    public ListNode removeNthFromEnd_2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
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