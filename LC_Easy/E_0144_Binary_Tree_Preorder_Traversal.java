package LC_Easy;

import Resource.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * 7/10 (makeup 7/9)
 * Binary Tree
 * Basket Wang: https://www.youtube.com/watch?v=COBCEDPncus
 */
public class E_0144_Binary_Tree_Preorder_Traversal {

    // Recursive with helper
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        DFS(root, result);
        return result;
    }
    private void DFS(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        DFS(node.left, list);
        DFS(node.right, list);
    }

    // Iterative with stack
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                result.add(node.val);
                stack.push(node.right); // note we have to push right first, in order we always pop and add left first
                stack.push(node.left);
            }
        }
        return result;
    }
}
