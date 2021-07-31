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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 7/31 (makeup 7/18)
 */
public class T_0802_02_Minimum_Path {
    public static int minimumPathRecursive(TNode root) {
        // Write your code here
        if (root.left == null && root.right == null) {
            return root.data;
        }
        if (root.left == null) {
            return root.data + minimumPathRecursive(root.right);
        }
        if (root.right == null) {
            return root.data + minimumPathRecursive(root.left);
        }
        return root.data + Math.min(minimumPathRecursive(root.right), minimumPathRecursive(root.left));
    }

    // not sure if this is the best method, passed all tests
    public static int minimumPathIterative(TNode root) {
        // Write your code here
        Set<TNode> visited = new HashSet<>();
        visited.add(null);
        Deque<TNode> stack = new ArrayDeque<>();
        TNode t = root;
        int minSum = Integer.MAX_VALUE;
        int sum = 0;
        while (t != null) {
            stack.offerLast(t);
            visited.add(t);
            sum += t.data;
            if (t.left != null) t = t.left;
            else if (t.right != null) t = t.right;
            else {
                // both null - reached leaf node
                // compare against minSum and remove the leaf node
                minSum = Math.min(sum, minSum);
                // if the last node has no unvisited path left, remove the node and subtract its data from the sum
                while (!stack.isEmpty() && visited.contains(stack.peekLast().left) && visited.contains(stack.peekLast().right)) {
                    TNode removed = stack.pollLast();
                    sum -= removed.data;
                }
                if (stack.isEmpty()) break;
                // otherwise, visit unvisited notes
                if (visited.contains(stack.peekLast().left)) t = stack.peekLast().right;
                else if (visited.contains(stack.peekLast().right)) t = stack.peekLast().left;
            }
        }
        return minSum;
    }
}
