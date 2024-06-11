// 325394997 Ben Tau (not in the Javadoc)
/**
 * This class finds words containing a specified character.
 */
public class FindWordsContaining {

    /**
     * The main method takes an array of words and a character from the command line.
     * It then prints words from the array that contain the specified character.
     *
     * @param args Command-line arguments, expecting multiple words followed by a single character.
     */
    public static void main(String[] args) {
        if (args.length < 2 || args[args.length - 1].length() != 1) {
            System.out.println("Invalid input");
        } else {
            for (int i = 0; i < args.length - 1; i++) {
                if (args[i].contains(args[args.length - 1])) {
                    System.out.println(args[i]);
                }
            }
        }
    }
}