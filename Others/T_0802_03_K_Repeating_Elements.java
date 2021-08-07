package Others;

import java.util.HashMap;
import java.util.Map;

/**
 * Expanding window
 */
public class T_0802_03_K_Repeating_Elements {
    public static int kRepeatingElements(String s, int k) {
        // TODO - create criteria to shrink window by setting a requirement on max distinct chars
        // Broken down into 26 problems: Find the longest substring with at most n (1-26) distinct characters ...
        // ... each with at least k occurrences.
        int maxLength = 0;
        for (int maxDistinctChars = 0; maxDistinctChars < 26; maxDistinctChars++) {
            int distinctChars = 0;
            int distinctCharsWithKAppearances = 0;
            int start = 0;
            int[] charFreq = new int[26]; // since s will only be lowercase
            for (int end = 0; end < s.length(); end++) {
                // update variables based on new char
                int newCharIndex = s.charAt(end) - 'a';
                if (charFreq[newCharIndex] == 0) {
                    distinctChars++;
                }
                charFreq[newCharIndex]++;
                if (charFreq[newCharIndex] == k) {
                    distinctCharsWithKAppearances++;
                }
                // if we have more distinct chars than max, shrink window
                while (distinctChars > maxDistinctChars) {
                    int dropCharIndex = s.charAt(start) - 'a';
                    if (charFreq[dropCharIndex] == k) {
                        distinctCharsWithKAppearances--;
                    }
                    charFreq[dropCharIndex]--;
                    if (charFreq[dropCharIndex] == 0) {
                        distinctChars--;
                    }
                    start++;
                }
                // if all distinctChars have k appearances, see if it's the longest
                if (distinctChars == distinctCharsWithKAppearances) {
                    maxLength = Math.max(maxLength, end - start + 1);
                }
            }
        }
        return maxLength;
    }

    public static void test(String s, int k, int expected) {
        int result = kRepeatingElements(s, k);
        System.out.printf("%s: In %s, Target %d, Got %d, Expected %d\n", result == expected ? "SUCCESS" : "FAIL",
            s, k, result, expected);
    }

    public static void main(String[] args) {
        test("aaabb", 3, 3);
        test("ababbc", 2, 5);
        test("abcdefg", 1, 7);
        test("abcdefg", 3, 0);
        test("balloon", 2, 4);
        test("abababababababababab", 10, 20);
        test("bbbaaabb", 3, 8);
        test("bbbaaabbcdef", 3, 8);
        test("cdefbbbaaabb", 3, 8);
        test("aacabbb", 3, 3);
        test("abcabcabddeeac", 3, 0);
        test("aabbfba", 3, 0);
    }
}
