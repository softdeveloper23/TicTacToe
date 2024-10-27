package tictactoe;

import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Start the game by getting the initial state.
        initialState(scanner);
    }
    // Method to get the initial state and validate it.
    private static void initialState(Scanner scanner) {
        String input;
        while (true) {
            System.out.println("Enter the cells:");
            input = scanner.nextLine();
            // Check if the input length is exactly 9.
            if (input.length() != 9) {
                System.out.println("Invalid input. Please enter exactly 9 characters.");
                continue;
            }
            // Check if all characters are 'X', 'O', or '_' using regex.
            if (!input.matches("[XO_]+")) {
                System.out.println("Invalid input. Only X, O, or _ are allowed.");
                continue;
            }
            // Valid input; break out of the loop.
            break;
        }
        // Display the initial table.
        printTable(input);
    }
    // Method to print the 3x3 table.
    private static void printTable(String input) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                char c = input.charAt(i * 3 + j);
                // Replace '_' with ' ' for better display
                if (c == '_') c = ' ';
                System.out.print(c + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static void cellCoordinates(Scanner scanner, String input) {
        while (true) {
            System.out.println("Enter the coordinates:");
            String coordinates = scanner.nextLine();
            // Trim leading and trailing whitespace and split the input by spaces
            String[] tokens = coordinates.trim().split("\\s+");

            if (isValidCoordinate(coordinates)) {
                continue;
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }
    /**
     * Checks if the given input string represents valid coordinates within a 3x3 grid.
     * The input must contain exactly two integers between 1 and 3, separated by whitespace.
     *
     * @param coordinates The string input to validate.
     * @return true if the input is valid coordinates; false otherwise.
     */
    private static boolean isValidCoordinate(String coordinates) {
        // Trim leading and trailing whitespace and split the input by spaces
        String[] tokens = coordinates.trim().split("\\s+");
        // Check if there are exactly two tokens
        if (tokens.length != 2) {
            return false;
        }
        try {
            // Parse the tokens to integers
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);

            // Check if both integers are between 1 and 3 (inclusive)
            return x >= 1 && x <= 3 && y >= 1 && y <= 3;
        } catch (NumberFormatException e) {
            // If parsing fails, the input is invalid
            return false;
        }
    }

}
