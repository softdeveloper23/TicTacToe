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

        cellCoordinates(scanner, input);
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

            String result = isValidCoordinate(coordinates, input);

            if (result.equals("Valid")) {
                break; // Exit the loop since we have valid coordinates
            } else if (result.equals("Invalid Length")) {
                System.out.println("You should enter numbers!");
            } else if (result.equals("Invalid Coordinates")) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (result.equals("Not Numbers")) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    /**
     * Checks if the given input string represents valid coordinates within a 3x3 grid.
     * The input must contain exactly two integers between 1 and 3, separated by whitespace.
     *
     * @param coordinates The string input to validate.
     * @return Valid if the input is valid coordinates; Invalid Coordinates or Not Numbers otherwise.
     */
    private static String isValidCoordinate(String coordinates, String input) {
        // Trim leading and trailing whitespace and split the input by spaces
        String[] tokens = coordinates.trim().split("\\s+");
        // Check if there are exactly two tokens
        if (tokens.length != 2) {
            return "Invalid Length";
        }
        try {
            // Parse the tokens to integers
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);

            // Check if both integers are between 1 and 3 (inclusive)
            if (x >= 1 && x <= 3 && y >= 1 && y <= 3) {
                updateGameState(x, y, input);
                return "Valid";
            } else {
                return "Invalid Coordinates";
            }
        } catch (NumberFormatException e) {
            // If parsing fails, the input is not numbers
            return "Not Numbers";
        }
    }
    private static void updateGameState(int x, int y, String input) {
        char[][] arr = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = input.charAt(i * 3 + j);
            }
        }
        if (arr[x - 1][y - 1] == 'X' || arr[x - 1][y - 1] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
        } else {
            arr[x - 1][y - 1] = 'X';
        }
        printUpdatedTable(arr);
    }
    private static void printUpdatedTable(char[][] arr) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                char c = arr[i][j];
                // Replace '_' with ' ' for better display
                if (c == '_') c = ' ';
                System.out.print(c + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
