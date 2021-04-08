import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 4/8/2021 https://leetcode.com/problems/intersection-of-two-arrays/
 * method 1 - two hash sets + time O(n)
 * method 2 - sort both arrays + one hash set + two pointers
 * method 3 - sort one array + one hash set + binary search
 */
public class E349IntersectionOfTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // O(n)
        Arrays.sort(nums2); // O(n)
        int target, start, end, mid;
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            target = nums1[i];
            start = 0;
            end = nums2.length - 1;
            while (start <= end) {
                mid = (start + end) / 2;
                if (nums2[mid] == target) {
                    result.add(target);
                    break;
                } else if (nums2[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        int count = result.size();
        int[] intersections = new int[count];
        for (Integer n : result) {
            intersections[count - 1] = n;
            count--;
        }
        return intersections;
    }
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] result = intersection(nums1, nums2);
        for (int n : result) {
            System.out.print(n);
        }
        System.out.println();
    }
}
