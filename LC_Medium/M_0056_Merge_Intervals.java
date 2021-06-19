package LC_Medium;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/merge-intervals/
 * 2021: 6/19 (makeup 6/5)
 * Intervals (sort arrays by certain val, list vs arrays transitions, extracting element from list of arrays, when to use linkedlist)
 * Solution: https://leetcode.com/problems/merge-intervals/solution/
 */
public class M_0056_Merge_Intervals {
    public int[][] merge(int[][] intervals) {
        // sort the array before comparing - O(n*logn)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();

        // comparing each array for merging opportunities - O(n)
        for (int[] interval : intervals) {
            // we can directly add current interval, if -
            // 1) this is the first interval, or -
            // 2) this interval has no overlap from the last.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
                // otherwise there has to be overlap
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }

        // Analysis on Collections.toArray() complexity - in short, O(n)
        // https://stackoverflow.com/questions/27327958/what-is-the-time-complexity-of-collection-toarray
        // note the input is a initialized array with only the depth (rows) not width (columns) defined
        return merged.toArray(new int[merged.size()][]);
    }
}
