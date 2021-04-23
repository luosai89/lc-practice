package CTCI;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 * (Page 101-203).
 */
public class C_0101_IsUnique {

    /**
     * Without using extra space
     */
    public static boolean isUnique(String s) {
        if (s == null) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == c) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String test = "";
        System.out.println(test + " is " + isUnique(test));
        test = null;
        System.out.println(test + " is " + isUnique(test));
        test = "aba";
        System.out.println(test + " is " + isUnique(test));
        test = "ab";
        System.out.println(test + " is " + isUnique(test));
    }
}
