package LC_Medium;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * 2021: 6/21
 * Heap - top K elements: 1) priority queue
 * 2) quick sort with random partition https://www.youtube.com/watch?v=zyskis1Gw0c
 */
public class M_0215_Kth_Largest_Element_in_an_Array {

    // priority queue
    public int findKthLargest1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : nums) {
            pq.offer(n);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
}
