// 325394997 Ben Tau (not in the Javadoc)
/**
 * The Ass3Game class serves as the entry point for the game application.
 * It initializes and runs the game.
 */
public class Ass3Game {
    /**
     * The main method is the entry point of the application.
     * It creates a Game object, initializes it, and then runs the game.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}