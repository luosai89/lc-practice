package LC_Medium;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 2021: 6/17 (makeup 6/9)
 */
public class M_0003_Longest_Substring_without_Repeating_Characters {

    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        // corner case
        if (s == null || s.isEmpty()) return result;

        // record streaks
        Set<Character> set = new HashSet<>();

        // slide the window
        int start = 0, end = 0;
        while (end < s.length()) {
            char endChar = s.charAt(end++);
            if (!set.contains(endChar)) {
                set.add(endChar);
                result = Math.max(result, set.size());
            } else {
                while (start < end) {
                    char startChar = s.charAt(start++);
                    if (startChar == endChar) break;
                    set.remove(startChar);
                }
            }
        }
        return result;
    }

}
