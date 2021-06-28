package LC_Medium;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * 6/27 (makeup 5/30)
 * Binary Search
 */
public class M_0033_Search_in_Rotated_Sorted_Array {
    public int search(int[] nums, int target) {
        // edge case
        if (nums == null || nums.length == 0) return -1;

        // variables
        int left = 0;
        int right = nums.length - 1;

        // binary search
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > nums[left]) { // if mid is before the "cliff" (if any)
                if (nums[left] <= target && target < nums[mid]) right = mid;
                else left = mid;
            } else { // else mid is after the "cliff"
                if (nums[mid] < target && target <= nums[right]) left = mid;
                else right = mid;
            }
        }

        // check the last two numbers
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
}
