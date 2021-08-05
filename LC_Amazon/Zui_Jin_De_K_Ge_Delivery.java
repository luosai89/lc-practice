package LC_Amazon;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 8/5 (makeup 8/2)
 */
public class Zui_Jin_De_K_Ge_Delivery {
    public static int[][] closestKLocations(int[][] allLocations, int k) {
        // priority queue with further deliveries at the front (to be easily removed)
        // initial capacity at k + 1, so we can add a new delivery at capacity at the correct order, and poll the worst
        PriorityQueue<int[]> pq = new PriorityQueue<>(k + 1, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int distance1 = (a[0] * a[0]) + (a[1] * a[1]);
                int distance2 = (b[0] * b[0]) + (b[1] * b[1]);

                // return larger distance, else larger x-coordinate
                if (distance1 == distance2) return b[0] - a[0];
                return distance2 - distance1;
            }
        });

        // Time O(n * logk), Space O(k + 1)
        for (int[] location : allLocations) {
            pq.offer(location); // add a new delivery at the correct order (even above capacity)
            if (pq.size() > k) pq.poll(); // poll the worst one above capacity
        }

        int[][] result = new int[k][2]; // space O(k)
        return pq.toArray(result); // time O(k)
    }

    public static void main(String[] args) {
        int[][] test1 = new int[][] {
            {1, 2},
            {1, -1},
            {3, 4}
        };

        int[][] result = closestKLocations(test1, 2);

        for (int[] each_elt : result) {
            System.out.println(String.format("[%d,%d]", each_elt[0], each_elt[1]));
        }
    }
}
