package lab7_rehina_yakubiv;

import java.util.Stack;
import java.util.Scanner;


// Rehina Yakubiv (N01649674)

public class Lab7_Rehina_Yakubiv {

    public static int evaluatePostfix(String expression) {
        Stack<Integer> stack = new Stack<>(); // Stack to store numbers
        String[] tokens = expression.split(" "); // Split the expression into individual tokens

        for (String token : tokens) {
            if (isNumber(token)) {
                // If the token is a number, push it onto the stack
                stack.push(Integer.parseInt(token));
            } else {
                // Ensure that there are at least two numbers in the stack before applying an operation
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Error: Not enough operands for operator " + token);
                }
                int num2 = stack.pop(); // Pop the second operand
                int num1 = stack.pop(); // Pop the first operand
                int result = applyOperation(num1, num2, token); // Perform the operation
                stack.push(result); // Push the result back onto the stack
            }
        }

        // After processing all tokens, there should be exactly one result left in the stack
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Error: Too many operands in expression.");
        }

        return stack.pop(); // Return the final result
    }


    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str); // Try converting the string to an integer
            return true;
        } catch (NumberFormatException e) {
            return false; // If parsing fails, it is not a number
        }
    }

    private static int applyOperation(int num1, int num2, String operator) {
        switch (operator) {
            case "+": return num1 + num2; // Addition
            case "-": return num1 - num2; // Subtraction
            case "*": return num1 * num2; // Multiplication
            case "/":
                if (num2 == 0) { // Prevent division by zero
                    System.out.println("Error: Division by zero is not allowed.");
                    return 0;
                }
                return num1 / num2; // Division
            default:
                throw new IllegalArgumentException("Error: Invalid operator: " + operator);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a postfix expression (e.g., '1 2 + 3 *'): ");
        String expression = scanner.nextLine(); // Read user input

        int result = evaluatePostfix(expression); // Evaluate the expression
        System.out.println("Result: " + result); // Print the final result
    }
}
