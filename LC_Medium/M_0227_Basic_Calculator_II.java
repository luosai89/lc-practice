package LC_Medium;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 * 6/27* (makeup 5/31)
 * Stack
 */
public class M_0227_Basic_Calculator_II {
    public int calculate(String s) {
        // edge case
        if (s == null || s.length() == 0) return 0;

        // variables
        int result = 0;
        int num = 0;
        char op = '+';
        Stack<Integer> stack = new Stack<>();

        // append any operator, here we use '+'
        // just to trigger the push and calculation on the last number
        s = s + '*';
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
                continue;
            }

            // we don't want to do anything unless we found the next operator
            // which means we must first check if we even found an operator
            if (c == ' ') continue;

            // if c is not space nor number, has to be an operator
            // check current operator and do math, before updating new operator
            if (op == '+') stack.push(num);
            else if (op == '-') stack.push(-num);
            else if (op == '*') stack.push(stack.pop() * num);
            else if (op == '/') stack.push(stack.pop() / num);

            // now we can update the operator and reset num
            op = c;
            num = 0;
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}
