package LC_Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * Amazon OA: https://cybergeeksquad.co/2021/06/find-all-combination-of-numbers-that-sum-to-a-target-amazon-oa.html
 * General approach to backtracking: https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
 */
public class M_0039_Combination_Sum {
    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> combinations = new ArrayList<>(); // to collect result combinations
        Arrays.sort(nums); // must sort inputs todo why?
        backtrack(combinations, new ArrayList<>(), nums, target, 0);
        return combinations;
    }

    private static void backtrack(List<List<Integer>> combinations, List<Integer> tempList, int [] nums, int remainingSum, int startIndex){
        // if remaining sum drops below zero, combination not found
        if(remainingSum < 0) return;
        // if remaining sum drops to zero, combination is found (tempList)
        else if(remainingSum == 0) combinations.add(new ArrayList<>(tempList));
        // if remaining sum is above zero, keep looking
        else{
            for(int i = startIndex; i < nums.length; i++){
                tempList.add(nums[i]);
                // startIndex is not i + 1 because we can reuse same elements
                backtrack(combinations, tempList, nums, remainingSum - nums[i], i);
                // once returned (combination found or not found), remove the last element and try the next element         1
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        test(new int[]{2, 3, 6, 7}, 7);
    }

    public static void test(int[] nums, int target) {
        List<List<Integer>> result = combinationSum(nums, target);
        for (int i = 0; i < result.size(); i++) {
            System.out.printf("Combination %d: ", i + 1);
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
