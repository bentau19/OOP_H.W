// 325394997 Ben Tau (not in the Javadoc)

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * The {@code MultipleFramesBouncingBallsAnimation} class provides an animation of multiple balls bouncing within
 * multiple rectangular frames on the screen.
 */
public class MultipleFramesBouncingBallsAnimation {
    public static final int HEIGHT = 750;
    public static final int WIDTH = 1200;

    /**
     * Draws two rectangular frames on the draw surface.
     *
     * @param d the draw surface to draw the rectangles on
     */
    public static void drawRact(DrawSurface d) {
        d.setColor(Color.gray);
        d.drawRectangle(50, 50, 450, 450);
        d.fillRectangle(50, 50, 450, 450);
        d.setColor(Color.yellow);
        d.drawRectangle(450, 450, 150, 150);
        d.fillRectangle(450, 450, 150, 150);
    }

    /**
     * Adds an array of balls with random positions within specified frames and sets their speeds.
     *
     * @param len  the number of balls to create
     * @param d    the draw surface to draw the balls on
     * @param rads an array of ball radii
     * @return an array of balls with random positions and specified sizes
     */
    public static Ball[] addRandomBalls(int len, DrawSurface d, int[] rads) {
        Border fullScreen = new Border(0, 0, WIDTH, HEIGHT);
        Border greyScreen = new Border(50, 50, 500, 500);
        ArrayList<Border> badAreas = new ArrayList<>();
        badAreas.add(new Border(450, 450, 600, 600));
        Ball[] balls = new Ball[len];
        Random rand = new Random();
        for (int i = 0; i < len / 2; i++) {
            int rad = Math.min(Math.abs(rads[i]), 180);
            balls[i] = Ball.addRandBall(greyScreen, rad, rand, badAreas);
            MultipleBouncingBallsAnimation.setBallSpeed(balls[i]);
            balls[i].setColor(Color.pink);
            balls[i].drawOn(d);
        }
        badAreas.add(greyScreen);
        for (int i = len / 2; i < len; i++) {
            int rad = Math.min(Math.abs(rads[i]), 180);
            balls[i] = Ball.addRandBall(fullScreen, rad, rand, badAreas);
            MultipleBouncingBallsAnimation.setBallSpeed(balls[i]);
            balls[i].drawOn(d);
        }
        return balls;
    }

    /**
     * The main method to start the animation. Takes command-line arguments for the sizes of the balls.
     *
     * @param args command-line arguments specifying the sizes of the balls
     */
    public static void main(String[] args) {
        int[] rads = MultipleBouncingBallsAnimation.intConvert(args);
        final int len = args.length;
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        DrawSurface d = gui.getDrawSurface();
        Ball[] balls = addRandomBalls(len, d, rads);
        while (true) {
            d = gui.getDrawSurface();
            drawRact(d);
            for (int i = 0; i < len; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(10); // wait for 10 milliseconds.
        }
    }
}
