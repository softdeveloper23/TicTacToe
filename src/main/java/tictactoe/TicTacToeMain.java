package tictactoe;

import java.util.Scanner;

/**
 * A simple Tic-Tac-Toe game where a user can play against another user.
 * The game accepts initial board input and allows users to make moves until
 * there's a winner or a draw. This version includes detailed comments and documentation
 * for future reference and easy understanding.
 */
public class TicTacToeMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the initial state and create the board
        char[][] board = readInitialState(scanner);

        // Display the initial board
        displayBoard(board);

        // Main game loop
        while (true) {
            // Get the user's move
            getUserMove(scanner, board);

            // Display the board after the move
            displayBoard(board);

            // Evaluate the game state
            String result = evaluateGameState(board);

            // Check if the game has ended
            if (!result.equals("Game not finished")) {
                // Output the result (e.g., "X wins", "O wins", or "Draw")
                System.out.println(result);
                break; // Exit the game loop
            }
            // If the game is not finished, continue to the next move
        }
    }

    /**
     * Reads the initial game state from the user.
     * Prompts the user to enter a string of exactly 9 characters consisting of 'X', 'O', or '_'.
     * The underscores represent empty cells.
     *
     * @param scanner Scanner object for reading user input.
     * @return A 2D char array representing the initial game board.
     */
    private static char[][] readInitialState(Scanner scanner) {
        char[][] board = new char[3][3]; // 3x3 game board
        while (true) {
            System.out.println("Enter the cells:");
            String input = scanner.nextLine();

            // Validate input length
            if (input.length() != 9) {
                System.out.println("Invalid input. Please enter exactly 9 characters.");
                continue; // Prompt the user again
            }

            // Validate characters using regex (only 'X', 'O', or '_')
            if (!input.matches("[XO_]+")) {
                System.out.println("Invalid input. Only X, O, or _ are allowed.");
                continue; // Prompt the user again
            }

            // Fill the board array with the input characters
            int index = 0; // Index for the input string
            for (int i = 0; i < 3; i++) { // Rows
                for (int j = 0; j < 3; j++) { // Columns
                    char c = input.charAt(index++);
                    if (c == '_') c = ' '; // Replace '_' with ' ' for display purposes
                    board[i][j] = c;
                }
            }
            break; // Valid input received, exit the loop
        }
        return board; // Return the initialized board
    }

    /**
     * Displays the current game board to the console.
     * Formats the board with borders for a better visual representation.
     *
     * @param board A 2D char array representing the game board.
     */
    private static void displayBoard(char[][] board) {
        System.out.println("---------"); // Top border
        for (int i = 0; i < 3; i++) { // Rows
            System.out.print("| "); // Left border
            for (int j = 0; j < 3; j++) { // Columns
                System.out.print(board[i][j] + " "); // Cell content
            }
            System.out.println("|"); // Right border
        }
        System.out.println("---------"); // Bottom border
    }

    /**
     * Handles the user's move by prompting for coordinates, validating them,
     * and updating the game board accordingly.
     *
     * @param scanner Scanner object for reading user input.
     * @param board   The current game board.
     */
    private static void getUserMove(Scanner scanner, char[][] board) {
        while (true) {
            System.out.println("Enter the coordinates:");
            String input = scanner.nextLine();

            // Split the input into tokens (expected to be two numbers)
            String[] tokens = input.trim().split("\\s+");

            // Validate that exactly two coordinates are provided
            if (tokens.length != 2) {
                System.out.println("You should enter numbers!");
                continue; // Prompt the user again
            }

            int x, y; // Coordinates provided by the user
            try {
                x = Integer.parseInt(tokens[0]); // Row number
                y = Integer.parseInt(tokens[1]); // Column number
            } catch (NumberFormatException e) {
                // Handle non-numeric input
                System.out.println("You should enter numbers!");
                continue; // Prompt the user again
            }

            // Validate that the coordinates are within the acceptable range (1 to 3)
            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue; // Prompt the user again
            }

            // Map user coordinates to array indices (0-based indexing)
            int i = x - 1; // Row index
            int j = y - 1; // Column index

            // Check if the chosen cell is already occupied
            if (board[i][j] == 'X' || board[i][j] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                continue; // Prompt the user again
            } else {
                // Determine whose turn it is and place the symbol
                char symbolToPlace = getNextSymbol(board);
                board[i][j] = symbolToPlace; // Update the board
                break; // Move made; exit the loop
            }
        }
    }

    /**
     * Determines whose turn it is based on the current state of the board.
     * The game starts with 'X', and players alternate turns.
     *
     * @param board The current game board.
     * @return 'X' if it's X's turn, 'O' if it's O's turn.
     */
    private static char getNextSymbol(char[][] board) {
        int xCount = 0; // Number of 'X's on the board
        int oCount = 0; // Number of 'O's on the board

        // Count the number of 'X's and 'O's on the board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 'X') xCount++;
                else if (board[row][col] == 'O') oCount++;
            }
        }

        // Determine the next symbol to place
        return (xCount <= oCount) ? 'X' : 'O';
    }

    /**
     * Evaluates the current game state to determine if there's a winner,
     * a draw, or if the game should continue.
     *
     * @param board The current game board.
     * @return A string representing the game state ("X wins", "O wins", "Draw", or "Game not finished").
     */
    private static String evaluateGameState(char[][] board) {
        // Check for a winner
        String winner = checkWinner(board);
        if (winner != null) {
            return winner + " wins"; // Return the winner
        }

        // Check for a draw or if the game is not finished
        boolean hasEmptyCells = false; // Flag to check for empty cells
        for (int i = 0; i < 3; i++) { // Rows
            for (int j = 0; j < 3; j++) { // Columns
                if (board[i][j] == ' ') {
                    hasEmptyCells = true;
                    break; // No need to check further
                }
            }
            if (hasEmptyCells) break; // Exit the outer loop as well
        }

        // Return the game state based on the presence of empty cells
        if (hasEmptyCells) {
            return "Game not finished";
        } else {
            return "Draw";
        }
    }

    /**
     * Checks the board for a winner by evaluating all possible winning combinations:
     * rows, columns, and diagonals.
     *
     * @param board The current game board.
     * @return "X" if X wins, "O" if O wins, or null if there's no winner yet.
     */
    private static String checkWinner(char[][] board) {
        // Check all rows for a winner
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && // Ensure the cell is not empty
                    board[i][0] == board[i][1] && // Check first and second cell
                    board[i][1] == board[i][2]) { // Check second and third cell
                return String.valueOf(board[i][0]); // Return the winner ('X' or 'O')
            }
        }

        // Check all columns for a winner
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' && // Ensure the cell is not empty
                    board[0][j] == board[1][j] && // Check first and second cell
                    board[1][j] == board[2][j]) { // Check second and third cell
                return String.valueOf(board[0][j]); // Return the winner ('X' or 'O')
            }
        }

        // Check the main diagonal for a winner
        if (board[0][0] != ' ' && // Ensure the cell is not empty
                board[0][0] == board[1][1] && // Check first and second cell
                board[1][1] == board[2][2]) { // Check second and third cell
            return String.valueOf(board[0][0]); // Return the winner ('X' or 'O')
        }

        // Check the anti-diagonal for a winner
        if (board[0][2] != ' ' && // Ensure the cell is not empty
                board[0][2] == board[1][1] && // Check first and second cell
                board[1][1] == board[2][0]) { // Check second and third cell
            return String.valueOf(board[0][2]); // Return the winner ('X' or 'O')
        }

        // No winner found
        return null;
    }
}
