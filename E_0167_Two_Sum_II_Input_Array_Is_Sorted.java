import java.util.Arrays;
import java.util.List;

public class E_0167_Two_Sum_II_Input_Array_Is_Sorted {
    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            result[0] = i + 1;
            int secondTarget = target - numbers[i];

            int start = i + 1;
            int end = numbers.length - 1;
            while (start < end) {
                int mid = (start + end) / 2;
                if (numbers[mid] == secondTarget) {
                    result[0] = i + 1;
                    result[1] = mid + 1;
                    return result;
                } else if (numbers[mid] > secondTarget) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            if (end < numbers.length && numbers[end] == secondTarget) {
                result[0] = i + 1;
                result[1] = end + 1;
                return result;
            }
        }
        return result;
    }

    public int[] twoSumTwoPointer(int[] num, int target) {
        int[] indice = new int[2];
        int left = 0, right = num.length - 1;
        while (left < right) {
            int v = num[left] + num[right];
            if (v == target) {
                indice[0] = left + 1;
                indice[1] = right + 1;
                break;
            } else if (v > target) {
                right --;
            } else {
                left ++;
            }
        }
        return indice;
    }

    public static void main(String[] args) {
        int[] test = {1,2,3,4,4,9,56,90};
        int target = 8;
        int[] result = twoSum(test, target);
        System.out.println(result[0] + " " + result[1] );
    }
}
