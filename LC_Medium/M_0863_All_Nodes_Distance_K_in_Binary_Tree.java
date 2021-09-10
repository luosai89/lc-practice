package LC_Medium;

import Resource.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 2021: 9/8
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values
 * of all nodes that have a distance k from the target node (in any order.)
 */
public class M_0863_All_Nodes_Distance_K_in_Binary_Tree {

    // todo METHOD 1 - GRAPH + BFS (Map, Set, Queue)

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // Step 1 - build a graph, key = a node, value = list of child and parent nodes for this node (for BFS)
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        buildGraph(root, null, graph);
        // Step 2 - use queue to bfs and set to track visited
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(target);
        Set<TreeNode> offered = new HashSet<>();
        offered.add(target);
        //visited.add(target);
        // Step 3 - bfs starting from target node (unlike dfs we do not need the "path" to it since we know its parent)
        while(!queue.isEmpty()) {
            int size = queue.size(); // represents nodes at the same distance from target
            // Step 3.1 - if we have reached the destination
            if(k == 0) {
                return queue.stream().map(node -> node.val).collect(Collectors.toList());
            }
            // Step 3.2 - if we are still looking for the right distance
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                for(TreeNode neighbor : graph.get(node)) {
                    if(!offered.contains(neighbor)) {
                        queue.offerLast(neighbor);
                        offered.add(neighbor); // we must mark "visited" when we offered, NOT when we polled ...
                        // ... because we do not want to offer duplicates, messing up size control variants
                    }
                }
            }
            k--;
        }
        return Collections.emptyList();
    }

    public void buildGraph(TreeNode node, TreeNode parent, Map<TreeNode, List<TreeNode>> graph) {
        // Goal - build a map to represent graph: a node and its neighboring nodes (parent or child)
        // Exception 1 - if the node is null, return directly, no actions needed
        if(node == null) {
            return;
        }
        // Exception 2 - if the node is not already in the map, we need to first start a new values list for it
        if(!graph.containsKey(node)) {
            graph.put(node, new LinkedList<>());
        }
        // Step 1 - add the node and parent to their respective neighbors
        if(parent != null) {
            graph.get(node).add(parent);
            graph.get(parent).add(node);
        }
        // Step 2 - do the same for this node's children (if not null)
        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    }

    // todo METHOD 2 - DFS

    // todo TESTING

    public static void main(String[] args) {
        M_0863_All_Nodes_Distance_K_in_Binary_Tree problem = new M_0863_All_Nodes_Distance_K_in_Binary_Tree();

        // leetcode example 1
        TreeNode two = new TreeNode(2, new TreeNode(7), new TreeNode(4));
        TreeNode five = new TreeNode(5, new TreeNode(6), two);
        TreeNode one = new TreeNode(1, new TreeNode(0), new TreeNode(8));
        TreeNode three = new TreeNode(3, five, one);
        test(problem, three, five, 2, new int[]{7,4,1});

        // leetcode example 2
        one = new TreeNode(1);
        test(problem, one, one, 3, new int[]{});

        // failed test case 1
        two = new TreeNode(2);
        one = new TreeNode(1, new TreeNode(3), two);
        TreeNode zero = new TreeNode(0, one, null);
        test(problem, zero, two, 1, new int[]{1});

        // failed test case 2
        three = new TreeNode(3);
        one = new TreeNode(1, three, null);
        zero = new TreeNode(0, new TreeNode(2), one);
        test(problem, zero, three, 3, new int[]{2});
    }

    private static void test(M_0863_All_Nodes_Distance_K_in_Binary_Tree problem, TreeNode root,
                            TreeNode target, int distance, int[] expected) {
        List<Integer> res = problem.distanceK(root, target, distance);
        Integer[] result = new Integer[res.size()];
        res.toArray(result);
        Arrays.sort(expected);
        Arrays.sort(result);

        String expectedPrint = Arrays.toString(expected);
        String resultPrint = Arrays.toString(result);
        System.out.printf("%s:\tGiven root %d,\ttarget %d,\tdistance %d.\t\tGot %s,\tExpected %s.\n",
            resultPrint.equals(expectedPrint),
            root.val, target.val, distance, resultPrint, expectedPrint);
    }
}

/**
 * Reflections
 * 2021
 * 9/9 Wed
 * I had the idea that we needed to first find the path to the target node, and then look around it with DFS. However I
 * wanted to use recursively look left, right, and pop path stack up. The problem is that you only want to backtrack
 * WHEN you have found the target but AFTER you have looked left and right. So I could not use one single DFS to do it.
 * Zoom team discussion
 * 9/10 Thu
 * Learned graph + bfs method here: https://tinyurl.com/a5ddx9hb she named the set as visited which is not accurate. I
 * changed it to offered. (We
 */
