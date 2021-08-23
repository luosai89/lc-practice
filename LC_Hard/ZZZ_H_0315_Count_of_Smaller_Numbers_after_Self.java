package LC_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * https://www.1point3acres.com/bbs/thread-785083-1-1.html
 */
// TODO only the mergesort is faster (beats 88%), the BST version is TLE
public class ZZZ_H_0315_Count_of_Smaller_Numbers_after_Self {
    public static List<Integer> countSmaller(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) == 0) {
            return new ArrayList<>();
        }

        // Merge sort indices instead of values
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        int[] tmpIndices = new int[n];
        int[] counts = new int[n];
        mergeSort(nums, indices, tmpIndices, counts, 0, n - 1);

        List<Integer> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(counts[i]);
        }
        return result;
    }

    private static void mergeSort(int[] nums, int[] indices, int[] tmpIndices, int[] counts, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        mergeSort(nums, indices, tmpIndices, counts, left, mid);
        mergeSort(nums, indices, tmpIndices, counts, mid + 1, right);
        merge(nums, indices, tmpIndices, counts, left, mid, right);
    }

    private static void merge(int[] nums, int[] indices, int[] tmpIndices, int[] counts, int left, int mid, int right) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int rightCount = 0;
        int sortedIndex = left;
        while (leftIndex <= mid && rightIndex <= right) {
            if (nums[indices[leftIndex]] > nums[indices[rightIndex]]) {
                tmpIndices[sortedIndex] = indices[rightIndex];
                // Increase rightCount if left value is bigger than right value
                rightCount++;
                rightIndex++;
            } else {
                tmpIndices[sortedIndex] = indices[leftIndex];
                // If left value is smaller, increase the counts by rightCount found so far
                counts[indices[leftIndex]] += rightCount;
                leftIndex++;
            }
            sortedIndex++;
        }
        while (leftIndex <= mid) {
            // If there are remaining left values, increase their counts by adding rightCount
            tmpIndices[sortedIndex] = indices[leftIndex];
            counts[indices[leftIndex]] += rightCount;
            leftIndex++;
            sortedIndex++;
        }
        if (rightIndex <= right) {
            System.arraycopy(indices, rightIndex, tmpIndices, sortedIndex, right - rightIndex + 1);
        }
        System.arraycopy(tmpIndices, left, indices, left, right - left + 1);
    }

    public static void main(String[] args) {
        test(new int[]{5,2,6,1}, new int[]{2,1,1,0});
        test(new int[]{4,8,5,3,6,2,3,1}, new int[]{4,6,4,2,3,1,1,0});
        test(new int[]{-1}, new int[]{0});
        test(new int[]{-1,-1}, new int[]{0,0});
    }

    private static void test(int[] nums, int[] expected) {
        String resultString = Arrays.toString(countSmaller(nums).toArray());
        String expectedString = Arrays.toString(expected);
        System.out.printf("%s: Given %s, Got %s, Expected %s\n",
            resultString.equals(expectedString) ? "SUCCESS" : "FAIL",
            Arrays.toString(nums), resultString, expectedString);
    }
}
