package LC_Amazon;

import java.util.Arrays;

public class M_1395_Count_Number_of_Teams {

    // brute force
    public static int numTeams(int[] rating) {
        int count = 0;
        int n = rating.length;
        for (int j = 0; j < n; j++) {
            int leftSmaller = 0, rightLarger = 0;
            int leftLarger = 0, rightSmaller = 0;
            // count numbers to the left that are ...
            for (int i = 0; i < j; i++) {
                if (rating[i] < rating[j]) { // ... smaller
                    leftSmaller++;
                } else if (rating[i] > rating[j]) { // ... larger
                    leftLarger++;
                }
            }
            // count numbers to the right that are ...
            for (int k = j + 1; k < n; k++) {
                if (rating[j] < rating[k]) { // ... smaller
                    rightLarger++;
                } else if (rating[j] > rating[k]) { // ... larger
                    rightSmaller++;
                }
            }
            count += leftSmaller * rightLarger + leftLarger * rightSmaller;
        }

        return count;
    }
    public static void test(int[] rating, int expected) {
        int result = numTeams(rating);
        System.out.printf("%s: Given %s, Got %d, Expected %d\n",
            result == expected ? "SUCCESS":"FAIL",
            Arrays.toString(rating), result, expected);
    }
    public static void main(String[] args) {
        test(new int[]{2,5,3,4,1}, 3);
        test(new int[]{2,1,3}, 0);
        test(new int[]{1,2,3,4}, 4);
        //test(new int[]{}, );
    }
}
