package LC_Medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 2021: 8/28
 * https://leetcode.com/problems/evaluate-division/
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi]
 * and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer
 * for Cj / Dj = ?. Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and
 * that there is no contradiction.
 */
public class M_0399_Evaluate_Division {

    // TODO 方法1： DFS + HashMap

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // numer和denom角色可互换（只是结果不同），可以公用一个map，不需分开
        // Space O(2e) 有 e 个equations, 就有 2e 个map entries(numer和denom分别当key)
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String numer = equations.get(i).get(0);
            String denom = equations.get(i).get(1);

            map.putIfAbsent(numer, new HashMap<>());
            map.putIfAbsent(denom, new HashMap<>());

            map.get(numer).put(denom, values[i]);
            map.get(denom).put(numer, 1 / values[i]);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String numer = queries.get(i).get(0);
            String denom = queries.get(i).get(1);
            // quotient从1开始（因为是除法不影响结果），所有的query都可以共用map，但是每个query必须有新的seen set
            res[i] = dfs(numer, denom, 1, map, new HashSet<>());
        }

        return res;
    }

    public static double dfs(String numer, String denom, double quotient,
                             Map<String, Map<String, Double>> map, Set<String> seen) {
        // 如果整个map里就没有numer能除的数，那肯定没有答案
        // 反之，如果map里还有numer能除的数，那么我们把这个numer加到seen里，只要能加，就是没见过，开始探索，
        // 如果整个map里有numer能除的数，但是这个numer我们已经试过了（没有return说明在这个numer的树里找不到答案）
        if (!map.containsKey(numer) || !seen.add(numer)) return -1;

        // 找到了 -- 这里是不是应该换成 map.get(number).containsKey(denom) 会更有效率些？TODO
        if (numer.equals(denom)) return quotient;

        // 还在找 - 如果numer不能直接除denom，看看number有哪个denom可以直接除denom
        Map<String, Double> denoms = map.get(numer);
        for (String thisDenom : denoms.keySet()) {
            //                  找什么      给谁找         积累什么                        在哪儿找  找到过没有
            //                  给denom找numer            quotient，每找到一个就乘上        公用map  每个query特有的seen
            double result = dfs(thisDenom, denom, quotient * denoms.get(thisDenom), map, seen);

            // 怎么处理结果：
            // 结果要么是 -1 （没找到），要么是 quotient（找到了）
            // 如果找到了，就不用再试剩下的了，立即返回
            // 如果没找到，就去试下一个demom，看他的树里有没有目标denom的numer
            if (result != -1) {
                return result;
            }
        }

        // 如果每个都试过了还是没有找到，那么就真的没找到
        return -1;
    }


    public static void test(List<List<String>> equations, double[] values, List<List<String>> queries, double[] expected) {
        double[] result = calcEquation(equations, values, queries);
        System.out.printf("%s: Given %s. Got %s. Expected %s.", Arrays.toString(result).equals(Arrays.toString(expected)),
            Arrays.toString(values), Arrays.toString(result), Arrays.toString(expected));
    }

    public static void main(String[] args) {
        // lc example 1
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = new double[]{2.0, 3.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"),
                                                   Arrays.asList("a", "e"), Arrays.asList("a", "a"),
                                                   Arrays.asList("x", "x"));
        double[] expected = new double[]{6.00000, 0.50000, -1.00000, 1.00000, -1.00000};
        test(equations, values, queries, expected);

        // lc example 2
        equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("bc", "cd"));
        values = new double[]{1.5, 2.5, 5.0};
        queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("c", "b"), Arrays.asList("bc", "cd"),
                                Arrays.asList("cd", "bc"));
        expected = new double[]{3.75000, 0.40000, 5.00000, 0.20000};
        test(equations, values, queries, expected);

        // lc example 3
        equations = Collections.singletonList(Arrays.asList("a", "b"));
        values = new double[]{0.5};
        queries = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "a"), Arrays.asList("a", "c"),
                                Arrays.asList("x", "y"));
        expected = new double[]{0.50000, 2.00000, -1.00000, -1.00000};
        test(equations, values, queries, expected);
    }
}
