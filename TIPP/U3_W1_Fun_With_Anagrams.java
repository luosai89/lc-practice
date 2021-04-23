package TIPP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class U3_W1_Fun_With_Anagrams {
    public static List<String> funWithAnagrams(List<String> text) {
        // Write your code here
        // space O(n)
        ArrayList<Integer> stringValues = new ArrayList<>();
        // time O(m*n)
        for (int i = 0; i < text.size(); i++) {
            String str = text.get(i);
            int charSum = 0;
            for (int ch = 0; ch < str.length(); ch++) {
                charSum += str.charAt(ch);
            }
            if (stringValues.contains(charSum)) {
                text.remove(i--);
            } else {
                stringValues.add(charSum);
            }
        }
        // time O(n)
        Collections.sort(text);
        return text;
    }

    public static List<String> funWithAnagramsSolution(List<String> text) {
        // Tracks the sorted character strings we've already seen in the input list.
        Set<String> seen = new HashSet<>();

        // Stores the result list containing the first occurence of an anagram.
        List<String> noAnagrams = new ArrayList<>();

        for (String word : text) {
            // Sort the characters in the word so that all anagrams result in the same sorted string.
            char[] charactersInWord = word.toCharArray();
            Arrays.sort(charactersInWord);
            String sorted = new String(charactersInWord);

            // Check if this sequences of characters has been seen before.
            if (!seen.contains(sorted)) {
                // Add the original unsorted word into the result list.
                noAnagrams.add(word);
                // Add the sorted characters into the set to check for later occurrences of anagrams.
                seen.add(sorted);
            }
        }

        // Sort the result list in ascending order.
        Collections.sort(noAnagrams);
        return noAnagrams;
    }

    public static void main(String[] args) {
        List<String> text = new ArrayList<>();
        text.add("code");
        text.add("aaagmnrs");
        text.add("anagrams");
        text.add("doce");
        List<String> result = funWithAnagrams(text);
        for (String s : result) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
