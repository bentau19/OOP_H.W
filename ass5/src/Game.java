// 325394997 Ben Tau (not in the Javadoc)
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The Game class is responsible for initializing and running the game.
 * It manages the game environment, sprites, and the game loop.
 */
public class Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private final int borderSize = 15;
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    /**
     * Removes the given collidable from the environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c){
        this.environment.removeCollidable(c);
    }
    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s){
        this.sprites.removeSprite(s);
    }
    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Adds border blocks to the game to define the game area boundaries.
     */
    public void addBorders() {
        Block block = new Block(new Point(0, 0), WIDTH, borderSize, Color.GRAY);
        block.addToGame(this);

        block = new Block(new Point(0, 0), borderSize, HEIGHT, Color.GRAY);
        block.addToGame(this);

        block = new Block(new Point(WIDTH - borderSize, 0), borderSize, HEIGHT, Color.GRAY);
        block.addToGame(this);

        block = new Block(new Point(0, HEIGHT - borderSize), WIDTH, borderSize, Color.GRAY);
        block.addToGame(this);
    }

    /**
     * Initializes a new game: creates the Blocks, Balls, and Paddle, and adds them to the game.
     */
    public void initialize() {
        for (int i = 0; i < 2; i++) {
            Ball ball = new Ball(new Point(400, 400), 6, java.awt.Color.RED, environment);
            ball.setVelocity(Velocity.fromAngleAndSpeed(-30 + i * 60, 10));
            ball.addToGame(this);
        }
        addBorders();
        int blockH = 25;
        int blockW = 45;
        int startBlockH = 150;
        Color[] colors = new Color[]{Color.GRAY, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.ORANGE, Color.YELLOW};
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 12 - j; i++) {
                Block block = new Block(new Point(blockW * i + borderSize, startBlockH + blockH * j),
                        blockW, blockH, colors[j]);
                block.addToGame(this);
            }
        }
    }

    /**
     * Runs the game -- starts the animation loop.
     */
    public void run() {
        GUI gui = new biuoop.GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Paddle p = new Paddle(new Point(400, HEIGHT - borderSize - 20.1), 100, 20, Color.PINK, 10, gui);
        p.addToGame(this);
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
