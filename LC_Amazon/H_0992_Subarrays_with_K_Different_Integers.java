package LC_Amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class H_0992_Subarrays_with_K_Different_Integers {

    public static int subarraysWithKDistinctSlowerWithHelper(int[] nums, int k) {
        return subarraysWithAtMostKDistinct(nums, k) - subarraysWithAtMostKDistinct(nums, k - 1);
    }

    public static int subarraysWithAtMostKDistinct(int[] nums, int k) {
        int subarrayCount = 0;
        Map<Integer, Integer> numFrequency = new HashMap<>(); // TODO space O(2n)

        int start = 0;
        for (int end = 0; end < nums.length; end++) { // TODO time O(2n)

            // if new num, reduce k
            int newNum = nums[end];
            int newNumFrequency = numFrequency.getOrDefault(newNum, 0);
            if (newNumFrequency == 0) k -= 1;
            numFrequency.put(newNum, newNumFrequency + 1);

            // if k < 0, we have more distinct integer than needed
            // ... must drop enough numbers from the front until we have just enough (k == 0)
            while (k < 0) {
                int dropNum = nums[start];
                numFrequency.put(dropNum, numFrequency.get(dropNum) - 1);
                if (numFrequency.get(dropNum) == 0) k++;
                start++;
            }

            // there are count of (end - start) of new arrays ending with the new num
            // there are count of 1 of new array being the new num itself
            // ... as such the subarrayCount is incremented by (end - start + 1)
            subarrayCount += end - start + 1;
        }
        return subarrayCount;
    }

    public int subarraysWithKDistinctFasterWithoutHelper(int[] nums, int k) {
        int count = 0, prefix = 0;
        int[] m = new int[nums.length + 1];
        for (int i = 0, j = 0, cnt = 0; i < nums.length; ++i) {
            if (m[nums[i]]++ == 0) ++cnt;
            if (cnt > k) {
                --m[nums[j++]]; --cnt; prefix = 0;
            }
            while (m[nums[j]] > 1) {
                ++prefix; --m[nums[j++]];
            }
            if (cnt == k) count += prefix + 1;
        }
        return count;
    }

    public static void test(int[] nums, int k, int expected) {
        int result = subarraysWithKDistinctSlowerWithHelper(nums, k);
        System.out.printf("%s %s: Got %d Expected %d", result == expected ? "SUCCESS" : "FAIL",
            Arrays.toString(nums), result, expected);
        System.out.println();
    }

    public static void main(String[] args) {
        test(new int[]{1,2,1,2,3}, 2, 7);
        test(new int[]{1,2,1,3,4}, 3, 3);
    }

}
