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
            // Check if all characters are 'X', 'O', or '_'.
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
}
