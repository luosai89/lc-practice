package LC_Easy;

/**
 * 4/16/2021 https://leetcode.com/problems/reverse-linked-list/
 */
public class E206_Reversed_Linked_List {

    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseListIterative(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode nextHolder = head.next;
            head.next = newHead;
            newHead = head;
            head = nextHolder;
        }
        return newHead;
    }
}
