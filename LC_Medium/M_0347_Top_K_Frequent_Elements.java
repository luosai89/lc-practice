package LC_Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 * 6/22 (makeup 6/2)
 * Heap - top K elements
 */
public class M_0347_Top_K_Frequent_Elements {
    // Method 1 - PQ1
    public int[] topKFrequent1(int[] nums, int k) {
         Map<Integer, Integer> map = new HashMap<>();
         for (int n : nums) {
             map.put(n, map.getOrDefault(n, 0) + 1);
         }
         PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
         for (Map.Entry entry : map.entrySet()) {
             pq.add(entry);
         }
         int[] result = new int[k];
         while (k > 0) {
             result[k-- - 1] = pq.poll().getKey();
         }
         return result;
    }

    // Method 2 - bucket
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int n : map.keySet()) {
            int freq = map.get(n);
            if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
            buckets[freq].add(n);
        }

        int[] result = new int[k];
        int j = 0;
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (int n : buckets[i]) {
                    result[j++] = n;
                    if (j == k) return result;
                }
            }
        }

        return result;
    }
}
