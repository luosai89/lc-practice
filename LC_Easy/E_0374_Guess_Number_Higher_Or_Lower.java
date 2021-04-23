package LC_Easy;

/**
 * 4/15/2021 https://leetcode.com/problems/guess-number-higher-or-lower/
 */
public class E_0374_Guess_Number_Higher_Or_Lower {
    public int guessNumber(int n) {
        int start = 1;
        int end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int r = guess(mid);
            if (r == 0) return mid;
            if (r == 1) start = mid + 1;
            else end = mid - 1;
        }
        return end;
    }

    /**
     * Just added to make the code compile
     * @param i
     * @return
     */
    public int guess(int i) {
        return 1;
    }
}
