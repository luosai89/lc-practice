package LC_Easy;

import Resource.ListNode;

/**
 * 5/11/2021 https://leetcode.com/problems/delete-node-in-a-linked-list/
 */

public class E_0237_Delete_Node_in_a_Linked_List {
    public void deleteNode(ListNode node) {
        // because not tail node, node.next is non-null
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
