package tictactoe;

import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Ask the user to provide the initial state of the 3x3 table with the first input line.
        initialState(scanner);
    }

    private static void initialState(Scanner scanner) {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the cells:");
            String input = scanner.nextLine();
            // Must include nine symbols that can be X, O or _
            if (input.contains("X") || input.contains("O") || input.contains("_") && input.length() == 9) {
                // Test code
                System.out.println(input);
                flag = false;
            } else {
                System.out.println("Invalid input");
                flag = true;
            }
        }
    }
}
