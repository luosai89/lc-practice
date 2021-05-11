package LC_Easy;

/**
 * 5/11/2021 https://leetcode.com/problems/merge-two-sorted-lists/
 */

public class E_0021_Merge_Two_Sorted_Lists {
    /**
     * Same spirit as my original solution but better
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
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
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            // TODO this is very smart, you don't need to "add the value"
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}
