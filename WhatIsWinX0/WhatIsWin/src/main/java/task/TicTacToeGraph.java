package task;

public class TicTacToeGraph {

    public static void main(String[] args) {
        Node root = generateGameTree();
        System.out.println("Game tree generated.");
    }

    public static Node generateGameTree() {
        char[][] emptyBoard = new char[3][3]; // Пустая доска
        return generateTree(new Node(emptyBoard, 'X'));
    }

    private static Node generateTree(Node node) {
        if (isGameOver(node)) {
            return node;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (node.board[i][j] == '\0') { // Если ячейка пуста
                    char[][] newBoard = makeMove(node.board, i, j, node.currentPlayer);
                    Node child = new Node(newBoard, node.currentPlayer == 'X' ? 'O' : 'X');
                    node.children.add(child);
                    generateTree(child);
                }
            }
        }
        return node;
    }

    private static char[][] makeMove(char[][] board, int row, int col, char player) {
        char[][] newBoard = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, 3);
        }
        newBoard[row][col] = player;
        return newBoard;
    }

    private static boolean isGameOver(Node node) {
        if (checkWin(node.board, 'X')) {
            node.result = "X wins";
            return true;
        }
        if (checkWin(node.board, 'O')) {
            node.result = "O wins";
            return true;
        }
        if (isDraw(node.board)) {
            node.result = "Draw";
            return true;
        }
        return false;
    }

    private static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    private static boolean isDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') return false; // Если есть пустая клетка
            }
        }
        return true; // Все клетки заполнены, ничья
    }
}
