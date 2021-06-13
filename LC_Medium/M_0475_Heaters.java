package LC_Medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/heaters/
 * 06/12/2021
 * 2-pointer explanation: https://leetcode.com/problems/heaters/discuss/95881/Simple-Java-Solution-with-2-Pointers
 * binary search solution: https://leetcode.com/problems/heaters/discuss/95886/Short-and-Clean-Java-Binary-Search-Solution
 */
public class M_0475_Heaters {
    // two-pointer v1
    public int findRadius1(int[] houses, int[] heaters) {
        int r = 0;
        int i = 0;
        int j = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        while (i < houses.length && j < heaters.length) {
            int currR = Math.abs(houses[i] - heaters[j]);
            int nextR = Integer.MAX_VALUE;
            if (j + 1 < heaters.length) {
                nextR = Math.abs(houses[i] - heaters[j + 1]);
            }
            if (currR < nextR) {
                r = Math.max(r, currR);
                i++;
            } else {
                j++;
            }
        }
        return r;
    }

    // two-pointer v2 (shortened)
    public int findRadius2(int[] houses, int[] heaters) {
        int r = 0, i = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        for (int house : houses) {
            while (i + 1 < heaters.length &&
                   // TODO - why using < will fail test
                   Math.abs(house - heaters[i + 1]) <= Math.abs(house - heaters[i])) {
                i++;
            }
            r = Math.max(r, Math.abs(house - heaters[i]));
        }
        return r;
    }

    public int findRadius3(int[] houses, int[] heaters) {
        int r = 0;
        Arrays.sort(heaters);
        for (int house : houses) {
            int minRadius = findMinRadiusForHouse(house, heaters);
            r = Math.max(r, minRadius);
        }
        return r;
    }

    private int findMinRadiusForHouse(int house, int[] heaters) {
        // heaters passed in must be
        int start = 0, end = heaters.length - 1;
        // Set max val because we will return the shortest radius
        int leftR = Integer.MAX_VALUE, rightR = Integer.MAX_VALUE;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int heater = heaters[mid];
            if (heater == house) return 0;
            if (heater > house) {
                rightR = heater - house;
                end = mid - 1;
            } else {
                leftR = house - heater;
                start = mid + 1;
            }
        }
        return Math.min(leftR, rightR);
    }

    // brutal force O(n^2) - find the largest (shortest house to heater distance)
    public int findRadius4(int[] houses, int[] heaters) {
        int r = 0;
        for (int i = 0; i < houses.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < heaters.length; j++) {
                int diff = Math.abs(houses[i] - heaters[j]);
                if (diff < min) min = diff;
            }
            if (min > r) r = min;
        }
        return r;
    }
}
