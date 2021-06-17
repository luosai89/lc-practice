package LC_Easy;

/**
 * https://leetcode.com/problems/maximum-subarray/
 * 2021: 6/16 (makeup 6/15)
 * the largest sum ending x, will be the largest positive sum ending x - 1 plus x
 */
public class E_0053_Maximum_Subarray {
    public int maxSubArray(int[] nums) {
        int[] maxSums = new int[nums.length];
        maxSums[0] = nums[0];
        int res = maxSums[0];
        for (int i = 1; i < nums.length; i++) {
            maxSums[i] = nums[i] + (maxSums[i - 1] < 0 ? 0 : maxSums[i - 1]);
            res = Math.max(res, maxSums[i]);
        }
        return res;
    }
}
