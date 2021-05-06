package CTCI;

/**
 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
 * P204.
 * Cannot check the sum of chars, because, 0,1,1 = 2 <> 0,2,0 = 2
 * S1 - convert Strings to char[], and sort with Arrays.sort()
 * S2 - best - use int[] as char counter, if a value false below 0, not permutations, Time O(n), Space
 * S3 - mine, use indexOf() to cross check
 */
public class C_0102_CheckPermutation {

    public static boolean checkPermutationS2(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) return false;
        if (a.equals(b)) return true;
        int[] counter = new int[128];
        for (char c : a.toCharArray()) counter[c]++;
        for (char c : b.toCharArray()) {
            counter[c]--;
            // crucial step here, works for both nonexistent char or too many (too few) char
            if (counter[c] < 0) return false;
        }
        return true;
    }

    //Mine. Time O(m*n), Space O(1)
    public static boolean checkPermutationS3(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) return false;
        if (a.equals(b)) return true;
        int ai = 0;
        int bi = 0;
        while (ai < a.length() && bi < b.length()) {
            char ac = a.charAt(ai);
            char bc = b.charAt(bi);
            if (b.indexOf(ac) == -1 || a.indexOf(bc) == -1) return false;
            ai++;
            bi++;
        }
        return true;
    }

    public static void main(String[] args) {
        String a = "abc%.34";
        String b = ".ab%c43";
        System.out.println(a + " and " + b + " are " + checkPermutationS2(a, b));
    }
}
