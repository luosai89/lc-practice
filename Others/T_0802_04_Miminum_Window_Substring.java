package Others;

/**
 * 8/6 expanding window problem solving problem homework
 */
public class T_0802_04_Miminum_Window_Substring {
    public static String minimumWindowSubstring(String s, String t) {
        // TODO must use 128 -> return substring as it is, case matters
        int[] targetCharFreq = new int[128];
        // Build a count of the characters we need to avoid O(n) lookup in validation
        for (int i = 0; i < t.length(); i++) {
            targetCharFreq[t.charAt(i)]++;
        }

        int remainingTargetChar = t.length();
        int startIdxOfSmallestWindow = 0;
        int endIdxOfSmallestWindow = -1;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            // Look for target char, update its frequency and remaining char tally
            int newChar = s.charAt(end);
            if (targetCharFreq[newChar] > 0) {
                remainingTargetChar--;
            }
            // TODO must reduce freq for all chars target or not - those negative are def NOT target chars
            targetCharFreq[s.charAt(end)]--;
            // While we have a valid window, assess if its smallest
            while (remainingTargetChar == 0) {
                // TODO do not forget to also update when it's the first window found
                if (end - start < endIdxOfSmallestWindow - startIdxOfSmallestWindow || endIdxOfSmallestWindow == -1) {
                    // if smallest, 1) update startIdx and length of smallest window ...
                    startIdxOfSmallestWindow = start;
                    endIdxOfSmallestWindow = end;
                }
                // ... 2) shrink the window until we are 1 char away from a new valid window
                int dropChar = s.charAt(start);
                targetCharFreq[dropChar]++;
                // TODO non-target chars will NEVER be recovered back to above 0.
                if (targetCharFreq[dropChar] > 0) {
                    remainingTargetChar++;
                }
                start++;
            }
        }
        if (endIdxOfSmallestWindow == -1) return "";
        return s.substring(startIdxOfSmallestWindow, endIdxOfSmallestWindow + 1);
    }

    public static void main(String[] args) {
        test("ADOBECODEBANC", "ABC", "BANC");
        test("a", "a", "a");
        test("a", "aa", "");
        test("testing", "gnt", "ting");
        test("bananas", "bs", "bananas");
        test("CaSiNg", "c", "");
        test("community", "mom", "omm");
    }

    public static void test(String s, String t, String expected) {
        String result = minimumWindowSubstring(s, t);
        System.out.printf("%s: In %s, find %s, Got %s, Expected %s\n", result.equals(expected) ? "SUCCESS" : "FAIL",
            s, t, result, expected);
    }
}
