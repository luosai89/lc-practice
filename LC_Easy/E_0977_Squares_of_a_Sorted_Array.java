package LC_Easy;

/**
 * 5/24/2021 https://leetcode.com/problems/squares-of-a-sorted-array/
 */
public class E_0977_Squares_of_a_Sorted_Array {
    public int[] sortedSquares(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int[] sorted = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            // if start less than end
            if (Math.abs(nums[start]) < Math.abs(nums[end])) {
                // put end value at sorted[i], shrink end with end--
                sorted[i] = nums[end] * nums[end];
                end--;
            } else {
                // put start value at sorted[i], shrink start with start++
                sorted[i] = nums[start] * nums[start];
                start++;
            }
        }
        return sorted;
    }
}
