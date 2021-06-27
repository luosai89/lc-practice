package LC_Medium;

import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-absolute-file-path/
 * 6/26 (hard, need to revisit)
 * Stack: https://www.youtube.com/watch?v=O2NOfAAFYm4&t=3s
 * ArrayDeque (can be used as stack or queue): https://www.youtube.com/watch?v=gXZt4P97UW4
 */
public class M_0388_Longest_Absolute_File_Path {
    public int lengthLongestPath(String input) {
        int maxLen = 0;
        int currLen = 0;

        // tracking lengths of the parts
        Stack<Integer> stack = new Stack<>();

        // calculate the path lengths, backtrack when stack size higher than current level
        // compare to max lengths when we've reached the end (indicated by file extension dot)
        for (String part : input.split("\n")) {
            // # of tabs = # of levels before this part
            // if no tab, # of levels before is 0 (-1 + 1)
            // "\" is not counted
            int tabs = part.lastIndexOf("\t") + 1;

            while (stack.size() > tabs) {
                // pop until it goes back to state before this part
                // i.e. levels above it
                currLen -= stack.pop();
            }

            // length of current part removing tabs, so we do not double count
            int partLen = part.length() - tabs;

            // if current part is a directory (no file extension with dot)
            // push part length to stack
            // and add part length to current length
            if (part.indexOf(".") == -1) {
                stack.push(partLen);
                currLen += partLen;
            } else {
                // compare to max
                // NOTE - we don't want to actually add partLen + tabs to currLen
                // because we'd have to take it off, so just compare and record without changing it
                maxLen = Math.max(maxLen, currLen + partLen + tabs);
            }
        }

        return maxLen;
    }
}
