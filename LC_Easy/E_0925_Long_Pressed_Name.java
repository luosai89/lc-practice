package LC_Easy;

/**
 * 4/11/2021 https://leetcode.com/problems/long-pressed-name/
 * Two-pointer
 */
public class E_0925_Long_Pressed_Name {
    public static boolean isLongPressedName(String name, String typed) {
        int n = 0;
        int t = 0;
        char prevChar;
        while (n < name.length()) {
            if (t == typed.length()) return false;
            if (name.charAt(n) != typed.charAt(t)) {
                if (n == 0) return false;
                prevChar = name.charAt(n-1);
                if (typed.charAt(t) != prevChar) return false;
            } else {
                n++;
            }
            t++;
        }
        prevChar = name.charAt(n-1);
        while (t < typed.length()) {
            if (typed.charAt(t) != prevChar) return false;
            t++;
        }
        return true;
    }

    // Lee215 solution
    public boolean isLongPressedNameSolution(String name, String typed) {
        int i = 0, m = name.length(), n = typed.length();
        // traversing through typed automatically via ++j
        // TODO - loop through j because we need to check until the last char of typed, but not necessarily for name.
        // TODO - also, you want to increment j regardless (except when return false is needed), but not necessarily for i
        for (int j = 0; j < n; j++)
            // when name has not reached the end via i, && the two characters are the same --> move i (and move j)
            if (i < m && name.charAt(i) == typed.charAt(j))
                i++;
            // if name has ended or if the two characters are different, && if j is at 0 or if j is different from prev
            // --> has to be false
            else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1))
                return false;
            // if j finished before i reached the end, it has to be false, otherwise its ok
        return i == m;
    }
}
