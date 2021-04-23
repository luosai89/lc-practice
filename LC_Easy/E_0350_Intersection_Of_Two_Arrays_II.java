package LC_Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 4/12/2021 https://leetcode.com/problems/intersection-of-two-arrays-ii/
 * Find all common elements among two unsorted arrays, including duplicates
 *
 * Time O(n) & Space O(n) : store elements from one array in Map with frequency, check against the other array.
 *
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class E_0350_Intersection_Of_Two_Arrays_II {
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        // store nums1 value and frequencies in the map
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }
        // check nums2 values against the map
        for (int i = 0; i < nums2.length; i++) {
            int n = nums2[i];
            if (map.containsKey(n)) {
                int freq = map.get(n);
                result.add(n);
                if (freq == 1) {
                    map.remove(n);
                } else {
                    map.put(n, freq - 1);
                }
            }
        }
        // convert result list to array
        int[] intersections = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            intersections[i] = result.get(i);
        }
        return intersections;
    }
    public static int[] intersectAssumeSorted(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, l1 = nums1.length, l2 = nums2.length;
        ArrayList<Integer> result = new ArrayList<>();
        while (i < l1 && j < l2) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            }
        }
        int[] r = new int[result.size()];
        for (int k = 0; k < result.size(); k++) {
            r[k] = result.get(k);
        }
        return r;
    }
}
