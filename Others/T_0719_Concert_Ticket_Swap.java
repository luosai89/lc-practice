package Others;

import java.util.Arrays;
import java.util.List;

/**
 * Problem Statement:
 * John has a ticket to a concert and is thinking about trading someone for a different seat.
 * All the seats cost different amounts of money, and he's trying to figure out how much money
 * he would need to pay or how much money someone would pay him to trade. He wants to figure
 * out which seat he would get for a certain amount of money.
 *
 * He has a map of the price differences between his ticket and the ticket for each seat. The
 * price differences are stored in a two-dimensional array. Price differences are in ascending
 * order from top to bottom and left to right, respectively, because the stage is in the top
 * left corner, and seats farther from the stage are costlier. Each price difference is how
 * much money John would get if he traded for that seat, or how much he would pay if it is a
 * negative number.
 *
 * Write an algorithm to find the pair containing the location of the seat with the target price
 * difference in <row, column> order. If not seat costs the target price difference, return <-1, -1>.
 *
 * Example:
 *
 * seatMap =
 *     [[-3, 1, 31, 40],
 *     [10, 33, 40, 66],
 *     [22, 43, 61, 70]]
 * targetValue = 22
 *
 * Explanation:
 * The target integer 22 is present in the matrix at the 2nd row and 0th column.
 * So, the output is (2, 0).
 *
 * Output:
 * [2, 0]
 *
 * Function Description
 * Complete the function findTicketLocation in the editor below.
 *
 * findTicketLocation has the following parameter(s):
 *     int[n][m] seatMap: a two-dimensional array with each cell representing the price difference
 *                          between two tickets
 *     int targetValue: an integer representing the target price difference
 *
 * Returns:
 *     int[z]: a list of two integers, in row, column order, representing the location of the
 *              targetValue. If targetValue is not contained in seatMap, [-1, -1] should be returned.
 *
 * Constraints
 * - seatMap[n][m] <= seatMap[n+1][m]
 * - seatMap[n][m] <= seatMap[n][m + 1]
 * - seatMap contains only one unique targetValue
 */

/**
 * 7/27
 */

public class T_0719_Concert_Ticket_Swap {
    public static List<Integer> findTicketLocationOptimal(List<List<Integer>> seatMap, int targetValue) {
        int row = 0;
        int col = seatMap.get(0).size();

        while(true) {
            int currVal = seatMap.get(row).get(col);
            if (currVal == targetValue) {
                return Arrays.asList(row, col);
            }
            if (currVal < targetValue) {
                if (row + 1 < seatMap.size()) {
                    row++;
                } else {
                    return Arrays.asList(-1, -1);
                }

            } else {
                if (col > 0) {
                    col--;
                } else {
                    return Arrays.asList(-1, -1);
                }
            }
        }
    }
}
