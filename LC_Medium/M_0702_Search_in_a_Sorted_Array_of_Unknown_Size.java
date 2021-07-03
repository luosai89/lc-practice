package LC_Medium;

/**
 * (premium) https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/
 * 7/3 (makeup 7/2)
 * Binary search
 * Given an integer array sorted in ascending order, write a function to search target in nums. If target exists, then
 * return its index, otherwise return -1. However, the array size is unknown to you. You may only access the array using
 * an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed). You may
 * assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will
 * return 2147483647.
 * Example 1:
 *      Input: array = [-1,0,3,5,9,12], target = 9
 *      Output: 4
 *      Explanation: 9 exists in nums and its index is 4
 * Example 2:
 *      Input: array = [-1,0,3,5,9,12], target = 2
 *      Output: -1
 *      Explanation: 2 does not exist in nums so return -1
 * Note:
 *      You may assume that all elements in the array are unique.
 *      The value of each element in the array will be in the range [-9999, 9999].
 */
public class M_0702_Search_in_a_Sorted_Array_of_Unknown_Size {

    // 2-pass binary search (find the range first by doubling the right edge, then regular binary search for target)
    public int search(ArrayReader reader, int target) {
        int left = 0, right = 1;
        while (true) {
            if (target > reader.get(left) && target < reader.get(right)) break;
            if (target == reader.get(left)) return left;
            if (target == reader.get(right)) return right;
            left = right + 1;
            right *= 2;
        }
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) == target) return mid;
            if (reader.get(mid) > target) left = mid;
            else right = mid;
        }
        return reader.get(left) == target ? left : right;
    }

    // Provides ArrayReader;
    public static class ArrayReader {
        private int[] arr;
        public ArrayReader(int[] arr) {
            this.arr = arr;
        }
        public int get(int k) {
            if (k < 0 || k > arr.length) return Integer.MAX_VALUE;
            return arr[k];
        }
    }
}
