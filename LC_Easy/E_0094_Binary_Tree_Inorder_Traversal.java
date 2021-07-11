package LC_Easy;

import Resource.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * 7/10
 * Basketwang explanation: https://www.youtube.com/watch?v=COBCEDPncus
 */
public class E_0094_Binary_Tree_Inorder_Traversal {

    // Recursive with helper method
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        BFS(root, result);
        return result;
    }
    private void BFS(TreeNode node, List<Integer> list) {
        if (node == null) return;
        BFS(node.left, list);
        list.add(node.val);
        BFS(node.right, list);
    }

    // Iterative with Stack
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode t = root;
        while (t != null || !stack.isEmpty()) {
            while (t != null) {
                stack.push(t);
                t = t.left;
            }
            t = stack.pop();
            result.add(t.val);
            t = t.right;
        }
        return result;
    }

}
