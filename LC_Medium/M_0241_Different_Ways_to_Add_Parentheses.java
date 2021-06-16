package LC_Medium;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 * 2021: 6/16
 */
public class M_0241_Different_Ways_to_Add_Parentheses {
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(expression.substring(0,i));
                List<Integer> right = diffWaysToCompute(expression.substring(i+1));
                for (int k : left) {
                    for (int j : right) {
                        if (c == '+') result.add(k + j);
                        else if (c == '-') result.add(k - j);
                        else result.add(k * j);
                    }
                }
            }
        }
        if (result.size() == 0) result.add(Integer.valueOf(expression));
        return result;
    }
}
