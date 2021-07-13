package LC_Medium;

import Resource.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * 7/12 (makeup 7/4)
 * Binary Tree - Iterator
 *
 */
public class M_0173_Binary_Search_Tree_Iterator {

    /**
     * suppose the binary search tree has height h (if it's a balanced bst, h is O(logn))
     * constructor: O(h) worst case
     * hasNext(): O(1)
     * next(): O(h) worst case
     * add(): O(h) worst case
     */
    class BSTIterator {
        private Deque<TreeNode> deque;

        public BSTIterator(TreeNode root) {
            this.deque = new ArrayDeque<>();
            pushAll(root);
        }

        public int next() {
            TreeNode node = deque.pop();
            pushAll(node.right);
            return node.val;
        }

        public boolean hasNext() {
            return !deque.isEmpty();
        }

        private void pushAll(TreeNode root) {
            TreeNode t = root;
            while (t != null) {
                this.deque.push(t);
                t = t.left;
            }
        }
    }
}
