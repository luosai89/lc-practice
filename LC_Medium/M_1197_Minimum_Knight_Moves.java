package LC_Medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 2021: 9/13 (1G)
 * Premium: https://leetcode.com/problems/minimum-knight-moves/ -> public int minKnightMoves(int x, int y)
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0]. A knight
 * has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one
 * square in an orthogonal direction. Return the minimum number of steps needed to move the knight to the square [x, y].
 * It is guaranteed the answer exists.
 * Example 1: x = 2, y = 1 -> output = 1 ([0,0] -> [2,1])
 * Example 2: x = 5, y = 5 -> output = 4 ([0,0] -> [2,1] -> [4,2] -> [3,4] -> [5,5])
 */
public class M_1197_Minimum_Knight_Moves {
    static final class Point {
        final int x;
        final int y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        Point[] getMoves(int[] x_moves, int[] y_moves) {
            if(x_moves.length != y_moves.length) return new Point[]{};
            Point[] moves = new Point[x_moves.length];
            for(int i = 0; i < x_moves.length; i++) {
                moves[i] = new Point(x + x_moves[i], y + y_moves[i]);
            }
            return moves;
        }
    }

    public int minKnightMoves(int x, int y) {
        final int[] x_dir = new int[]{-1, 1, 2, 2, 1,-1,-2,-2};
        final int[] y_dir = new int[]{ 2, 2, 1,-1,-2,-2,-1, 1};
        Queue<Point> unvisited = new LinkedList<>();
        Set<Point> offered = new HashSet<>();
        unvisited.offer(new Point(0,0));
        int steps = 0;
        while(!unvisited.isEmpty()) {
            int size = unvisited.size();
            for(int i = 0; i < size; i++) {
                Point current = unvisited.poll();
                if(current.x == x && current.y == y) {
                    return steps;
                }
                for(Point move : current.getMoves(x_dir, y_dir)) {
                    if(!offered.contains(move)) {
                        unvisited.offer(move);
                        offered.add(move);
                    }
                }
            }
            steps++;
        }
        return steps;
    }

    private static void test(M_1197_Minimum_Knight_Moves problem, int x, int y, int expected) {
        int result = problem.minKnightMoves(x, y);
        System.out.printf("%s:\tGiven x\t%d and y\t%d. Got\t%d. Expected\t%d.\n", result == expected,
                          x, y, result, expected);
    }

    public static void main(String[] args) {
        M_1197_Minimum_Knight_Moves problem = new M_1197_Minimum_Knight_Moves();
        test(problem, 2, 1, 1);
        test(problem, 5, 5, 4);
    }
}
