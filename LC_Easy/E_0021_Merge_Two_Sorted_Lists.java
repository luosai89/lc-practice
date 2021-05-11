package LC_Easy;

/**
 * 5/11/2021 https://leetcode.com/problems/merge-two-sorted-lists/
 */

public class E_0021_Merge_Two_Sorted_Lists {
    /**
     * My original solution, time O(n), space O(1), non-destructive
     * 42 mins
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        // t0 reserved to track the last node of the new list
        ListNode newHead = null;
        ListNode t0 = null;
        // t1 & t2 to traverse the provided lists
        ListNode t1 = l1;
        ListNode t2 = l2;

        // while there's at least one node remaining in either provided list
        while (t1 != null || t2 != null) {
            // we will add the next node from l1, when l2 is exhausted or when l1 value is smaller
            // TODO - check both are not null before comparing vals
            if (t1 != null && t2 == null || (t1 != null && t2 != null && t1.val < t2.val)) {
                if (newHead == null) {
                    newHead = new ListNode(t1.val, null);
                    // TODO - t0 must track the last node of the new head, if they t0 tracks newHead when it's null,
                    //  the tracking must be re-established
                    t0 = newHead;
                }
                else {
                    t0.next = new ListNode(t1.val, null);
                    t0 = t0.next;
                }
                // TODO - this is a common step
                t1 = t1.next;
            }
            // otherwise, add the next node from l2
            else {
                if (newHead == null) {
                    newHead = new ListNode(t2.val, null);
                    t0 = newHead;
                }
                else {
                    t0.next = new ListNode(t2.val, null);
                    t0 = t0.next;
                }
                t2 = t2.next;
            }
        }

        // both lists must both be exhausted here, or they'd be still in the while loop
        return newHead;
    }

    /**
     * Same spirit as my original solution but better
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode l0 = new ListNode(0);
        ListNode t0 = l0;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                t0.next = l1;
                l1 = l1.next;
            } else {
                t0.next = l2;
                l2 = l2.next;
            }
            t0 = t0.next;
        }
        if (l1 == null) t0.next = l2;
        else t0.next = l1;
        return l0.next;
    }

    /**
     * Recursive, though not recommended
     */
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            // TODO this is very smart, you don't need to "add the value"
            l1.next = mergeTwoLists3(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists3(l1, l2.next);
            return l2;
        }
    }
}
