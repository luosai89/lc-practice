package LC_Medium;

/**
 * https://leetcode.com/problems/subarray-product-less-than-k/
 * 2021: 6/18*
 * Sliding window https://leetcode.com/problems/subarray-product-less-than-k/discuss/108861/JavaC%2B%2B-Clean-Code-with-Explanation
 */
public class M_0713_Subarray_Product_Less_Than_K {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // edge case - because everything is non-negative but k can be zero
        if (k == 0) return 0;

        // slide the window
        int product = 1, result = 0, start = 0, end = 0;
        while (end < nums.length) {
            // don't do (if n < k, multiply) here because that will allows skipping and breaks continuity
            product = product * nums[end++];
            // start can equal end, resetting product to 1 when a single number is >= k, breaking continuity
            // we need to divide when product equals k because we only want those less than
            while (start <= end && product >= k) {
                product = product / nums[start++];
            }
            result += (end - start);
        }

        return result;
    }
}
