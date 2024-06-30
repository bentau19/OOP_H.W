// 325394997 Ben Tau (not in the Javadoc)
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * The Ball class represents a ball with a position, radius, color, velocity, and environment.
 * The ball can move within a specified border and can avoid certain bad areas.
 */
public class Ball implements Sprite {
    private Point point;
    private final int radius;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);
    private final GameEnvironment environment;
    /**
     * Constructs a Ball object with a specified center point, radius, color, and environment.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     * @param environment the environment of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.point = center;
        this.radius = r;
        this.color = color;
        this.environment = environment;
    }
    /**
     * Constructs a Ball object with specified position, radius, color, and environment.
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
     * Gets the x-coordinate of the ball's center.
     *
     * @return the x-coordinate of the ball's center
     */
    public int getX() {
        return (int) point.getX();
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
     * Changes the color of the ball.
     *
     * @param color the new color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
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
     * Moves the ball one step, taking into account possible collisions.
     */
    public void moveOneStep() {
        double dx = velocity.getDx();
        double dy = velocity.getDy();
        Line trajectory = new Line(this.point, new Point(this.point.getX() + dx, this.point.getY() + dy));
        CollisionInfo collisionInfo = environment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.point = velocity.applyToPoint(point);
        } else {
            double almostX = new Line(collisionInfo.collisionPoint(), this.point).middle().getX();
            double almostY = new Line(collisionInfo.collisionPoint(), this.point).middle().getY();
            this.velocity = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), velocity);
            if (!(Math.signum(dx) == Math.signum(this.velocity.getDx())
                    && Math.signum(dy) == Math.signum(this.velocity.getDy()))) {
                this.point = new Point(almostX, almostY);
            } else {
                this.point = velocity.applyToPoint(point);
            }
        }
    }
    /**
     * Updates the ball's state, typically called once per frame.
     */
    public void timePassed() {
        moveOneStep();
    }
    /**
     * Adds the ball to the given game.
     *
     * @param g the game to add the ball to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
