package LC_Easy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 */
public class E_0020_Valid_Parentheses {
    public boolean isValid(String text) {
        if (text == "") return true;
        if (text == null) return false;
        // the deque will have nothing but opening
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : text.toCharArray()) {
            if (isClosing(c)) {
                if (deque.isEmpty()) return false;
                if (!isMatching(deque.pop(), c)) return false;
            } else {
                deque.push(c);
            }
        }
        return deque.isEmpty();
    }

    private boolean isClosing(char c) {
        return c == ')' || c == '}' || c == ']';
    }

    private boolean isMatching(char opening, char closing) {
        boolean result;
        return (opening == '(' && closing == ')')
            || (opening == '{' && closing == '}')
            || (opening == '[' && closing == ']');
    }
}
