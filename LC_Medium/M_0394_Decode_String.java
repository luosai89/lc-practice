package LC_Medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * https://leetcode.com/problems/decode-string/
 * 6/23
 * Stack - 1) use two stacks 2) using recursion and queue
 */
public class M_0394_Decode_String {
    // stack
    public String decodeString1(String s) {
        Stack<String> strings = new Stack<>();
        Stack<Integer> counts = new Stack<>();
        String result = "";
        int index = 0;

        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                int count = 0;
                while (Character.isDigit(s.charAt(index))) {
                    count = count * 10 + s.charAt(index) - '0';
                    index++;
                }
                counts.push(count);
            } else if (s.charAt(index) == '[') {
                // anything before the open bracket does not need to be repeated within the current bracket
                strings.push(result);
                result = "";
                index++;
            } else if (s.charAt(index) == ']') {
                // add the part that does not need to be "repeated"
                StringBuilder sb = new StringBuilder(strings.pop());
                // append the part that needs to be "repeated" based on the count
                int count = counts.pop();
                for (int i = 0; i < count; i++) {
                    sb.append(result);
                }
                // reconstruct result
                result = sb.toString();
                index++;
            } else {
                // build result
                result += s.charAt(index);
                index++;
            }
        }

        return result;
    }

    // recursion + queue
    public String decodeString2(String s) {
        Deque<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            queue.offer(c);
        }
        return helper2(queue);
    }

    public String helper2(Deque<Character> queue) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (Character.isDigit(c)) {
                count = count * 10 + c - '0';
            } else if (c == '[') {
                // call helper on the rest of the queue
                String rest = helper2(queue);
                for (int i = 0; i < count; i++) {
                    sb.append(rest);
                }
                count = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
