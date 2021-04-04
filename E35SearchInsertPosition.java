/**
 * 4/3/2021 https://leetcode.com/problems/search-insert-position/
 */
public class E35SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        // R1: start != end, R2: start <= end
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        // Debated a bit here - since start and end is the same,
        // if smaller, insert and replace start/end, if larger, after start
        if (nums[start] < target) {
            return start + 1;
        } else {
            return start;
        }
    }
}
