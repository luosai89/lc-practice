package LC_Medium;

import java.util.HashMap;

/**
 * 6/8/2021 https://leetcode.com/problems/subarray-sum-equals-k/
 * Laioffer explanation https://www.youtube.com/watch?v=aYfwus5T3Bs
 */
public class M_0560_Subarray_Sum_Equals_K {

    // Most optimal - prefix-sum, hashmap
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, Integer> sums = new HashMap<>();
        sums.put(0, 1);
        int preSum = 0;
        int count = 0;
        for (int n : nums) {
            preSum += n;
            count += sums.getOrDefault(preSum - k, 0);
            sums.put(preSum, sums.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    // Space efficient
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int preSum = 0;
            for (int j = i; j < nums.length; j++) {
                preSum += nums[j];
                if (preSum == k) count++;
            }
        }
        return count;
    }
}
