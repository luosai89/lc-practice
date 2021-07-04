package LC_Easy;

import Resource.ListNode;

/**
 * 5/12/21 LinkedList https://leetcode.com/problems/linked-list-cycle/
 * 1) fast/slow pointer
 * 2) use next to make markings
 */

public class E_0141_Linked_List_Cycle {
    public boolean hasCycle_fastAndSlow(ListNode head) {
        if (head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        // TODO - make sure fast/slow/head is not null
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // TODO - simply check address is the same
            if (fast == slow) return true;
        }
        return false;
    }

    public boolean hasCycle_nextMarking(ListNode head) {
        while (head != null) {
            ListNode storeNext = head.next; // store what's supposedly next
            if (storeNext == head) return true; // if what's supposedly next is head, we've visited this node
            head.next = head; // use next to mark visited (point next to head)
            head = storeNext; // now go to what's supposedly next
        }
        // destructed original list
        return false;
    }

}
