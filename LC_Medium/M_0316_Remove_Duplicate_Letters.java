package LC_Medium;

/**
 * https://leetcode.com/problems/remove-duplicate-letters/
 * 6/24 (makeup 6/2)
 * Stack
 */
public class M_0316_Remove_Duplicate_Letters {
    public String removeDuplicateLetters(String s) {
        // edge case
        if (s == null || s.length() == 0) return s;

        // find last occurrences
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        // build the result
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            int l = sb.length();
            char currChar = s.charAt(i);
            if (used[currChar - 'a']) continue;
            while (l > 0 && sb.charAt(l - 1) > currChar && lastIndex[sb.charAt(l - 1) - 'a'] > i) {
                used[sb.charAt(l - 1) - 'a'] = false;
                sb.deleteCharAt(l - 1);
            }
            sb.append(currChar);
            used[currChar - 'a'] = true;
        }
        return sb.toString();
    }
}
