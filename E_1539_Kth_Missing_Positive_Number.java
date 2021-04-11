/**
 * 4/2/2021 https://leetcode.com/problems/kth-missing-positive-number/
 */
public class E_1539_Kth_Missing_Positive_Number {
    public static int findKthPositive(int[] arr, int k) {
        int n = 0;
        int i = 0;
        while(i < arr.length) {
            if (arr[i] - (i + 1) < k) {
                n = arr[i] - (i + 1);
                i++;
            } else {
                break;
            }
        }
        // after the while loop, i could very well be 0 (the first missing number occurred before the first ever element)
        // for array questions, important to question could index be out of bound
        // there's a binary search solution that I have not fully comprehended
        if (i > 0) {
            return arr[--i] + (k-n);
        }
        return k-n;
    }
    public static void main(String[] args) {
        int[] arr1 = {2, 3, 4, 7, 11};
        int result = findKthPositive(arr1, 5);
        System.out.println(result);

        int[] arr2 = {1, 2, 3, 4};
        result = findKthPositive(arr2, 2);
        System.out.println(result);

        int[] arr3 = {2};
        result = findKthPositive(arr3, 1);
        System.out.println(result);
    }
}
