package Others;

/**
 * A path in a binary search tree (BST) is an ordered list of nodes that connect the root of the tree to
 * any one of the leaf-nodes without using the same node more than once.
 * The sum of a path in a BST is the sum of the integer values of the nodes in the path.
 * The minimum path of a BST is the path with the smallest sum.
 * Write an algorithm to determine the sum of the minimum path for a given BST.
 *
 * Example:
 *          7
 *         / \
 *       3   10
 *      /    / \
 *     1     8  12
 *    /
 *   0
 *
 *   There are three paths from the root node to the leaf nodes.
 *   7 → 3 → 1 → 0, sum = 11
 *   7 → 10 → 8, sum = 25
 *   7 → 10 → 12, sum = 29
 *
 *   The sum of the minimum path is 11.
 *
 *   Complete the function minimumPathSum in the editor below.
 *   minimumPathSum has the following parameter(s):
 *   TNode root:  the root of the binary search tree
 *
 *   Returns:
 *   int:  the sum of the minimum path
 *
 *   Constraints
 *   All nodes have the tree have non-negative integer data values
 */

import Resource.TNode;

import java.util.List;

/**
 * 7/31 (makeup 7/18)
 */
public class T_0802_02_Minimum_Path {
    public static int minimumPath(TNode root) {
        // Write your code here
        if (root.left == null && root.right == null) {
            return root.data;
        }
        if (root.left == null) {
            return root.data + minimumPath(root.right);
        }
        if (root.right == null) {
            return root.data + minimumPath(root.left);
        }
        return root.data + Math.min(minimumPath(root.right), minimumPath(root.left));
    }
}
