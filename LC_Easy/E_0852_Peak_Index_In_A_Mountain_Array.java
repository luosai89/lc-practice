package LC_Easy;

/**
 * 4/29/2021 https://leetcode.com/problems/peak-index-in-a-mountain-array/
 */
public class E_0852_Peak_Index_In_A_Mountain_Array {
    // Inefficient, my original method O(n)
    public int peakIndexInMountainArrayN(int[] arr) {
        // front climber and back climber
        int front = 0;
        int back = arr.length - 1;
        // because there's guaranteed to be a mountain, they will meet
        while (front < back) {
            if (arr[front] < arr[front + 1]) front++;
            if (arr[back] < arr[back - 1]) back--;
        }
        return front;
    }

    public int peakIndexInMountainArray(int[] arr) {
        return 0;
    }
}
