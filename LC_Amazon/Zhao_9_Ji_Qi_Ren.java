package LC_Amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 * OA prep
 * 8/5 (makeup 7/31)
 */
public class Zhao_9_Ji_Qi_Ren {
    public static int Zhao_9_Ji_Qi_Ren(int[][] grid) {
        int[][] direction = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}}; // order here does not matter
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        visited[0][0] = true;

        int distance = 0;
        while (!queue.isEmpty()) {
            // bfs approach - for each unexplored, add all possible next path to the queue
            int unexplored = queue.size();
            for (int i = 0; i < unexplored; i++) {
                int[] currPos = queue.poll(); // take off the first path to explore
                for (int[] dir : direction) {
                    int newX = currPos[0] + dir[0];
                    int newY = currPos[1] + dir[1];
                    // Exception - if the new coordinates are off the matrix, visited, or in trench -> move on
                    if (newX < 0 || newX > row - 1 || newY < 0 || newY > col - 1
                        || grid[newX][newY] == 0 || visited[newX][newY]) {
                        continue;
                    }
                    // Return condition - if target found, it must be the shortest
                    if (grid[newX][newY] == 9) {
                        return distance + 1;
                    }
                    // Otherwise - this is a possible new path, add to unexplored in order
                    visited[newX][newY] = true; // mark visited
                    queue.offer(new int[]{newX, newY});
                }
            }
            distance++;
        }
        return -1;
    }
}
