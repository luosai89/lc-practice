package LC_Easy;

/**
 * 4/9/2021 https://leetcode.com/problems/n-th-tribonacci-number/
 */

public class E_1137_Nth_Tribonacci_Number {
    public int tribonacci(int n) {
        if (n < 3) {
            return n == 0 ? 0 : 1;
        }
        int first = 0;
        int second = 1;
        int third = 1;
        int t = 4;
        int sum = first + second + third;
        while (t <= n) {
            first = second;
            second = third;
            third = sum;
            sum = first + second + sum;
            t++;
        }
        return sum;
    }
}
