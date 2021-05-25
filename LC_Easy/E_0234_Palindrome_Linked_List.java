package LC_Easy;

/**
 * 5/12/2021 https://leetcode.com/problems/palindrome-linked-list/
 * This is the combination of finding mid point (876) and reversing linked list (206)
 * SO(1) method is destructive; non-destructive method would be recursive but SO(n)
 */
public class E_0234_Palindrome_Linked_List {

    public boolean isPalindrome_reverse2ndHalf_destructive(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = reverse(slow);
        while (slow != null) {
            if (slow.val != head.val) return false;
            slow = slow.next;
            head = head.next;
        }
        return true;
    }
    public ListNode reverse(ListNode node) {
        if (node == null || node.next == null) return node;
        ListNode newHead = null;
        while (node != null) {
            ListNode realNext = node.next;
            node.next = newHead;
            newHead = node;
            node = realNext;
        }
        return newHead;
    }

    // TODO - understand the recursive method
}
