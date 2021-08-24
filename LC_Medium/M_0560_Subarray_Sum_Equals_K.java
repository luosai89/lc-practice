package LC_Medium;

import java.util.HashMap;

/**
 * 2021: 6/8 (R), 8/23 (R)
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 * Laioffer explanation https://www.youtube.com/watch?v=aYfwus5T3Bs
 */
public class M_0560_Subarray_Sum_Equals_K {

    // Most optimal - prefix-sum, hashmap
    public static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        // use prefix sum as key, and frequency (count) as value
        // we cannot use a set because we are not looking up by index but by target value
        HashMap<Integer, Integer> sums = new HashMap<>();
        sums.put(0, 1); // if the current sum is k, then find k - k = 0 and increment count by 1
        int preSum = 0;
        int count = 0;
        for (int n : nums) {
            // calculate new current sum with new n
            preSum += n;
            // if k = 5, current sum is 7, yet we have 2 of -2 from previous sums, then there's 2 subarrays
            count += sums.getOrDefault(preSum - k, 0);
            // put the current sum result
            sums.put(preSum, sums.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    // Space efficient
    public static int subarraySum2(int[] nums, int k) {
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

    public static void main(String[] args) {
        int result1 = subarraySum(new int[]{1,1,1}, 2);
        int result2 = subarraySum(new int[]{1,2,3}, 3);
    }
}
