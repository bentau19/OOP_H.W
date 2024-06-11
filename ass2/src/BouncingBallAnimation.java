// 325394997 Ben Tau (not in the Javadoc)

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The {@code BouncingBallAnimation} class provides an animation of a ball bouncing around within a specified area.
 */
public class BouncingBallAnimation {

    /**
     * Draws and animates a ball starting from a given point with specified velocity.
     *
     * @param start the starting point of the ball
     * @param dx    the change in position along the `x` axis
     * @param dy    the change in position along the `y` axis
     */
    public static void drawAnimation(Point start, double dx, double dy) {
        final int radius = 30;

        if (start.getX() < radius || start.getY() < radius
                || start.getX() > (AbstractArtDrawing.WIDTH - radius)
                || start.getY() > (AbstractArtDrawing.HEIGHT - radius)) {
            System.out.println("ball not in the border: please put dx, dy at least " + radius + " or max "
                    + (AbstractArtDrawing.WIDTH - radius) + "X" + (AbstractArtDrawing.HEIGHT - radius) + "!");
            return;
        }

        if (Math.max(dx, -dx) > AbstractArtDrawing.WIDTH - 2 * radius
                || Math.max(dy, -dy) > AbstractArtDrawing.HEIGHT - 2 * radius) {
            System.out.println("you are over speeding dude!!");
            return;
        }

        GUI gui = new GUI("title", AbstractArtDrawing.WIDTH, AbstractArtDrawing.HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);

        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }

    /**
     * The main method to start the animation. Takes command-line
     * arguments for the starting position and velocity of the ball.
     *
     * @param args command-line arguments: `x` and `y` coordinates of the starting point, `dx` and `dy` for the velocity
     */
    public static void main(String[] args) {
        drawAnimation(new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1])),
                Integer.parseInt(args[2]), Integer.parseInt(args[3]));
    }
}
