package Others;

import java.util.List;

public class T_0729_02_Minimum_Sum_Subset {
    public static int findMinimumSum(List<Integer> input, int k) {
        // edge cases
        int minSum = 0;
        if (input == null || input.size() < 1 || k > input.size()) {
            return minSum;
        }
        // initialize minSum with the result from the 1st window
        for (int i = 0; i < k; i++) {
            minSum += input.get(i);
        }
        int currSum = minSum;
        // slide window from the 2nd window
        for (int i = k; i < input.size(); i++) {
            currSum += input.get(i);
            currSum -= input.get(i - k);
            if (currSum < minSum) {
                minSum = currSum;
            }
        }
        return minSum;
    }
}
