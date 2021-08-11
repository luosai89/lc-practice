package LC_Amazon;

import java.util.Arrays;

public class Zui_xiao_zu_zhuang_shi_jian {
    public static int connectSticks(int[] sticks) {
        // TODO IMPORTANT, if stick length is 1, no connection needed, cost still 0
        if (sticks == null || sticks.length < 2) return 0;
        // earlier cost will affect later cost, so must connect cheaper first
        // sort sticks based on length
        Arrays.sort(sticks);
        // variable tracking total cost, initiated with first two costs added
        // variable tracking last cost, initiated with first two costs added
        int minTotalCost = sticks[0] + sticks[1];
        int lastCost = minTotalCost;
        // starting third stick
        for (int i = 2; i < sticks.length; i++) {
            // temp cost = last cost + new cost
            lastCost += sticks[i];
            // last cost = temp cost
            // total cost += temp cost;
            minTotalCost += lastCost;
        }
        // return total cost
        return minTotalCost;
    }

    public static void test(int[] sticks, int expected) {
        int result = connectSticks(sticks);
        System.out.printf("%s: Given %s, Got %d, Expected %d\n", result == expected ? "SUCCESS" : "FAIL",
            Arrays.toString(sticks), result, expected);
    }

    public static void main(String[] args) {
        test(new int[]{2,4,3}, 14);
        test(new int[]{1,8,3,5}, 30);
    }
}
