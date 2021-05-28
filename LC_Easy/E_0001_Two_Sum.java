package LC_Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 5/27/2021 https://leetcode.com/problems/two-sum/
 */
public class E_0001_Two_Sum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numsToIndex = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numsToIndex.containsKey(complement)) {
                return new int[] { numsToIndex.get(complement), i };
            }
            numsToIndex.put(nums[i], i);
        }
        return new int[2];
    }
}
