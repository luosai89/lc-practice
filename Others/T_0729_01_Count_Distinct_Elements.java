package Others;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Problem solving class
 * Sliding window 1
 */
public class T_0729_01_Count_Distinct_Elements {
    public static List<Integer> countDistinctElements(List<Integer> input, int k) {
        List<Integer> res = new LinkedList<>();
        // edge cases
        if (input == null || input.size() < 1 || k > input.size()) {
            return res;
        }
        // set to track unique values for each sliding window - space O(k)
        Map<Integer, Integer> unique = new HashMap<>();
        int start = 0, end = 0;
        while (end < input.size()) {
            // add or increase count for the new number
            int currNum = input.get(end);
            unique.put(currNum, unique.getOrDefault(currNum, 0) + 1);
            // starting the second window ...
            // ... remove or decrease count for the first number
            if (end >= k) {
                int firstNum = input.get(end - k);
                if (unique.get(firstNum) == 1) {
                    unique.remove(firstNum);
                } else {
                    unique.put(firstNum, unique.get(firstNum) - 1);
                }
            }
            // when we have a full window ...
            // ... add the unique count (map size) in the result list
            if (end >= k - 1) {
                res.add(unique.size());
            }
            end++;
        }
        return res;
    }
}
