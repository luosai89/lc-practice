package LC_Amazon;

import java.util.Arrays;

/**
 * 8/9 （makeup 7/14)
 */
public class Zhao_Zu {
    public static int zhaoZu(int renShu, int[] jiBie, int xuQiu, int diDuan, int gaoDuan) {
        // First find out how many associates are eligible - time O(n)
        int eligibleAssociates = 0;
        for (int skill : jiBie)
            if (skill >= diDuan && skill <= gaoDuan)
                eligibleAssociates++;

        // If insufficient valid associates, stop
        if (eligibleAssociates < xuQiu) return 0;

        // TODO - KEY is to construct the factorial array for quick look up on factorial results
        // Two advantages to have factorial capacity of n + 1 instead of n
        // ... 1) consistent for loop strategy to construct the factorial array below
        // ... 2) easy look up for 0! at 0-index
        int[] factorial = new int[eligibleAssociates + 1];
        factorial[0] = 1;
        for (int i = 1; i <= eligibleAssociates; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        int teams = 0;
        // TODO - Combination formula to find EXACTLY r among n: n! / (r! * (n-r)!)
        //  ... Because this problem looks for AT LEAST, we need to loop through the difference
        for (int i = xuQiu; i <= eligibleAssociates; i++) // i between (3,4)
            teams += factorial[eligibleAssociates] / (factorial[i] * factorial[eligibleAssociates - i]);
        // factorial(4) / factorial(3) * factorial(4 - 3) -> find exactly 3 in 4
        // factorial(4) / factorial(4) * factorial(4 - 4) -> find exactly 4 in 4

        return teams;
    }

    public static void test(int num, int[] skills, int minAssociates, int minLevel, int maxLevel, int expected) {
        int result = zhaoZu(num, skills, minAssociates, minLevel, maxLevel);
        System.out.printf("%s: Given %s, Min %d (%d to %d), Got %d, Expected %d\n",
            result == expected ? "SUCCESS" : "FAIL",
            Arrays.toString(skills), minAssociates, minLevel, maxLevel, result, expected);
    }

    public static void main(String[] args) {
        test(6, new int[]{12,4,6,13,5,10}, 3,4,10,5);
        test(6, new int[]{4,5,6,10,12,13}, 2,4,10,4);
    }

}
