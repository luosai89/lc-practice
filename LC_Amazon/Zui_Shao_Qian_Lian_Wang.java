package LC_Amazon;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 8/9
 */
public class Zui_Shao_Qian_Lian_Wang {
//    public static void main(String[] args) {
//        int n = 6;
//        int[][] edges = {{1, 4}, {4, 5}, {2, 3}};
//        int[][] newEdges = {{1, 2, 5}, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}};
//        System.out.println(minCost(n, edges, newEdges));
//    }

    public int minCost(int n, int[][] edges, int[][] newEdges) {
        // TODO STEP 1 - Create new UF and supply known parents based on existing edges
        // Initialize a new uf with n + 1 nodes (for convenience since nodes are 1-based)
        UnionFind uf = new UnionFind(n + 1);

        // When uf first initialized, each node has itself as parent...
        // ... therefore we need to inform of their existing actual parents
        // ... by making two nodes (edge[0], [1]) of each existing edge to have the same parents
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        // TODO STEP 2 - Check new edges from the least costly, if not already connected ...
        //  ... connect and track total cost and remaining edges needed
        // PriorityQueue of unconnected new edge, sort ascend by cost of connection (newEdge[2])
        Queue<int[]> pq = new PriorityQueue<>(newEdges.length, (e1, e2) -> Integer.compare(e1[2], e2[2]));
        pq.addAll(Arrays.asList(newEdges));

        int totalCost = 0;
        while (!pq.isEmpty() && uf.edgesNeeded != 0) {
            int[] edge = pq.poll();
            if (!uf.connected(edge[0], edge[1])) {
                uf.union(edge[0], edge[1]);
                totalCost += edge[2];
            }
        }
        return totalCost;
    }
    class UnionFind {
        private int[] parents;  // parent[i] = parent of i
        private byte[] parentRanks;   // rank[i] = rank of subtree rooted at i (never more than 31)
        public int edgesNeeded; // number of connected components

        public UnionFind(int n) {
            if (n < 0) throw new IllegalArgumentException();
            parents = new int[n];
            parentRanks = new byte[n]; // initialize all parents to be equal (0)
            for (int i = 0; i < n; i++) {
                parents[i] = i; // initialize all nodes to be their own parents
            }
            edgesNeeded = n - 1; // we only need n - 1 edges for n nodes to have them all connected
        }

        // The parent is found when the parent's parent is itself (i.e it has no other parent)
        public int findParentFor(int p) {
            while (p != parents[p]) { // if p is not its own parent
                parents[p] = parents[parents[p]]; // set p's parent to p's grandpa
                p = parents[p]; // move pointer to p's parent
            }
            return p;
        }

        public void union(int p, int q) {
            // recursively find the real parents for p and q
            int pParent = findParentFor(p);
            int qParent = findParentFor(q);

            // if p & q already share the same parent, no need to union
            if (pParent == qParent) return;

            if (parentRanks[pParent] < parentRanks[qParent]) { // the lower ranked has to accept the parent of the higher ranked
                parents[pParent] = qParent;
            } else {
                parents[qParent] = pParent;
                // if we assign qParent's parent to be pParent because their rank was the same ...
                // ... then we can rank one of them higher
                if (parentRanks[pParent] == parentRanks[qParent]) parentRanks[pParent]++;
            }
            edgesNeeded--;
        }

        public boolean connected(int p, int q) {
            return findParentFor(p) == findParentFor(q); // p and q are connected if they share the same parents
        }
    }
}
