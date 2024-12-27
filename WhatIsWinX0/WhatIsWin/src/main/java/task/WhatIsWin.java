package task;

import static task.TicTacToeGraph.generateGameTree;
import static task.TicTacToeGraph.generateGameTree;

public class WhatIsWin {

    public static void main(String[] args) {
        Node root = generateGameTree();
        String optimalResult = whatIsWin(root);
        System.out.println("Optimal result for root: " + optimalResult);
    }

    private static String whatIsWin(Node root) {
        if (root.children.isEmpty()) {
            return root.result;
        }

        String result = root.currentPlayer == 'X' ? "O wins" : "X wins";

        for (Node child : root.children) {
            String childResult = whatIsWin(child);

            if (root.currentPlayer == 'X') {
                if (childResult.equals("X wins")) {
                    result = "X wins";
                }
                if (childResult.equals("Draw")) {
                    result = "Draw";
                }
            } else {
                if (childResult.equals("O wins")) {
                    result = "O wins";
                }
                if (childResult.equals("Draw")) {
                    result = "Draw";
                }
            }
        }
        root.result = result;
        return result;
    }
}

