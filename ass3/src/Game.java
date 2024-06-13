import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

public class Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private onMoveCollection moves = new onMoveCollection();
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    public void addonMovement(onMovement m) {
        this.moves.addMove(m);
    }
    public void addBorders() {
        int borderSize = 30;
        Block block = new Block(new Point(0,0),WIDTH,borderSize,Color.GRAY);
        block.addToGame(this);

        block = new Block(new Point(0,0),borderSize,HEIGHT,Color.GRAY);
        block.addToGame(this);

        block = new Block(new Point(WIDTH - borderSize, 0), borderSize, HEIGHT, Color.GRAY);
        block.addToGame(this);

        block = new Block(new Point(0,HEIGHT-borderSize),WIDTH,borderSize,Color.GRAY);
        block.addToGame(this);
    }
    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        for (int i = 0; i < 10; i++) {
            Ball ball = new Ball(new Point(400,450),10, Color.GREEN,environment);
            ball.setVelocity(i,6);
            ball.addToGame(this);
        }
        addBorders();
        for (int i=0;i<5;i++) {
            Block block = new Block(new Point(100*i,200),100,50,Color.red);
            block.addToGame(this);
        }

    }
    // Run the game -- start the animation loop.
    public void run() {
        GUI gui = new biuoop.GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();

        Paddle p = new Paddle(new Point(400,HEIGHT-100),100,20,Color.pink,10,gui, moves);
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
