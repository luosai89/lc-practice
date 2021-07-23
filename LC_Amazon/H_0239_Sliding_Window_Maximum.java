package LC_Amazon;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * 7/22
 * Amazon high freq
 */
public class H_0239_Sliding_Window_Maximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length - k + 1;
        int[] res = new int[len];

        // i to traverse the index of nums
        int i = 0;
        // dq as window of indexes
        // we will keep the index of the max value at the front
        Deque<Integer> dq = new ArrayDeque<>();

        while (i < nums.length) {
            // check if the first number is being pushed out of the window
            if (!dq.isEmpty() && dq.peekFirst() == i - k) {
                dq.pollFirst();
            }
            // remove from back all values smaller than current number
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            // add current number to deque
            dq.offerLast(i);
            // add results if the index is sitting in the valid range
            // only start adding max to result when we have the first full window
            if (i >= k - 1) {
                res[i - k + 1] = nums[dq.peekFirst()];
            }
            // increment i
            i++;
        }
        return res;
    }
}
