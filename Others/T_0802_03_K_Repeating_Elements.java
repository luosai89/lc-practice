package Others;

import java.util.HashMap;
import java.util.Map;

/**
 * Expanding window
 */
public class T_0802_03_K_Repeating_Elements {
    public static int kRepeatingElements(String s, int k) {
        // edge case
        if (s == null || s.length() < 1 || k < 0) return 0;

        // store each char's max count (freq)
        Map<Character, Integer> charFreqMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);
        }

        // find the first window, expand it
        int maxLen = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char newChar = s.charAt(right);
            // if <k char shows up in our window, reset
            if (charFreqMap.get(newChar) < k) {
                right++;
                while (left < right) {
                    char droppingChar = s.charAt(left);
                    if (charFreqMap.get(droppingChar) == 1) charFreqMap.remove(droppingChar);
                    else charFreqMap.put(droppingChar, charFreqMap.get(droppingChar) - 1);
                    left++;
                }
            } else {
                right++;
                maxLen = Math.max(right - left, maxLen);
            }
        }
        return maxLen;
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
    }

    public static void test(String s, int k, int expected) {
        int result = kRepeatingElements(s, k);
        System.out.printf("%s: input %s with %d repeating chars, expected %d, got %d%n",
            result == expected ? "SUCCESS" : "FAIL",
            s, k,
            expected, result
        );
    }
}
