package LC_Medium;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 * 2021: 6/20(*)
 * Intervals - to revisit
 */
public class M_0986_Interval_List_Intersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList == null || firstList.length == 0 || secondList == null || secondList.length == 0) return new int[][]{};
        int i1 = 0, i2 = 0;
        LinkedList<int[]> merged = new LinkedList<>();
        while (i1 < firstList.length && i2 < secondList.length) {
            int maxStart = Math.max(firstList[i1][0], secondList[i2][0]);
            int minEnd = Math.min(firstList[i1][1], secondList[i2][1]);
            if (maxStart <= minEnd) merged.add(new int[]{maxStart, minEnd});
            if (firstList[i1][1] == minEnd) i1++;
            if (secondList[i2][1] == minEnd) i2++;
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
