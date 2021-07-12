package LC_Medium;

import Resource.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 7/11**
 * Binary tree construction
 * 贾考博 Recursive https://www.youtube.com/watch?v=6Xcz08RkRHE
 *
 */
public class M_0105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {

    /**
     * Recursive method with map cache (to look up root index)
     * and helper (identify root node, set left, set right, return null when reaching leaf node child)
     * [a, b, c], r, [x, y, z]  inorder
     * r, [a, b, c], [x, y, z]  preorder
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        // Map to track root index in inorder array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, inorder, 0, 0, inorder.length - 1, map);
    }

    private static TreeNode helper(int[] preorder, int[] inorder,
                            int pre_st, int in_st, int in_end,
                            Map<Integer, Integer> map) {
        // exit criteria
        if (pre_st > inorder.length - 1 || in_st > in_end) return null;

        // find the new root and construct the tree
        int root = preorder[pre_st];
        TreeNode node = new TreeNode(root);

        // set the tree left and tree right by recursively calling helper
        int i = map.get(root);
        node.left = helper(preorder,inorder, pre_st + 1, in_st, i - 1, map);
        node.right = helper(preorder, inorder, pre_st + (i - in_st + 1), i + 1, in_end, map);
        return node;
    }

    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        // deal with edge case(s)
        if (preorder.length == 0) {
            return null;
        }

        // build a map of the indices of the values as they appear in the inorder array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        // initialize the stack of tree nodes
        Stack<TreeNode> stack = new Stack<>();
        int value = preorder[0];
        TreeNode root = new TreeNode(value);
        stack.push(root);

        // for all remaining values...
        for (int i = 1; i < preorder.length; i ++) {
            // create a node
            value = preorder[i];
            TreeNode node = new TreeNode(value);

            if (map.get(value) < map.get(stack.peek().val)) {
                // the new node is on the left of the last node,
                // so it must be its left child (that's the way preorder works)
                stack.peek().left = node;
            } else {
                // the new node is on the right of the last node,
                // so it must be the right child of either the last node
                // or one of the last node's ancestors.
                // pop the stack until we either run out of ancestors
                // or the node at the top of the stack is to the right of the new node
                TreeNode parent = null;
                while(!stack.isEmpty() && map.get(value) > map.get(stack.peek().val)) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }

        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        buildTree2(preorder, inorder);
    }
}
