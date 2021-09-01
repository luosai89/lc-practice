package LC_Medium;

import java.util.Arrays;

/**
 * 2021: 8/31 (1R)
 * https://leetcode.com/problems/minesweeper/
 * You are given an m x n char matrix board representing the game board where ...
 * ... 'M' = unrevealed mine,           'X' = revealed mine
 * ... 'E' = unrevealed empty square,   'B' = revealed blank square with no adjacent mines
 * You are also given an integer array click where click = [clickr, clickc] represents the next click position among all
 * the unrevealed squares ('M' or 'E'). Return the board after revealing this position according to the following rules:
 * ... 'M' = change to 'X', game over
 * ... 'E' = change to 'B' , reveal adjacent unrevealed squares
 * ... 'E' = change to '1' to '8' (number of adjacent mines)
 * ... no more squares = return the board
 */
public class M_0529_Minesweeper {

    // TODO METHOD 1 - DFS

    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0];
        int col = click[1];

        // if directly clicked on unrevealed mine, mark it as 'X' and game is over
        if(board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }

        int[][] directions = new int[][]{
            new int[]{0,1}, new int[]{1,0}, new int[]{0,-1}, new int[]{-1,0},
            new int[]{1,1}, new int[]{1,-1}, new int[]{-1,-1}, new int[]{-1,1}};

        dfs(board, click[0], click[1], directions);
        return board;
    }

    public void dfs(char[][] board, int row, int col, int[][] directions) {
        // row, col will either be M or E
        if(!isValid(board, row, col)) return; // if invalid return
        if(board[row][col] != 'E') return; // if it's not 'E', it has been revealed, or its an unrevealed mine

        int mines = sweepMines(board, row, col, directions);
        if(mines != 0) {
            board[row][col] = (char) (mines + '0');
            return;
        }

        board[row][col] = 'B';
        for(int[] direction : directions) {
            dfs(board, row + direction[0], col + direction[1], directions);
        }
    }

    public int sweepMines(char[][] board, int x, int y, int[][] directions) {
        int count = 0;
        for(int[] direction : directions) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            if(isValid(board, nx, ny)) {
                count += board[nx][ny] == 'M' ? 1 : 0;
            }
        }
        return count;
    }

    public boolean isValid(char[][] board, int row, int col) {
        return !(row < 0 || row >= board.length || col < 0 || col >= board[0].length);
    }

    public void test(char[][] board, int[] click, char[][] expected) {
        String drawResult = drawBoard(updateBoard(board, click));
        String drawExpected = drawBoard(expected);
        System.out.printf("%s: \nGot: \n%sExpected: \n%s", drawResult.equals(drawExpected), drawResult, drawExpected);
    }



    public static void main(String[] args) {
        M_0529_Minesweeper minesweeper = new M_0529_Minesweeper();

        // lc example 1
        char[][] board = new char[][]{
            new char[]{'E','E','E','E','E'},
            new char[]{'E','E','M','E','E'},
            new char[]{'E','E','E','E','E'},
            new char[]{'E','E','E','E','E'}};
        int[] click = new int[]{3,0};
        char[][] expected = new char[][]{
            new char[]{'B','1','E','1','B'},
            new char[]{'B','1','M','1','B'},
            new char[]{'B','1','1','1','B'},
            new char[]{'B','B','B','B','B'}};
        minesweeper.test(board, click, expected);

        // lc example 2
        board = new char[][]{
            new char[]{'B','1','E','1','B'},
            new char[]{'B','1','M','1','B'},
            new char[]{'B','1','1','1','B'},
            new char[]{'B','B','B','B','B'}};
        click = new int[]{1,2};
        expected = new char[][]{
            new char[]{'B','1','E','1','B'},
            new char[]{'B','1','X','1','B'},
            new char[]{'B','1','1','1','B'},
            new char[]{'B','B','B','B','B'}};
        minesweeper.test(board, click, expected);
    }

    public String drawBoard(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : board) {
            sb.append(String.format("%s\n",Arrays.toString(row)));
        }
        return sb.toString();
    }
}


