package LC_Easy;

import Resource.ListNode;

/**
 * 4/10/2021 https://leetcode.com/problems/remove-linked-list-elements/
 */
public class E_0203_Remove_Linked_List_Elements {
    public static ListNode removeElements(ListNode head, int val) {
        // TODO - pay attention to head node (and future head nodes) that are offending
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode p = head;
        while (p != null && p.next != null) {
            // TODO - you do not need to "advance" to the next node here, because you essentially have advanced
            if (p.next.val == val) p.next = p.next.next;
            // TODO - else is necessary here
            else p = p.next;
        }
        return head;
    }
    public static void main(String[] args) {
        ListNode node1 = new ListNode();
        node1.val = 1;
        ListNode node2 = new ListNode();
        node2.val = 2;
        ListNode node3 = new ListNode();
        node3.val = 2;
        ListNode node4 = new ListNode();
        node4.val = 1;
//        LC.ListNode node5 = new LC.ListNode();
//        node5.val = 4;
//        LC.ListNode node6 = new LC.ListNode();
//        node6.val = 5;
//        LC.ListNode node7 = new LC.ListNode();
//        node7.val = 6;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;
//        node5.next = node6;
//        node6.next = node7;
//        node7.next = null;
        ListNode result = removeElements(node1, 2);
        ListNode p = result;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }
}
