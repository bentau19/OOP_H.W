// 325394997 Ben Tau (not in the Javadoc)
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.Random;

/**
 * The {@code MultipleBouncingBallsAnimation} class provides an animation of multiple
 * balls bouncing within a specified area.
 */
public class MultipleBouncingBallsAnimation {
    private static final int BIG_BALL_SPEED = 1;
    private static final int SMALL_BALL_SPEED = 50;

    /**
     * Sets the speed of the ball based on its size.
     *
     * @param ball the ball for which the speed is to be set
     */
    public static void setBallSpeed(Ball ball) {
        if (ball.getSize() > 50) {
            ball.setVelocity(BIG_BALL_SPEED, BIG_BALL_SPEED);
        } else {
            ball.setVelocity((double) SMALL_BALL_SPEED / ball.getSize(), (double) SMALL_BALL_SPEED / ball.getSize());
        }
    }

    /**
     * Creates an array of randomly placed balls within the specified boundaries.
     *
     * @param len the number of balls to create
     * @param args an array of ball sizes
     * @param d the draw surface to draw the balls on
     * @return an array of balls with random positions and specified sizes
     */
    public static Ball[] makeRandomBalls(int len, int[] args, DrawSurface d) {
        Border fullScreen = new Border(0, 0, AbstractArtDrawing.WIDTH, AbstractArtDrawing.HEIGHT);
        Ball[] balls = new Ball[len];
        Random rand = new Random();
        for (int i = 0; i < len; i++) {
            if (Math.abs(args[i]) > Math.min(AbstractArtDrawing.WIDTH / 3, AbstractArtDrawing.HEIGHT / 3)) {
                args[i] = Math.min(AbstractArtDrawing.WIDTH / 3, AbstractArtDrawing.HEIGHT / 3);
            }
            balls[i] = Ball.addRandBall(fullScreen, Math.abs(args[i]), rand, new ArrayList<>());
            balls[i].drawOn(d);
            setBallSpeed(balls[i]);
        }
        return balls;
    }

    /**
     * Converts an array of strings to an array of integers.
     *
     * @param args an array of strings to be converted
     * @return an array of integers converted from the input strings
     */
    public static int[] intConvert(String[] args) {
        final int len = args.length;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }
        return arr;
    }

    /**
     * The main method to start the animation. Takes command-line arguments for the sizes of the balls.
     *
     * @param args command-line arguments specifying the sizes of the balls
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", AbstractArtDrawing.WIDTH, AbstractArtDrawing.HEIGHT);
        Sleeper sleeper = new Sleeper();
        DrawSurface d = gui.getDrawSurface();
        final int len = args.length;

        Ball[] balls = makeRandomBalls(len, intConvert(args), d);
        while (true) {
            d = gui.getDrawSurface();
            for (int i = 0; i < len; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(10); // wait for 10 milliseconds.
        }
    }
}
