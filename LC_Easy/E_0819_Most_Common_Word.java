package LC_Easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 5/5/2021 https://leetcode.com/problems/most-common-word/
 */
public class E_0819_Most_Common_Word {
    /**
     * My original method - too long....
     */
    public static String mostCommonWord1(String paragraph, String[] banned) {

        Map<String, Integer> freq = new HashMap<>();
        StringBuilder bannedsb = new StringBuilder();
        for (String s : banned) {
            bannedsb.append(s);
            bannedsb.append(" ");
        }
        String allBanned = bannedsb.toString().toLowerCase();

        StringBuilder sb = new StringBuilder();
        paragraph = paragraph.toLowerCase();

        int max = 0;
        String result = "";

        for (int i = 0; i < paragraph.length(); i++) {
            char c = paragraph.charAt(i);
            if (!Character.isLetter(c) && sb.length() > 0) {
                String word = sb.toString();
                sb = new StringBuilder();
                // TODO - problem here is indexOf has O(m*n) complexity
                if (allBanned.indexOf(word) == -1) {
                    freq.put(word, freq.getOrDefault(word, 0) + 1);
                    if (freq.get(word) > max) {
                        max = freq.get(word);
                        result = word;
                    }
                }
            } else if (Character.isLetter(c)) {
                sb.append(c);
            }
        }

        if (sb.length() > 0) {
            String lastWord = sb.toString();
            if (allBanned.indexOf(lastWord) == -1 && freq.getOrDefault(lastWord, 0) + 1 > max) {
                result = lastWord;
            }
        }
        return result;
    }

    /**
     * Lee215
     */
    public String mostCommonWord2(String p, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> count = new HashMap<>();
        // TODO - \\W+ is anything that's NOT alphanumeric
        // TODO - \\s+ is multiple white spaces
        String[] words = p.replaceAll("\\W+" , " ").toLowerCase().split("\\s+");
        for (String w : words) if (!ban.contains(w)) count.put(w, count.getOrDefault(w, 0) + 1);
        // TODO - a way quicker way to find the max value from the map
        return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    /**
     * Similar to my method but improved - eliminate the final check and uses a set
     */
    public String mostCommonWord3(String paragraph, String[] banned) {

        Set<String> bannedWords = new HashSet();
        for (String word : banned)
            bannedWords.add(word);

        String ans = "";
        int maxCount = 0;
        Map<String, Integer> wordCount = new HashMap();
        StringBuilder wordBuffer = new StringBuilder();
        char[] chars = paragraph.toCharArray();

        for (int p = 0; p < chars.length; ++p) {
            char currChar = chars[p];

            // 1). consume the characters in a word
            if (Character.isLetter(currChar)) {
                wordBuffer.append(Character.toLowerCase(currChar));
                if (p != chars.length - 1)
                    // skip the rest of the processing
                    continue;
            }

            // 2). at the end of one word or at the end of paragraph
            if (wordBuffer.length() > 0) {
                String word = wordBuffer.toString();
                // identify the maximum count while updating the wordCount table.
                if (!bannedWords.contains(word)) {
                    int newCount = wordCount.getOrDefault(word, 0) + 1;
                    wordCount.put(word, newCount);
                    if (newCount > maxCount) {
                        ans = word;
                        maxCount = newCount;
                    }
                }
                // reset the buffer for the next word
                wordBuffer = new StringBuilder();
            }
        }
        return ans;
    }
}
