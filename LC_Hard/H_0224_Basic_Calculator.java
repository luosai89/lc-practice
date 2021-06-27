package LC_Hard;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 * 6/27*
 * Stack
 */
public class H_0224_Basic_Calculator {
    public int calculate(String s) {
        // algorithm
        // 1) use stack to track the sign before each opening bracket
        // 2) the bracket does not change the order of the calc, but the sign

        // edge case
        if (s == null || s.length() == 0) return 0;

        // variables
        int sign = 1; // track the current sign before pushing on stack
        Stack<Integer> stack = new Stack<>(); // track the signs before each opening bracket
        stack.push(sign); // prime the stack with a positive sign
        int result = 0; // track the final result
        int num = 0; // track the current calculation before added to the result

        // loop through characters in s
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '+' || c == '-') {
                // when we meet a sign, we know we can now safely add the previous number (as its complete)
                // then set the next sign (sign before the bracket x current sign)
                // reset the number for next calculation
                result += (num * sign);
                sign = stack.peek() * (c == '+' ? 1 : -1);
                num = 0;
            } else if (c == '(') {
                stack.push(sign);
            } else if (c == ')') {
                stack.pop();
            }
        }

        return result + num * sign;
    }
}
