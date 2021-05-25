package LC_Easy;

/**
 * https://leetcode.com/problems/move-zeroes/
 * 5/24/2021
 */
public class E_0283_Move_Zeros {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }
        int j = 0;
        int cur = 0; // curr = 1

        for (int i = 0; i < nums.length; ++i) { // i = 1
            if (nums[i] != 0) { // nums[i] = 1
                int temp = nums[cur];
                nums[cur++] = nums[i];
                nums[i] = temp;
            }
        }
    }

    /**
     * Shift non-zero values as far forward as possible
     * Fill remaining space with zeros
     * To revisit
     */
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}
