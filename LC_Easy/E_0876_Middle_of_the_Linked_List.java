package LC_Easy;

import Resource.ListNode;

public class E_0876_Middle_of_the_Linked_List {
    /**
     * Lee215 fast and slow pointer. Because the fast pointer runs twice as fast, when fast pointer reaches the end,
     * the slow pointer reaches the middle
     */
    public ListNode middleNode_fastAndSlow(ListNode head) {
        ListNode t1 = head;
        ListNode t2 = head;
        while (t1.next != null  && t1.next.next != null) {
            t1 = t1.next.next;
            t2 = t2.next;
        }
        return t2;
    }

    /**
     * two-pass
     */
    public ListNode middleNode_twoPass(ListNode head) {
        int size = 0;
        ListNode t = head;
        while (t != null) {
            size++;
            t = t.next;
        }
        t = head;
        // TODO - not size / 2 + 1
        size /= 2;
        while (size > 0) {
            size--;
            t = t.next;
        }
        return t;
    }

}
