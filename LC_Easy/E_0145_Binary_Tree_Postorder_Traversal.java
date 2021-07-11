package LC_Easy;

import Resource.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 * 7/10* (makeup 7/8)
 * Binary Tree Traversal
 * Basket Wang: https://www.youtube.com/watch?v=COBCEDPncus
 * Iterative Summary: https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45551/Preorder-Inorder-and-Postorder-Iteratively-Summarization
 */
public class E_0145_Binary_Tree_Postorder_Traversal {
    // Recursive with helper
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        DFS(root, result);
        return result;
    }
    public void DFS(TreeNode node, List<Integer> list) {
        if (node == null) return;
        DFS(node.left, list);
        DFS(node.right, list);
        list.add(node.val);
    }

    // Iterative with ArrayDeque
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode t = root;
        while (t != null || !stack.isEmpty()) {
            if (t != null) {
                result.addFirst(t.val); // add later parent node value to the front, in effect reversing
                stack.push(t);
                t = t.right; // add later right node value to the front, before adding left node value
            } else {
                t = stack.pop();
                t = t.left; // add left node value the last
            }
        }
        return result;
    }
}
