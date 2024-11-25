import java.time.Duration;
import java.time.Instant;

public class FibonacciCalculatorService {
    public long fibonacci(int num) {
        if (num <= 1) {
            return num;
        }
        return fibonacci(num - 1) + fibonacci(num - 2);
    }

    public long fibonacciPerformance(int num) {
        Instant start = Instant.now();
        long result = fibonacci(num);
        Instant end = Instant.now();

        Duration time = Duration.between(start, end);
        System.out.println("The calculation took " + time.toMillis() + " milliseconds.");
        return result;
    }
}
