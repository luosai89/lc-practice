package LC_Medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/ (premium)
 * 6/20 (makeup 6/3)
 * Intervals
 * method 1: use PriorityQueue and keep the latest meeting time;
 * (?) method 2: use two arrays to store start/end times separately
 */
public class M_0253_Meeting_Rooms_II {

    // PriorityQueue
    public static int minMeetingRooms1(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0) return 0;

        // sort the array
        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);

        // use the priority queue, time O(nlogn)
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> a[1] - b[1]);
        pq.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            // if the next meeting start time is later than the earliest meeting end time, no new room needed
            // remove the earliest meeting end time
            if (intervals[i][0] >= pq.peek()[1]) pq.poll();
            // otherwise, we need a new room. but in either case, we just add this current meeting's time
            pq.offer(intervals[i]);
        }
        return pq.size();
    }

    // Two arrays for start/end, time O(nlogn)
    public static int minMeetingRooms2(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0) return 0;

        // separately store the start/end time, sorted
        int[] s = new int[intervals.length];
        int[] e = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            s[i] = intervals[i][0];
            e[i] = intervals[i][1];
        }
        Arrays.sort(s); // 0,  5,  15
        Arrays.sort(e); // 10, 20, 30

        // TODO - count the rooms
        int result = 0;
        for (int i = 0, j = 0; i < intervals.length; i++) {
            // if the start time is same or later than end time, no overlap
            if (s[i] >= e[j]) j++;
            // otherwise, add a room
            else result++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] test1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(minMeetingRooms2(test1));
        int[][] test2 = {{7, 10}, {2, 4}};
        System.out.println(minMeetingRooms2(test2));
        int[][] test3 = new int[][]{};
        System.out.println(minMeetingRooms2(test3));
    }
}
