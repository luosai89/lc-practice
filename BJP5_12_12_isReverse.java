/**
 * https://practiceit.cs.washington.edu/problem/view/bjp5/chapter12/e12-isReverse
 * Determine the base case
 */
public class BJP5_12_12_isReverse {
    public boolean isReverse(String a, String b) {
        int al = a.length(), bl = b.length();
        if (al != bl) return false;
        if (al <= 1 || bl <= 1) return a.equals(b);
        a = a.toLowerCase();
        b = b.toLowerCase();
        if (a.charAt(0) != b.charAt(b.length() - 1)) return false;
        return isReverse(a.substring(1), b.substring(0, bl - 1));
    }
}
