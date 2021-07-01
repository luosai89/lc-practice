package LC_Medium;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 * 6/30**
 *
 */
public class M_0287_Find_the_Duplicate_Number {

    // Binary search - O(n * logn)
    public static int findDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            // The usage of left, right, and mid index here, is not really for the values at those indexes
            // ... but rather the numbers equating to that index
            // ... that's why we are returning the index (representing the number of its equal)
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int n : nums) {
                // NOTE comparing to mid, not nums[mid]
                if (n <= mid) count++;
            }
            // If the mid index has more count of numbers at or below the index value
            // ... then the answer must be numbers equating to equal or lesser index
            if (count > mid) right = mid;
            else left = mid;
        }
        // Right is ALWAYS the right answer
        return right;
    }

    // Floyd cycle detection - O(n)
    public static int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        // First iteration - proving a loop exists and finding the meeting point
        // ... one slow, one fast
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) break;
        }
        // Second iteration - finding the starting point of the loop
        // ... both moving at the same pace
        // ... we need to find the entrance of the loop, because that is where we have two pointers to the same number
        // ... the two pointers represent the duplicate number we are looking for
        slow = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[fast];
            if (slow == fast) break;
        }
        return fast;
    }

    public static void main(String[] args) {
        int[] test = {1,3,4,2,2};
        System.out.println(findDuplicate2(test));
    }
}
