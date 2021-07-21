package LC_Amazon;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/reorder-data-in-log-files/
 * 7/20
 * Amazon high freq
 */
public class E_0937_Reorder_Data_in_Log_Files {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a, b) -> {
            String log1 = a.substring(a.indexOf(" ") + 1);
            String log2 = b.substring(b.indexOf(" ") + 1);
            boolean isDigit1 = Character.isDigit(log1.charAt(0));
            boolean isDigit2 = Character.isDigit(log2.charAt(0));

            if (isDigit1 && isDigit2) {
                // if both are numbers, maintain current order
                return 0;
            } else if (!isDigit1 && !isDigit2) {
                // if both are letters, compare String
                int comp = log1.compareTo(log2);
                if (comp == 0) {
                    comp = a.compareTo(b);
                }
                return comp;
            } else if (!isDigit1 && isDigit2) {
                // otherwise, make sure letter is first
                return -1;
            } else {
                return 1;
            }
        });
        return logs;
    }
}
