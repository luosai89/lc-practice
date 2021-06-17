package LC_Medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * 2021: 6/17*
 * Sliding window
 */
public class M_0438_Find_All_Anagrams_in_a_String {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();

        // corner case
        if (s.length() < p.length()) return result;

        // store p
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        // slide through s
        int start = 0, end = 0;
        while (end < s.length()) {
            // check each char 1 by 1
            char endChar = s.charAt(end++);
            // if this char is a target char, reduce its freq in map
            if (map.containsKey(endChar)) {
                map.put(endChar, map.get(endChar) - 1);
                // if we have found all freq of a target char, reduce number of target char left to find
                if (map.get(endChar) == 0) counter--;
            }
            // if this char is NOT a target char, do nothing and move along
            // if all target char have been found, let's see if there's an anagram between start and end
            while (counter == 0) {
                // if the window is the anagram (target length and all target chars accounted for), we can add to result
                if (end - start == p.length()) result.add(start);
                // if the window length is different, there could be two scenarios:
                //     1) there's no anagram in it, the target chars found were not consecutive
                //     2) there's an anagram immediately preceding the end pointer,
                //        but the start pointer has not been updated to the real start of the anagram
                // so we need to examine front chars 1 by 1 (while counter == 0) until we found one of the target char
                // even if the current window is the anagram, we'd still need to "slide" the window anyways
                char startChar = s.charAt(start++);
                // if the current front char is a target char, update the frequency
                if (map.containsKey(startChar)) {
                    map.put(startChar, map.get(startChar) + 1);
                    // if after frequency addition, its still 0 or less, then it's just an extra
                    if (map.get(startChar) > 0) counter++;
                }
            }
            // now that counter is no longer zero, we are checking on a new window with a new start and new end (if valid)
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "caabcbacab";
        String t = "abc";
        List<Integer> results = findAnagrams(s, t);
        for (int i : results) {
            System.out.print(i + " ");
        }
    }
}
