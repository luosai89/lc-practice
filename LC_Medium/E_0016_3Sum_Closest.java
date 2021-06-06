package LC_Medium;

import java.util.Arrays;

/**
 * 6/6/2021 https://leetcode.com/problems/3sum-closest/
 */
public class E_0016_3Sum_Closest {
    
    public static int threeSumClosest(int[] nums, int target) {
        // cannot use Integer.MAX_VALUE in case the target is negative
        int sum = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums); // O(nlogn) time, O(n) space
        for (int i = 0; i < nums.length - 2; i++) { // O(n^2) time
            // skip duplicate elements
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int next = i+1;
            int last = nums.length - 1;
            while (next < last) {
                int newSum = nums[i] + nums[next] + nums[last];
                if (newSum == target) {
                    return newSum;
                } else if (Math.abs(newSum - target) < Math.abs(sum - target)) {
                    sum = newSum;
                } else if (newSum < target) {
                    next++;
                } else {
                    last--;
                }

            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] test = {-1,2,1,-4};
        int target = 1;
        System.out.println(threeSumClosest(test, target));
    }
}
