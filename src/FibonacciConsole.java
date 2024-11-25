import java.util.InputMismatchException;
import java.util.Scanner;

public class FibonacciConsole {
    public void startConsoleApp () {
        FibonacciCalculatorService fcs = new FibonacciCalculatorService();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter the number of terms in the Fibonacci series!\nEnter a number!");
                String input = scanner.nextLine();

                if (input.equals("exit")) {
                    System.out.println("exiting...");
                    break;
                }

                try {
                    int n = Integer.parseInt(input);
                    if (n < 0) {
                        System.out.println("Invalid input. Please enter a positive number.");
                        continue;
                    }
                    System.out.println("Fibonacci series: " + fcs.fibonacciPerformance(n));
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
        } catch (InputMismatchException err) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

}
