package LC_Easy;

/**
 * https://leetcode.com/problems/reverse-string/
 * 5/24/2021
 */

public class E_0344_Reverse_String {
    public void reverseString(char[] s) {
        int front = 0;
        int back = s.length - 1;
        while (front < back) {
            char temp = s[front];
            s[front] = s[back];
            s[back] = temp;
            front++;
            back--;
        }
    }
}
