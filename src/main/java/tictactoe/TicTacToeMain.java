package tictactoe;

import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the initial state and create the board.
        char[][] board = readInitialState(scanner);
        // Display the board before the move.
        displayBoard(board);
        // Get the user's move.
        getUserMove(scanner, board);
        // Display the board after the move.
        displayBoard(board);
        // Output the state of the game (placeholder).
        String result = evaluateGameState(board);
        System.out.println(result);
    }

    // Reads the initial game state from the user.
    private static char[][] readInitialState(Scanner scanner) {
        char[][] board = new char[3][3];
        while (true) {
            System.out.println("Enter the cells:");
            String input = scanner.nextLine();
            // Validate input length.
            if (input.length() != 9) {
                System.out.println("Invalid input. Please enter exactly 9 characters.");
                continue;
            }
            // Validate characters.
            if (!input.matches("[XO_]+")) {
                System.out.println("Invalid input. Only X, O, or _ are allowed.");
                continue;
            }
            // Fill the board array.
            int index = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char c = input.charAt(index++);
                    if (c == '_') c = ' ';
                    board[i][j] = c;
                }
            }
            break;
        }
        return board;
    }
    // Displays the current game board.
    private static void displayBoard(char[][] board) {
        System.out.println("_________");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    // Handles the user's move.
    private static void getUserMove(Scanner scanner, char[][] board) {
        while (true) {
            System.out.println("Enter the coordinates:");
            String input = scanner.nextLine();
            String[] tokens = input.trim().split("\\s+");
            if (tokens.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int x, y;
            try {
                x = Integer.parseInt(tokens[0]);
                y = Integer.parseInt(tokens[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            int i = y - 1; // Map y to row index.
            int j = x - 1; // Map x to column index.
            if (board[i][j] == 'X' || board[i][j] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            } else {
                // Determine whose turn it is.
                int xCount = 0;
                int oCount = 0;
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        if (board[row][col] == 'X') xCount++;
                        else if (board[row][col] == 'O') oCount++;
                    }
                }
                char symbolToPlace = (xCount <= oCount ? 'X' : 'O');
                board[i][j] = symbolToPlace;
                break; // Move made; exit loop.
            }
        }
    }
    // Evaluates the game state (placeholder)
    private static String evaluateGameState(char[][] board) {
        // This method should check for wins or a draw
        // For now, we'll return "Game not finished"
        return "Game not finished";
    }
}
