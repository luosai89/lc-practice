package LC_Easy;

/**
 * 4/4/2021 https://leetcode.com/problems/first-bad-version/
 * Runtime and memory usage seemed lower than ideal. Need to check up-voted answers.
 */
public class E_0278_First_Bad_Version {
    public static int firstBadVersion(int n) {
        int lastGood = 0;
        int start = 1;
        int end = n;
        while (start < end) {
            // had to consider the largest int value here! mid turned negative!
            int mid = start/2 + end/2;
            if (isBadVersion(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
                lastGood = mid;
            }
        }
        return lastGood + 1;
    }
    private static boolean isBadVersion(int n) {
        return n >= 1702766719;
    }
    public static void main(String[] args) {
        int result = firstBadVersion(2126753390);
        System.out.println(result);
    }
}
