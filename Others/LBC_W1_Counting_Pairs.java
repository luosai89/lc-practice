package Others;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LBC_W1_Counting_Pairs {

    public static int countPairs(List<Integer> numbers, int k) {
        int count = 0;
        Set<Integer> unique = new HashSet<>(numbers);
        for (Integer n : numbers) {
            if (unique.contains(n + k)) {
                count++;
            }
        }
        return count;
    }
}
