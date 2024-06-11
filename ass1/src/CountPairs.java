// 325394997 Ben Tau (not in the Javadoc)
/**
 * This class counts pairs of integers in an array whose sum is less than a given target value.
 */
public class CountPairs {

    /**
     * The main method takes an array of integers and a target integer from the command line and counts pairs
     * of integers in the array whose sum is less than the target.
     *
     * @param args Command-line arguments, expecting multiple integers followed by a single target integer.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Invalid input");
        } else {
            int[] vals = new int[args.length - 1];
            for (int i = 0; i < args.length - 1; i++) {
                vals[i] = Integer.parseInt(args[i]);
            }
            int tar = Integer.parseInt(args[args.length - 1]);

            System.out.println(countPairs(vals, tar));
        }
    }

    /**
     * Counts pairs of integers in the given array whose sum is less than the target value.
     *
     * @param vals The array of integers in which pairs are to be counted.
     * @param tar  The target integer.
     * @return The count of pairs whose sum is less than the target.
     */
    private static int countPairs(int[] vals, int tar) {
        int sum = 0;
        for (int i = 0; i < vals.length; i++) {
            for (int j = i + 1; j < vals.length; j++) {
                if (vals[i] + vals[j] < tar) {
                    sum++;
                }
            }
        }
        return sum;
    }
}