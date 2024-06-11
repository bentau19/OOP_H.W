// 325394997 Ben Tau (not in the Javadoc)
/**
 * This class calculates the sum of digits of an integer.
 */
public class SumOfInteger {

    /**
     * The main method takes a command-line argument and prints the sum of its digits.
     *
     * @param args Command-line arguments, expecting a single integer input.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Invalid input");
        } else {
            int val = Integer.parseInt(args[0]);
            if (val < 0) {
                val = val * -1;
            }
            System.out.println(sumOfInteger(val));
        }
    }

    /**
     * Recursively calculates the sum of digits of the given integer.
     *
     * @param val The integer whose sum of digits needs to be calculated.
     * @return The sum of digits of the input integer.
     */
    private static int sumOfInteger(int val) {
        if (val == 0) {
            return 0;
        }
        return val % 10 + sumOfInteger(val / 10);
    }
}
