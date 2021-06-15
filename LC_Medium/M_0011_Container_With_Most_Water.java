package LC_Medium;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * 2021: 6/13
 * 1. two-pointer https://leetcode.com/problems/container-with-most-water/discuss/6099/Yet-another-way-to-see-what-happens-in-the-O(n)-algorithm
 */
public class M_0011_Container_With_Most_Water {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int max = 0;
        while (i < j) {
            int area = (j - i) * Math.min(height[j], height[i]);
            max = Math.max(max, area);
            if (height[i] < height[j]) i++;
            else j--;
        }
        return max;
    }
}
