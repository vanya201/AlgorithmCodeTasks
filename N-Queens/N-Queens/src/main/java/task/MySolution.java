package task;

import java.util.*;

public class MySolution implements Solution {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(board[i], '.');

        Set<Integer> cols = new HashSet<>();
        Set<Integer> diag1 = new HashSet<>();
        Set<Integer> diag2 = new HashSet<>();

        backtrack(0, n, board, cols, diag1, diag2, results);
        return results;
    }

    //O(n!)
    private void backtrack(int row, int n, char[][] board, Set<Integer> cols,
                           Set<Integer> diag1, Set<Integer> diag2, List<List<String>> results) {
        if (row == n) {
            results.add(constructBoard(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols.contains(col) || diag1.contains(row - col) || diag2.contains(row + col))
                continue;

            board[row][col] = 'Q';
            cols.add(col);
            diag1.add(row - col);
            diag2.add(row + col);

            backtrack(row + 1, n, board, cols, diag1, diag2, results);

            board[row][col] = '.';
            cols.remove(col);
            diag1.remove(row - col);
            diag2.remove(row + col);
        }
    }

    //O(n^2)
    private List<String> constructBoard(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board)
            result.add(new String(row));
        return result;
    }
}
