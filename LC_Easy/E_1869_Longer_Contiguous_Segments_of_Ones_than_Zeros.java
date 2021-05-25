package LC_Easy;

/**
 * https://leetcode.com/problems/longer-contiguous-segments-of-ones-than-zeros/
 * 5/24/2021
 */
public class E_1869_Longer_Contiguous_Segments_of_Ones_than_Zeros {
    public boolean checkZeroOnes(String s) {
        int count0 = 0, count1 = 0, max0 = 0, max1 = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '0') {
                count1 = 0;
                count0++;
                max0 = Math.max(max0, count0);
            } else {
                count0 = 0;
                count1++;
                max1 = Math.max(max1, count1);
            }
        }
        return (max1 > max0);
    }
}
