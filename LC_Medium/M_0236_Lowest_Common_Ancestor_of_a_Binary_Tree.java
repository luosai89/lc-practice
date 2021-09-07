package LC_Medium;

import Resource.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 2021: 9/7
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 */
public class M_0236_Lowest_Common_Ancestor_of_a_Binary_Tree {

    // recursive
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // exit criteria - we reached the end, or if we found p OR q
        if (root == null || root == p || root == q) return root;

        // find the lowest level parent for p and q in the left tree, and in the right tree
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);

        // if we found p or q in both sides, p and q must be in separate sub-trees
        if (left != null && right != null) return root;
        // otherwise they are on the same side
        return left == null ? right : left;
    }

    // iterative
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> immediateParent = new HashMap<>();
        immediateParent.put(root, null);

        Deque<TreeNode> path = new ArrayDeque<>();
        path.offerLast(root); // root cannot be null

        // Step 1: DFS (stack) until we found both p, q, and their immediate parents
        // we do not need to check that path is not empty, because p & q are guaranteed to exist in the tree
        while(!immediateParent.containsKey(p) || !immediateParent.containsKey(q)) {
            TreeNode parent = path.pollLast();
            if(parent.left != null) {
                path.offerLast(parent.left);
                immediateParent.put(parent.left, parent);
            }
            if(parent.right != null) {
                path.offerLast(parent.right);
                immediateParent.put(parent.right, parent);
            }
        }

        // Step 2: Find all the parents for p
        Set<TreeNode> pParents = new HashSet<>();
        pParents.add(p);
        TreeNode pParent = immediateParent.get(p);
        while(pParent != null) {
            pParents.add(pParent);
            pParent = immediateParent.get(pParent);
        }

        // Step 3: Go up in q's ancestry to find common parent with p
        if(pParents.contains(q)) return q;
        TreeNode qParent = immediateParent.get(q);
        while(qParent != null && !pParents.contains(qParent)) {
            qParent = immediateParent.get(qParent);
        }

        return qParent;
    }
}

/**
 * 2021
 * 9/7
 * I had the right overall plan for the iterative method (the stack, the child/parent map), but got stuck on how to find
 * the lowest common parents. I was thinking set but two sets. And didn't know how to identify the lowest common one.
 * Learned that I just need one set. And if you work the path of the other one, the first one you found had to be the
 * lowest one. The recursive method is quite mind boggling. I think I understood it theoretically but I do not know how
 * I could think of it on my own. 
 */