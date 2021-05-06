package LC_Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Done on 5/5/2021, for 5/4/2021
 * https://leetcode.com/problems/majority-element/
 */

public class E_0169_Majority_Element {
    /**
     * My original method, slow
     */
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
            if (freq.get(n) > nums.length / 2) return n;
        }
        return 0;
    }

    /**
     * Boyer-Moore majority vote algorithm, step here https://www.cs.utexas.edu/~moore/best-ideas/mjrty/example.html
     */
    public int majorityElement2(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

}
