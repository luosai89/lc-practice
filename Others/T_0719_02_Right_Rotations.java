package Others;

/**
 * Write an algorithm to find if a given pair of strings are right rotations of one another.
 * 7/27 (makeup 7/26)
 */
public class T_0719_02_Right_Rotations {
    public static int checkRightRotationsWithoutAllocatingExtraSpace(String word1, String word2) {
        // Edge cases
        // Both strings should be non-null and of same length to be rightRotations of one another
        if (word1 == null || word2 == null || word1.length() != word2.length()) {
            return -1;
        }

        final int n = word1.length();
        // Marks the starting position of word1 from where the match to word2 begins
        int startPosWord1 = 0;
        // loop variables
        int i = 0;
        int j = 0;

        // Terminate loop if no characters left in word1 to start another match with word2
        // Or if all the characters in word2 are traversed
        while (startPosWord1 < n && j < n) {
            // MATCH found
            if (word1.charAt(i % n) == word2.charAt(j)) {
                // Increment index values to check for consecutive characters match in word1 and word2
                i++;
                j++;
            } else {
                //Reset values
                i = ++startPosWord1;
                j = 0;
            }
        }
        // Return 1 if word2 is traversed completely
        return j == n ? 1 : -1;
    }

    public static int checkRightRotationsWithAllocatingExtraSpace(String word1, String word2) {
        // Edge cases
        // Both strings should be non-null and of same length to be rightRotations of one another
        if (word1 == null || word2 == null || word1.length() != word2.length()) {
            return -1;
        }

        // Construct a new string unwrappedWord1 by concatenating word1 twice
        // The resultant string is a string which will always contain all the possible right rotations of word1
        String unwrappedWord1 = word1 + word1;
        // Simply check if unwrappedWord1 contains the word2
        // If it contains then they both are right rotations of one another
        return unwrappedWord1.contains(word2) ? 1 : -1;
    }
}
