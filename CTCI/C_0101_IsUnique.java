package CTCI;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 * P203
 * TODO - For String/char questions, ask the interviewer first if it's ASCII or unicode.
 * S1 - given ASCII String, use a boolean[], time O(n), space O(1)
 * S2 - bit vector -> not getting into now
 * S3 - compare with every other character, time O(n^2), space O(1) without extra data structure
 * S4 - if modifying String allowed, sort it with time O(n*logn), however could take extra space
 */
public class C_0101_IsUnique {

    /**
     * Space O(1) with additional data structure. Time is arguably O(1) - won't be more than char set (c). But you could say O(min(n, c)).
     */
    public static boolean isUniqueS1(String s) {
        // TODO - if there's more characters than max unique in ASCII, there has to be duplicates
        if (s.length() > 128) {
            return false;
        }
        // TODO - simplicity using boolean[] instead of int[]
        boolean[] charSet = new boolean[128];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charSet[c]) return false;
            else charSet[c] = true;
        }
        return true;
    }

    /**
     * Without using extra space
     */
    public static boolean isUniqueS3(String s) {
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
        System.out.println(test + " is " + isUniqueS1(test));
        test = null;
        System.out.println(test + " is " + isUniqueS1(test));
        test = "aba";
        System.out.println(test + " is " + isUniqueS1(test));
        test = "ab";
        System.out.println(test + " is " + isUniqueS1(test));
    }
}
