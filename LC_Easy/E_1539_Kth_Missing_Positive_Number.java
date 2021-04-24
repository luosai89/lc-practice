package LC_Easy;

/**
 * 4/2/2021 https://leetcode.com/problems/kth-missing-positive-number/
 * Alternative methods:
 * Binary search O(logN) https://leetcode.com/problems/kth-missing-positive-number/discuss/779999/JavaC%2B%2BPython-O(logN)
 */
public class E_1539_Kth_Missing_Positive_Number {
    /**
     * My own solution, O(n) & O(1)
     */
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
        // if i != 0, we start with the element at --i, and add the difference between k and incremented n
        if (i > 0) {
            return arr[--i] + (k-n);
        }
        // if i == 0, then it means the kth missing number occurred before index 0
        return k-n;
    }

    /**
     * Preferred solution
     * Lee215's binary search solution, O(logN) & O(1) 左闭右开
     * https://leetcode.com/problems/kth-missing-positive-number/discuss/779999/JavaC%2B%2BPython-O(logN)
     * Notes: when you see sorted, think binary search
     */
    public static int findKthPositiveBinarySearch(int[] arr, int k) {
        int l = 0, r = arr.length, m;
        while (l < r) {         // 左右不相等时
            m = (l + r) / 2;
            if (arr[m] - (m + 1) < k)
                l = m + 1;      // 左闭
            else
                r = m;          // 右开
        }
        return l + k ;
    }

    /**
     * Another O(n) solution that's quite magical
     * https://leetcode.com/problems/kth-missing-positive-number/discuss/876751/Java-1-liner-O(n)-simplest-easy-to-understand-beats-100
     */
    public static int findKthPositiveMagical(int[] arr, int k) {
        for(int i : arr){
            if(i <= k) k++; else break;
        }
        return k;
    }

    public static void main(String[] args) {
        //            0  1  2  3  4
        int[] arr1 = {2, 3, 4, 7, 11};
        int result = findKthPositiveMagical(arr1, 5);
        System.out.println(result);

        int[] arr2 = {1, 2, 3, 4};
        result = findKthPositiveMagical(arr2, 2);
        System.out.println(result);

        int[] arr3 = {2};
        result = findKthPositiveMagical(arr3, 1);
        System.out.println(result);
    }
}
