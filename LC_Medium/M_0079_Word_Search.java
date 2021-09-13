package LC_Medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2021: 9/10
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 */
public class M_0079_Word_Search {

    // todo METHOD 1 - DFS + RECURSIVE

    public boolean exist(char[][] board, String word) {
        // look for the start
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(dfs(board, i, j, word, 0)) {
                        return true; // return the first correct answer
                    } // if not, keep looking for the next word start
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int index) {
        // exit criteria - if index is at length, it can only reach here cuz all previous chars were found and returned true
        if(index == word.length()) {
            return true;
        }
        // exit criteria - if not viable path return false
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length      // out of board
            || board[i][j] == '#'                                           // visited
            || board[i][j] != word.charAt(index)) {                         // not what we expected
            return false;
        }
        // this one is ok, but not sure if neighbors are ok
        char storeChar = board[i][j];     // store char to mark visited
        board[i][j] = '#';                // mark visited
        if(dfs(board, i + 1, j, word, index + 1) || dfs(board, i, j + 1, word, index + 1)
            || dfs(board, i - 1, j, word, index + 1) || dfs(board, i, j - 1, word, index + 1)) {
            return true;
        }
        // neighbors are all bad, restore the value
        board[i][j] = storeChar;
        return false;
    }

}

/**
 * Reflections
 * 2021
 * 9/10 (1R)
 * My original idea involves using a stack initialized with (0,0). When to use stack vs directly look for the first
 * char? In other words, why can't we use dfs from (0,0)? If we dfs from (0,0). If it's not expected char, we cannot
 * mark it as visited. Say in "SEE", we found a correct E, but we are looking for S. If we mark visited, then it's
 * invisible to us. If we do not mark visited, then we might keep visiting it. We could use "offered" to check if its
 * already on our plan to visit. However it will take forever to find the start, it's better to just iterate. However, I
 * am still not clear how to think of this from the beginning.
 *
 */

/**
 * Time complexity
 * The complexity will be 𝑂(r * c * 4^s). r = rows, c = columns, s = string length
 * When we start searching from a character we have 4 choices of neighbors for the first character and subsequent
 * characters have only 3 or less than 3 choices but we can take it as 4 (permissible slopiness in upper bound).
 * This slopiness would be fine in large matrices. So for each character we have 4 choices. Total no. of characters are
 * 𝑠 where s is the length of the input string. So one invocation of search function of your implementation would take
 * 𝑂(4𝑠) time. Also in worst case the search is invoked for r * c times. So an upper bound would be 𝑂(r * c * 4^s).
 */