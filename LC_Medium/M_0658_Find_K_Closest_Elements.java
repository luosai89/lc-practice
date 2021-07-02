package LC_Medium;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-k-closest-elements/
 * 7/1**
 * Binary search
 */
public class M_0658_Find_K_Closest_Elements {

    // Using window of k + 1 elements to find the best starting element
    // Explanation: https://www.youtube.com/watch?v=gfwLpRYbCx0
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Find starting element in the range of 0 and length - k
        int lo = 0, hi = arr.length - k;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // Compare x value compared to those at mid vs mid + k
            // ... we cannot use the absolute value (fails case in main below)
            // If x is closer to mid + k than mid, then the lowest starting point cannot be mid or before
            if (x - arr[mid] > arr[mid + k] - x) lo = mid + 1;
            // If x is closer to mid than mid + k, then the highest starting point cannot be after mid
            else hi = mid;
        }
        List<Integer> res = new LinkedList<>();
        for (int i = lo; i < lo + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        // If using absolute value, if mid value is 2, and mid + k value is also 2
        // ... we will eliminate the later 2 and move further away from our desired range
        int[] test = {1,1,2,2,2,2,2,3,3};
        System.out.println(findClosestElements(test, 3, 3));
    }
}
