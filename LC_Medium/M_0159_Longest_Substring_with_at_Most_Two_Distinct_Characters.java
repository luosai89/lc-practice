package LC_Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Premium
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 * 2021: 6/17 (makeup 6/11)
 * Sliding window
 */
public class M_0159_Longest_Substring_with_at_Most_Two_Distinct_Characters {

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int start = 0; int end = 0;
        int result = 0;
        if (s == null || s.isEmpty()) return result;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            char endChar = s.charAt(end++); // alternatively can turn s into a char array
            // if it's an existing char, update freq and result
            map.put(endChar, map.getOrDefault(endChar, 0) + 1); // no need to check map size given while loop
            // if adding the new char increase the map size to 3, move start until map size reduces to 2
            while (map.size() > 2) {
                char startChar = s.charAt(start++);
                map.put(startChar, map.get(startChar) - 1);
                if (map.get(startChar) == 0) map.remove(startChar);
            }
            // now we are ready to compare to max length - no need to track current length in a variable
            result = Math.max(result, end - start);
        }
        return result;
    }

    public static void main(String[] args) {
        String test = "eceba";
        System.out.println(lengthOfLongestSubstringTwoDistinct(test));
        test = "ccaabbb";
        System.out.println(lengthOfLongestSubstringTwoDistinct(test));
        test = "ecececececec";
        System.out.println(lengthOfLongestSubstringTwoDistinct(test));
    }
}
