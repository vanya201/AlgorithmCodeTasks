package task;

import java.util.ArrayList;
import java.util.List;

public class Node {
    char[][] board; // Текущая доска 3x3
    char currentPlayer; // 'X' или 'O'
    List<Node> children; // Дочерние узлы
    String result; // "X wins", "O wins", "Draw", null

    public Node(char[][] board, char currentPlayer) {
        this.board = copyBoard(board);
        this.currentPlayer = currentPlayer;
        this.children = new ArrayList<>();
        this.result = null;
    }

    private char[][] copyBoard(char[][] board) {
        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 3);
        }
        return copy;
    }
}

