package LC_Easy;

/**
 * https://leetcode.com/problems/remove-element/
 * 5/24/2021
 */
public class E_0027_Remove_Element {
    public int removeElement(int[] nums, int val) {
        int len = 0; //slow runner and length recorder
        for (int i = 0; i < nums.length; i++) { //fast runner
            if (nums[i] != val) {
                nums[len] = nums[i];
                len++;
            }
        }
        return len;
    }
}
