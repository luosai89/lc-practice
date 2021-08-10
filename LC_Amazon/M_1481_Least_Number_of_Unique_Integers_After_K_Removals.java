package LC_Amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 8/9 (makeup 6/28)
 * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
 */
public class M_1481_Least_Number_of_Unique_Integers_After_K_Removals {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        // Steps:
        // put num:freq in map
        // put map.entry in priority queue, ordered ascending by freq
        // poll until k is met

        Map<Integer, Integer> numFreq = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> {
            return a.getValue() - b.getValue();
        });

        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];
            numFreq.put(n, numFreq.getOrDefault(n, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : numFreq.entrySet()) {
            pq.offer(entry);
        }
        while (k > 0) {
            int reduction = pq.poll().getValue();
            if (reduction <= k) {
                k -= reduction;
            } else { // if polled freq is greater than k, then this num is not depleted (e.g. has 5 but only 3 removals left)
                return pq.size() + 1;
            }
        }
        return pq.size();
    }
}
