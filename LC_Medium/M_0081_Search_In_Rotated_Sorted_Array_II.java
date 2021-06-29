package LC_Medium;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * 6/29
 * Binary search
 */
public class M_0081_Search_In_Rotated_Sorted_Array_II {
    public boolean search(int[] nums, int target) {
        // edge cases
        if (nums == null || nums.length == 0) return false;

        // variables
        int left = 0, right = nums.length - 1;

        // binary search
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] > nums[left]) {
                if (nums[left] <= target && target < nums[mid]) right = mid;
                else left = mid;
            } else if (nums[mid] < nums[left]) {
                if (nums[mid] < target && target <= nums[right]) left = mid;
                else right = mid;
            } else {
                left++;
            }
        }

        if (nums[left] == target || nums[right] == target) return true;
        return false;
    }
}
