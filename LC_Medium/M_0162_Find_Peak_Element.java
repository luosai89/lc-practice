package LC_Medium;

/**
 * https://leetcode.com/problems/find-peak-element/
 * 6/29 (makeup 6/23)
 * Binary search
 */
public class M_0162_Find_Peak_Element {
    public int findPeakElement(int[] nums) {
        // edge case
        if (nums == null || nums.length == 0) return -1;

        // variables
        int l = 0, r = nums.length - 1;

        // binary search
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (nums[m] < nums[m + 1]) l = m;
            else r = m;
        }

        return nums[l] > nums[r] ? l : r;
    }
}
