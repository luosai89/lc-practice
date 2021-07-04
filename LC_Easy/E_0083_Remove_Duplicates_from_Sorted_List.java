package LC_Easy;

import Resource.ListNode;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */

public class E_0083_Remove_Duplicates_from_Sorted_List {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode t = head;
        while (t != null && t.next != null) {
            if (t.next.val == t.val) {
                t.next = t.next.next;
            } else {
                t = t.next;
            }
        }
        return head;
    }
}
