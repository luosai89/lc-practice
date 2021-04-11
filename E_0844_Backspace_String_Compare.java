/**
 * 4/6/2021 https://leetcode.com/problems/backspace-string-compare/
 */
public class E_0844_Backspace_String_Compare {
    public static boolean backspaceCompare(String s, String t) {
        int s_pointer = s.length() - 1;
        int t_pointer = t.length() - 1;
        // while the pointer has not reached the first char from the back of either String
        while (s_pointer >= 0 || t_pointer >= 0) {
            int backspaceCount = 0;
            while (s_pointer >= 0 && (backspaceCount > 0 || s.charAt(s_pointer) == '#')) {
                backspaceCount += s.charAt(s_pointer) == '#' ? 1 : -1;
                s_pointer--;
            }
            backspaceCount = 0;
            while (t_pointer >= 0 && (backspaceCount > 0 || t.charAt(t_pointer) == '#')) {
                backspaceCount += t.charAt(t_pointer) == '#' ? 1 : -1;
                t_pointer--;
            }
            // s_pointer or t_pointer could be invalid
            // now we start a clean slate, no backspace and no characters need to be erased
            // if the current s&t chars are the same, move pointer
            // otherwise, the only scenario we could return true is when both pointers are invalid
            if (s_pointer >= 0 && t_pointer >= 0 && s.charAt(s_pointer) == t.charAt(t_pointer)) {
                s_pointer--;
                t_pointer--;
            } else {
                break;
            }
        }
        return s_pointer < 0 && t_pointer < 0;
    }
    public static void main(String[] args) {
        String s = "a";
        String t = "b";
        System.out.println(String.format("Compare %s and %s: %s", s, t, backspaceCompare(s, t)));
        s = "a#";
        t = "b#";
        System.out.println(String.format("Compare %s and %s: %s", s, t, backspaceCompare(s, t)));
        s = "abc#d##e";
        t = "ab#c##de";
        System.out.println(String.format("Compare %s and %s: %s", s, t, backspaceCompare(s, t)));
    }
}
