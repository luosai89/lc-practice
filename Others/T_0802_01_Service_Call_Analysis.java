package Others;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 7/30
 * Tipp
 */
public class T_0802_01_Service_Call_Analysis {
    public static List<String> distinctCharacterSubstrings(String s, int k) {
        // Write your code here
        Set<String> distCharStrings = new HashSet<>();
        // edge cases
        if (s == null || s.isEmpty() || k < 0) {
            return Collections.emptyList();
        }
        // map to track k-length string chars
        Map<Character, Integer> charFreqMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char newChar = s.charAt(i);
            charFreqMap.put(newChar, charFreqMap.getOrDefault(newChar, 0) + 1);
            if (i >= k) {
                char charToDrop = s.charAt(i - k);
                if (charFreqMap.get(charToDrop) == 1) {
                    charFreqMap.remove(charToDrop);
                } else {
                    charFreqMap.put(charToDrop, charFreqMap.get(charToDrop) - 1);
                }
            }
            if (i + 1 >= k && charFreqMap.size() == k) {
                distCharStrings.add(s.substring(i + 1 - k, i + 1));
            }
        }
        List<String> uniqueStrings = new LinkedList<>(distCharStrings);
        Collections.sort(uniqueStrings);
        return uniqueStrings;
    }
}
