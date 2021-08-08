package LC_Amazon;

import Resource.TreeNode;

/**
 * https://docs.google.com/document/d/18RXFNAJKx0dsS8NV-WjmOVs6NoKpmwkvSxFQSdJPTDU/edit?usp=sharing
 * 7/20 (makeup 7/18)
 * Amazon high freq
 */
public class M_1120_Maximum_Average_Subtree {
    public static double maximumAverageSubtreeSolution(TreeNode root) {
        return dfsSolution(root, Double.MIN_VALUE)[2];
    }

    private static double[] dfsSolution(TreeNode root, double max) {
        // if a root is null, use 0.0 for both sum(val) and count, to avoid any impact to results
        if (root == null) {
            return new double[]{0.0, 0.0, max};
        }

        // each double[] has sum(val), count(node), and max average
        // initialize double[] with parent info
        double[] parent = new double[]{root.val, 1, max};

        // add results from left and right tree to parent info
        // compare the max average and update if needed
        double[] left = dfsSolution(root.left, max);
        double[] right = dfsSolution(root.right, max);
        parent[0] += left[0] + right[0];
        parent[1] += left[1] + right[1];
        parent[2] = Math.max(left[2], right[2]);

        // calculate the new average and compare to current max
        double newAvg = parent[0] / parent[1];
        if (newAvg > parent[2]) parent[2] = newAvg;

        return parent;
    }

    public static double maximumAverageSubtreeTry(TreeNode root) {
        return dfsTry(root, Double.MIN_VALUE)[2];
    }

    public static double[] dfsTry(TreeNode root, double max) {
        // if null root, reached the end, return results that will not change anything
        if (root == null) return new double[]{0.0, 0.0, max};
        // initialize a parent from given root, track sum, count, current max average
        double[] parent = new double[]{root.val, 1, max};
        // recursively call this method on root.left and root right
        double[] left = dfsTry(root.left, max);
        double[] right = dfsTry(root.right, max);
        // add results to parent array
        // confirm the new max average
        parent[0] += (left[0] + right[0]);
        parent[1] += (left[1] + right[1]);
        parent[2] = Math.max(parent[0]/parent[1],Math.max(left[2], right[2]));
        // return the finalized parent
        return parent;
    }

    public static void main(String[] args) {
        TreeNode tn1 = new TreeNode(6);
        TreeNode tn2 = new TreeNode(3, new TreeNode(3), null);
        TreeNode tn3 = new TreeNode(1, tn1, tn2);
        System.out.println(maximumAverageSubtreeSolution(tn3));
    }

}
