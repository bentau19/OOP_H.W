// 325394997 Ben Tau (not in the Javadoc)
/**
*This class is full of Helping Functions (function that used in many classes).
 */
public class HF {
    private static final double THRESHOLD = 1e-10;
    /**
     * Compares two double values with a threshold to account for floating-point imprecision.
     *
     * @param a the first value to compare
     * @param b the second value to compare
     * @return true if the values are equal within the threshold, false otherwise
     */
    static boolean areEqual(double a, double b) {
        return Math.abs(a - b) < THRESHOLD;
    }


}
