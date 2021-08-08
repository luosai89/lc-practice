package Others;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class T_0804_02_Island_Counter {
    private int width;
    private int height;
    private int[][]map;

    class Coordinate {
        private final int row;
        private final int column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Coordinate that = (Coordinate) o;
            return row == that.row &&
                column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }

        @Override
        public String toString() {
            return String.format("Coordinate{row: %s, column: %s}",
                row,
                column);
        }
    }

    public T_0804_02_Island_Counter(int width, int height, int[][] map) {
        this.width = width;
        this.height = height;
        this.map = map;
    }

    /**
     * Counts the number of islands for the map.
     * @return the number of islands for the map.
     */
    public int countIslands() {
        Set<Coordinate> visited = new HashSet<>();
        int islandCount = 0;
        // visit each coordinate
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                Coordinate currentCoordinate = new Coordinate(h, w); // row (height) and column (width)
                if (visited.contains(currentCoordinate) || !isIsland(currentCoordinate)) {
                    continue;
                }
                // if we are on an island coordinate yet we have not visited it, we discovered a new island
                // ... find all neighboring island coordinates, and mark them as visited (prevent double counting)
                sweepIsland(currentCoordinate, visited);
                islandCount++;
            }
        }
        return islandCount;
    }

    private boolean isIsland(Coordinate coordinate) {
        return map[coordinate.getRow()][coordinate.getColumn()] == 1;
    }

    private void sweepIsland(Coordinate origin, Set<Coordinate> visited) {
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<Coordinate> unvisited = new LinkedList<>();
        unvisited.offer(origin);
        while (!unvisited.isEmpty()) {
            Coordinate currentCoordinate = unvisited.poll();
            if(isIsland(currentCoordinate)) {
                visited.add(currentCoordinate);
            }
            for (int i = 0; i < directions.length; i++) {
                int newX = currentCoordinate.getColumn() + directions[i][0];
                int newY = currentCoordinate.getRow() + directions[i][1];
                Coordinate nextCoordinate = new Coordinate(newY, newX);
                if (newX >= width || newX < 0 || newY >= height || newY < 0
                    || !isIsland(nextCoordinate) || visited.contains(nextCoordinate)) {
                    continue;
                }
                // It is a new island coordinate if it has value 1, still on the board, and not visited
                // ... in this case, we add it to the queue to visit (BFS)
                unvisited.add(nextCoordinate);
            }
        }
    }
}
