package LC_Easy;

import java.util.Arrays;

/**
 * 8/11 (1)
 * https://leetcode.com/problems/maximum-units-on-a-truck/
 * Amazon OA
 */
public class E_1710_Fill_The_Truck {
    // asking for max UNITS, truck size measured in BOXES
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        // TODO sort descending by # units in each box in order to maximize
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int max = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            int numBoxes = boxTypes[i][0];
            int unitsPerBox = boxTypes[i][1];
            if (truckSize >= numBoxes) {
                truckSize -= numBoxes;
                max += (numBoxes * unitsPerBox);
            } else {
                max += truckSize * unitsPerBox;
                return max; // TODO important to return max here. Otherwise it will keep looping through the rest
            }
        }
        return max;
    }
}
