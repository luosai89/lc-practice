package LC_Medium;

import Resource.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * 7/12 (makeup 7/3)
 * Binary Tree Iterator
 * Solutions: 1) iterative, 2) recursive
 * Followup: TODO
 */
public class M_230_Kth_Smallest_Element_in_a_BST {
    // Iterative
    public int kthSmallest1(TreeNode root, int k) {
        // inorder traversal (left, root, right)
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        int n = 0;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            n++;
            if (n == k) return node.val;
            node = node.right;
        }
        return -1;
    }

    // Recursive

    public int kthSmallest2(TreeNode root, int k) {
        int[] ans = new int[1];

        // prev keeps count of progress towards k
        dfs(root, k, 0, ans);
        return ans[0];
    }

    public int dfs(TreeNode root, int k, int prev, int ans[]) {
        // if root is null, no additions, exit and return prev
        if (root == null) return prev;

        // current count = previous count + 1
        int cur = dfs(root.left, k, prev, ans) + 1;

        // if current count reaches k, we found our answer
        if (k == cur) ans[0] = root.val;

        // if current count has not reached k, keep adding on
        if (cur < k) return dfs(root.right, k, cur, ans);
        
        return cur;
    }
}
