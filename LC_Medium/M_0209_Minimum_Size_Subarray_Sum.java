package LC_Medium;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * 2021: 6/19
 * Sliding window (shortest)
 */
public class M_0209_Minimum_Size_Subarray_Sum {
    public int minSubArrayLen(int target, int[] nums) {
        // result to store the min length
        int result = nums.length + 1;

        // slide the window
        int start = 0, end = 0, sum = 0;
        while (end < nums.length) {
            int n = nums[end++];
            if (n >= target) return 1;
            sum += n;
            while (sum >= target) {
                result = Math.min(result, end - start);
                sum -= nums[start++];
            }
        }

        // if end result is still max value, nothing was found
        return result > nums.length ? 0 : result;
    }
}
