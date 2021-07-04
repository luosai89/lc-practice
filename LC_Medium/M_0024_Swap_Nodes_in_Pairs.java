package LC_Medium;

import Resource.ListNode;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * #1 - 06/10/2021
 */
public class M_0024_Swap_Nodes_in_Pairs {

    public ListNode swapPairs(ListNode head) {
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode t = newHead;
        while (t.next != null && t.next.next != null) {
            ListNode temp = t.next.next;
            t.next.next = temp.next;
            temp.next = t.next;
            t.next = temp;
            t = t.next.next;
        }
        return newHead.next;
    }
}
