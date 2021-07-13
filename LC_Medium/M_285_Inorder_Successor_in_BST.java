package LC_Medium;

import Resource.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * (plus) https://baihuqian.github.io/2018-07-30-inorder-successor-in-bst/
 *
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val.
 *
 * Note 1: If the given node has no in-order successor in the tree, return null.
 * Note 2: It's guaranteed that the values of the tree are unique.
 *
 * Example 1: Input: root = [2,1,3], p = 1; Output: 2;
 * Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
 *   2
 *  / \
 * 1   3
 *
 * Example 2: Input: root = [5,3,6,2,4,null,null,1], p = 6; Output: null;
 * Explanation: There is no in-order successor of the current node, so the answer is null
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 *
 * 7/13
 * Binary Tree - Iterator - II
 */
public class M_285_Inorder_Successor_in_BST {
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        if (p == null) return null;
        TreeNode t = root;
        TreeNode succ = null;

        while (t != null) {
            // compare value to p to go left (if > p) or right (if < p)
            if (t.val > p.val) {
                succ = t; // an inoder successor will be the last node that has a greater value
                t = t.left;
            } else if (t.val < p.val) {
                t = t.right;
            } else {
                break; // we found p and t is pointing to it
            }
        }
        return succ;

        // 5. 1) when p is found, check if it has right pop the last node from the stack - where we last went left, not right
        //    2) check the popped node's right, if null, keep popping, otherwise return value of the next right
        // 6. if p is never found, return null
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(2, p, right);
        TreeNode result = inorderSuccessor(root, p);
        System.out.println(result == null ? null : result.val);

        TreeNode node3 = new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4));
        p = new TreeNode(6);
        root = new TreeNode(5, node3, p);
        result = inorderSuccessor(root, p);
        System.out.println(result == null ? null : result.val);
    }
}
