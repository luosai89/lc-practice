package Others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 7/29 (makeup 7/26)
 */
public class T_0726_02_Common_Search_Terms {
    public static List<String> findCommonSearchTerms(String helpText, List<String> wordsToExclude) {

        Set<String> excludedWordsSet = wordsToExclude.stream()
            .map(String::trim)
            .map(String::toLowerCase)
            .collect(Collectors.toSet());

        // The regex "[^a-z]+" matches one or more non letter characters. Alternatively this could match a list of all
        // whitespace and punctuation characters with a regex like: "( |!|\\?|'|&|,|;|\\.|%|\\*|-|\\^|\\(|\\)|[0-9])+".
        String[] helpTextWords = helpText.toLowerCase().split("[^a-z]+");

        int maxFrequency = 0;
        Map<String, Integer> helpTextWordToFrequency = new HashMap<>();
        for (String helpTextWord : helpTextWords) {
            if (!excludedWordsSet.contains(helpTextWord)) {
                int frequency = helpTextWordToFrequency.getOrDefault(helpTextWord, 0) + 1;
                if (frequency > maxFrequency) {
                    maxFrequency = frequency;
                }
                helpTextWordToFrequency.put(helpTextWord, frequency);
            }
        }

        List<String> commonSearchTerms = new ArrayList<>();
        for (Map.Entry<String, Integer> word : helpTextWordToFrequency.entrySet()) {
            if (word.getValue() == maxFrequency) {
                commonSearchTerms.add(word.getKey());
            }
        }

        Collections.sort(commonSearchTerms);

        return commonSearchTerms;
    }
}
