package LC_Medium;

/**
 * https://leetcode.com/problems/robot-bounded-in-circle/
 * 7/19
 * Amazon high freq
 */
public class M_1041_Robot_Bounded_in_Circle {
    public boolean isRobotBounded(String instructions) {
        // initialize starting position (x, y)
        // initialize starting direction facing up (0,1), i.e. index 0 of the dir array
        int x = 0, y = 0, i = 0;
        // the dir array in order of turning right
        int[][] dir = {{0,1},{1,0},{0, -1},{-1, 0}};
        // examine each instruction
        for (char c : instructions.toCharArray()) {
            if (c == 'G') {
                // if 'G', go forward with the current directions (dir[i])
                x += dir[i][0];
                y += dir[i][1];
            } else if (c == 'R') {
                // if 'R', go to the next direction - turn right once
                // use mod to stay within the array
                i = (i + 1) % 4;
            } else {
                // if 'L', go to the third direction after current direction - turn right thrice
                i = (i + 3) % 4;
            }
        }
        // it's bounded if 1) we are back to starting point, or 2) if we are in any direction but the starting direction
        return (x == 0 && y == 0) || i > 0;
    }
}
