// 325394997 Ben Tau (not in the Javadoc)

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * The Ball class represents a ball with a position, radius, color, and velocity.
 * The ball can move within a specified border and can avoid certain bad areas.
 */
public class Ball implements Sprite{
    private Point point;
    private final int radius;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment environment;
    /**
     * Constructs a Ball object with a specified center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     * @param environment the environment of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.point = center;
        radius = r;
        this.color = color;
        this.environment = environment;
    }
    /**
     * Constructs a Ball object with specified position, radius, and color.
     *
     * @param x     the x-coordinate of the ball's center
     * @param y     the y-coordinate of the ball's center
     * @param r     the radius of the ball
     * @param color the color of the ball
     * @param environment the environment of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment environment) {
        this(new Point(x, y), r, color, environment);
    }

    /**
     * Adds a random Ball object within the specified grey screen border, avoiding bad areas.
     *
     * @param greyScreen  the border within which the ball can move
     * @param rad         the radius of the ball
     * @param rand        the Random object used to generate random positions
     * @param environment the environment of the ball
     * @return a new Ball object
     */
    public static Ball addRandBall(Border greyScreen, int rad, Random rand,GameEnvironment environment) {
        int x1 = rand.nextInt(greyScreen.getEndX() - rad
                - (greyScreen.getStartY() + rad)) + 1 + greyScreen.getStartX() + rad;
        int y1 = rand.nextInt(greyScreen.getEndY() - rad
                - (greyScreen.getStartY() + rad)) + 1 + greyScreen.getStartY() + rad;
        Ball ball = new Ball(x1, y1, rad, Color.GREEN, environment);
        return ball;
    }


    /**
     * Gets the x-coordinate of the ball's center.
     *
     * @return the x-coordinate of the ball's center
     */
    public int getX() {
        return (int) point.getX();
    }

    /**
     * Change the color.
     *
     * @param color the surface to draw the ball on
     */
    public void setColor(Color color) {
        this.color = color;
    }
    public Point getCenter() {
        return this.point;
    }
    /**
     * Gets the y-coordinate of the ball's center.
     *
     * @return the y-coordinate of the ball's center
     */
    public int getY() {
        return (int) point.getY();
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return radius;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the surface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.drawCircle(getX(), getY(), radius);
        surface.fillCircle(getX(), getY(), radius);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the change in x-direction
     * @param dy the change in y-direction
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the current velocity of the ball
     */
    public Velocity getVelocity() {
        return velocity;
    }
    /**
     * Moves the ball one step, checking for collisions with borders and bad areas.
     */
    public void moveOneStep() {
        double dx = velocity.getDx();
        double dy = velocity.getDy();
        Line trajectory = new Line(this.point, new Point(this.point.getX() + dx, this.point.getY() + dy));
        CollisionInfo collisionInfo = environment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.point = velocity.applyToPoint(point);
        } else {
            double almostX = dx / Math.abs(dx);
            double almostY = dy / Math.abs(dy);
            this.point = new Point(collisionInfo.collisionPoint().getX() - almostX,
                    collisionInfo.collisionPoint().getY() - almostY);
            this.velocity = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), velocity);
        }
    }

    public void timePassed() {
        moveOneStep();
    }

    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
