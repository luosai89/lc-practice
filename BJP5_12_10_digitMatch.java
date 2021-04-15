/**
 * https://practiceit.cs.washington.edu/problem/view/bjp5/chapter12/e10-digitMatch
 * Define base case correctly
 */
public class BJP5_12_10_digitMatch {
    public int digitMatch(int a, int b) {
        if (a < 0 || b < 0) throw new IllegalArgumentException();
        if (a < 10 || b < 10) {
            return a % 10 == b % 10 ? 1 : 0;
        }
        int n = a % 10 == b % 10 ? 1 : 0;
        return n + digitMatch(a / 10, b / 10);
    }
}
