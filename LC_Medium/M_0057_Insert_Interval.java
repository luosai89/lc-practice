package LC_Medium;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/insert-interval/
 * 6/19 (makeup 6/4)
 * Intervals - to revisit - track the largest without adding until the end so we could add in order
 */
public class M_0057_Insert_Interval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // already sorted
        LinkedList<int[]> merged = new LinkedList<>();
        // start/end (not newInterval) will track the last/largest pair
        int start = newInterval[0], end = newInterval[1];
        for (int[] i : intervals) {
            // if smaller interval, add to front
            if (i[1] < start) merged.add(i);
                // if its the new largest interval, add the previous largest (start, end)
                // and have start/end track the new largest without adding to list
            else if (i[0] > end) {
                int[] temp = {start, end};
                merged.add(temp);
                start = i[0];
                end = i[1];
                // if it's overlapping with the previous largest, just update the previous largest
            } else {
                start = Math.min(start, i[0]);
                end = Math.max(end, i[1]);
            }
        }
        // we do not add the largest until the very end
        // and use newInterval to add to the list
        // which works with the edge case when the for loop didn't happen
        newInterval[0] = start;
        newInterval[1] = end;
        merged.add(newInterval);
        return merged.toArray(new int[merged.size()][]);
    }
}
