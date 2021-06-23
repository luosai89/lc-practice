package LC_Medium;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * 2021: 6/21 pq, 6/22 qs
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

    // quick sort - TODO: why randomize improves efficiency
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return 0;
        randomize(nums);
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int n = partition(nums, start - 1, start, end);
            if (n + 1 == nums.length - k + 1) return nums[n];
            if (n + 1 < nums.length - k + 1) {
                start = n + 1;
            } else {
                end = n - 1;
            }
        }
        return 0;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    private int partition(int[] nums, int slow, int fast, int end) {
        int pivot = nums[end];
        while (fast < end) {
            if (nums[fast] <= pivot) swap(nums, fast++, ++slow);
            else fast++;
        }
        swap(nums, end, ++slow);
        return slow;
    }

    private void randomize(int[] nums) {
        Random rand = new Random();
        for (int l = nums.length - 1; l > 0; l--) {
            swap(nums, l, rand.nextInt(l));
        }
    }
}
