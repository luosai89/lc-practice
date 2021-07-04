package LC_Medium;

import Resource.ListNode;

/**
 * https://leetcode.com/problems/reorder-list/
 * 7/3*
 * LinkedList - combining find mid node, reverse list, merge two lists
 */
public class M_0143_Reorder_List {
    public void reorderList(ListNode head) {
        if (head == null) return;
        // find the mid point
        ListNode mid = findMidNode(head);
        // reverse the second half of the linkedlist
        ListNode l2 = reverseLinkedList(mid.next);
        // merge first and second half
        mid.next = null;
        ListNode l1 = head;
        while (l1 != null && l2 != null) {
            ListNode next = l1.next; // store the next of l1 (non-destructive)
            l1.next = l2; // replace l1's next with l2
            l2 = l2.next; // advance l2 to l2's next (destructive)
            l1.next.next = next; // link the original next after the inserted l2 element
            l1 = l1.next.next; // advance l1 to the original next (which has not been merged yet)
        }
    }

    private ListNode findMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next; // when fast.next is null, mid is at the middle point
        }
        return slow;
    }

    private ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null; // keep track of the reversed "previous" nodes
        ListNode t = head;
        while(t != null) {
            ListNode next = t.next; // store the original next (not yet reversed)
            t.next = prev; // make the current node's next to be the reversed "previous" nodes
            prev = t; // now the current node is also part of the reversed "previous" nodes
            t = next; // reset pointer to the remaining unreversed nodes (original next)
        }
        return prev;
    }
}
