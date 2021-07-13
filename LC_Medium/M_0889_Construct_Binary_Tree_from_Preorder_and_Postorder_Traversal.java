package LC_Medium;

import Resource.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 * 7/12**
 * Binary tree construction: 1) recursive with helper, 2) recursive w/o helper, 3) iterative
 * Lee215: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/discuss/161268/C%2B%2BJavaPython-One-Pass-Real-O(N)
 */
public class M_0889_Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal {
    // r,[a,b,c],[x,y,z]  ->  preorder
    // [a,b,c],[x,y,z],r  ->  postorder

    /**
     * Method 1: Recursive with helper (more intuitive)
     */
    public TreeNode constructFromPrePost1(int[] pre, int[] post) {
        return helper1(pre, post, 0, 0, pre.length);
    }

    public TreeNode helper1(int[] pre, int[] post, int pre_st, int post_st, int size) {
        // termination criteria 1
        if (size <= 0) return null;

        // the 1st val in preorder must be root
        // termination criteria 2
        TreeNode node = new TreeNode(pre[pre_st]);
        if (size == 1) return node;

        // the 2nd val in preorder must be the root of the left subtree
        // however, the root of the left subtree will be the last element in postorder
        // therefore, traverse until we found the same element, which will inform the size of the left subtree
        int t = post_st;
        while (post[t] != pre[pre_st + 1]) t++;
        int leftSize = t - post_st + 1;

        // build left and right tree with recursive call
        node.left = helper1(pre, post, pre_st + 1, post_st, leftSize);
        node.right = helper1(pre, post, pre_st + 1 + leftSize, t + 1, size - 1 - leftSize);
        return node;
    }

    /**
     * TODO Method 2: Recursive without helper (lee215)
     * Create a node TreeNode(pre[preIndex]) as the root.
     *
     * Because root node will be lastly iterated in post order,
     * if root.val == post[posIndex],
     * it means we have constructed the whole tree,
     *
     * If we haven't completed constructed the whole tree,
     * So we recursively constructFromPrePost for left sub tree and right sub tree.
     *
     * And finally, we'll reach the posIndex that root.val == post[posIndex].
     * We increment posIndex and return our root node.
     */
    int preIndex = 0, posIndex = 0;
    public TreeNode constructFromPrePost2(int[]pre, int[]post) {
        TreeNode root = new TreeNode(pre[preIndex++]);
        if (root.val != post[posIndex])
            root.left = constructFromPrePost2(pre, post);
        if (root.val != post[posIndex])
            root.right = constructFromPrePost2(pre, post);
        posIndex++;
        return root;
    }

    /**
     * TODO Method 3: Iterative with Stack (lee215)
     * We will preorder generate TreeNodes, push them to stack and postorder pop them out.
     *
     * 1. Iterate on pre array and construct node one by one.
     * 2. stack save the current path of tree.
     * 3. node = new TreeNode(pre[i]), if not left child, add node to the left. otherwise add it to the right.
     * 4. If we meet a same value in the pre and post, it means we complete the construction for current subtree...
     * We pop it from stack.
     */
    public TreeNode constructFromPrePost3(int[] pre, int[] post) {
        Deque<TreeNode> s = new ArrayDeque<>();
        s.offer(new TreeNode(pre[0]));
        for (int i = 1, j = 0; i < pre.length; ++i) {
            TreeNode node = new TreeNode(pre[i]);
            while (s.getLast().val == post[j]) {
                s.pollLast();
                j++;
            }
            if (s.getLast().left == null) s.getLast().left = node;
            else s.getLast().right = node;
            s.offer(node);
        }
        return s.getFirst();
    }

}
