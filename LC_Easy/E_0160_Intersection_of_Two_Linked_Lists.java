package LC_Easy;

/**
 * 5/11/2021 https://leetcode.com/problems/intersection-of-two-linked-lists/
 */

public class E_0160_Intersection_of_Two_Linked_Lists {
    /**
     * assume list lenghts are a + c and b + c, after switching, total traversed length for both are a + b + 2c
     * so after a full traversal (list a to b, and b to a), if a and b didn't meet in between, there's no intersection
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    /**
     * align the rear end of the two lists
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        int al = getLength(headA), bl = getLength(headB);
        ListNode a = headA;
        ListNode b = headB;
        while (al > bl) {
            a = a.next;
            al--;
        }
        while (bl > al) {
            b = b.next;
            bl--;
        }
        while (a != b) {
            a = a.next;
            b = b.next;
        }
        return a;
    }

    private int getLength(ListNode list) {
        ListNode t = list;
        int length = 0;
        while (t != null) {
            length++;
            t = t.next;
        }
        return length;
    }
}

