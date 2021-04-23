package LC_Easy;

/**
 * 4/13/2021 https://leetcode.com/problems/valid-perfect-square/
 * Side knowledge: 1 + 3 + 5 + 7 + ... 2n - 1 = n^2 -> O(n^1/2), not as efficient as O(longn)
 * INT OVERFLOW: Use long, or num/mid to avoid mid^2 (must take care of mid == 0 case)
 */
public class E_0367_Valid_Perfect_Square {
    public static boolean isPerfectSquare(int num) {
        long start = 0;
        long end = num;
        while (start <= end) {
            long mid = (start + end) / 2;
            long midSquared = mid * mid;
            if (midSquared == num) return true;
            if (midSquared > num) end = mid - 1;
            else start = mid + 1;
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(isPerfectSquare(2147483647));
    }
}
