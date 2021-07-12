package LC_Medium;

import Resource.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * 7/11** (makeup 7/7)
 * Binary tree construction: recursive solution and iterative solution
 * Difference vs 105: we must also record post_end other than post_st
 * Iterative TODO https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/discuss/34807/Java-iterative-solution-with-explanation
 */
public class M_0106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
    // [a, b, c],  r, [x, y, z]  ->  inorder
    // [a, b, c], [x,  y, z], r  ->  postorder

    // Recursive with helper and map cache
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        // use a map to track node locations in inorder array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return helper(inorder, postorder,
            0, inorder.length - 1,
            0, inorder.length - 1,
            map);
    }
    private static TreeNode helper(int[] inorder, int[] postorder,
                            int post_st, int post_end, int in_st, int in_end,
                            Map<Integer, Integer> map) {
        // exit criteria
        if (post_st < 0 || in_st > in_end ) return null;

        // the last node in postorder must be root
        int r_val = postorder[post_end];
        TreeNode root = new TreeNode(r_val);

        // assign left and right to root by calling helper recursively
        int i = map.get(r_val);
        int size = i - in_st;
        root.left = helper(inorder, postorder, post_st, post_st + size - 1, in_st, i - 1, map);
        root.right = helper(inorder, postorder, post_st + size, post_end - 1, i + 1, in_end, map);
        return root;
    }

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        buildTree(inorder, postorder);
    }
}
