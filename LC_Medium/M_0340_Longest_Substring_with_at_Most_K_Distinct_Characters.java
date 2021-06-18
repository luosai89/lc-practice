package LC_Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * 2021: 6/17 (makeup 6/7) 
 * Sliding window - related to M159
 */
public class M_0340_Longest_Substring_with_at_Most_K_Distinct_Characters {
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int result = 0;

        // edge case
        if (s == null || s.length() == 0) return result;

        // store distinct char's freq
        Map<Character, Integer> distinct = new HashMap<>();

        // slide the window
        int start = 0, end = 0;
        while (end < s.length()) {
            char ec = s.charAt(end++);
            // add the new character to the map
            distinct.put(ec, distinct.getOrDefault(ec, 0) + 1);
            // if the map is now above capacity, teh streak has ended
            while (distinct.size() > k) {
                char sc = s.charAt(start++);
                // move the start index until we go back down to k distinct chars
                distinct.put(sc, distinct.get(sc) - 1);
                if (distinct.get(sc) == 0) distinct.remove(sc);
            }
            result = Math.max(result, end - start);
        }

        return result;
    }

    public static void main(String[] args) {
        String test = "eceba";
        int k = 2;
        System.out.println(lengthOfLongestSubstringKDistinct(test, k));
        test = "aa";
        k = 1;
        System.out.println(lengthOfLongestSubstringKDistinct(test, k));
    }
}
