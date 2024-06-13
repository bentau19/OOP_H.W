// 325394997 Ben Tau (not in the Javadoc)
/**
 * The {@code Velocity} class represents a velocity with changes in position along the `x` and `y` axes.
 * It provides methods to calculate velocity from an angle and speed, as well as methods to retrieve
 * the components of the velocity and apply the velocity to a point.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Constructs a {@code Velocity} with the specified changes in position along the `x` and `y` axes.
     *
     * @param dx the change in position along the `x` axis
     * @param dy the change in position along the `y` axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a {@code Velocity} instance from an angle and speed.
     *
     * @param angle the angle of the velocity in degrees
     * @param speed the speed of the velocity
     * @return a new {@code Velocity} object with the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        speed = Math.abs(speed);
        angle = angle + 90;
        angle = Math.toRadians(angle);
        double dx = Math.cos(angle) * speed;
        double dy = -Math.sin(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Returns the speed of the velocity.
     *
     * @return the speed of the velocity
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns the angle of the velocity in radians.
     *
     * @return the angle of the velocity in radians
     */
    public double getAngle() {
        return Math.atan2(dy, dx) - 90;
    }

    /**
     * Returns the change in position along the `x` axis.
     *
     * @return the change in position along the `x` axis
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the change in position along the `y` axis.
     *
     * @return the change in position along the `y` axis
     */
    public double getDy() {
        return dy;
    }

    /**
     * Applies the velocity to a given point and returns a new point.
     *
     * @param p the point to which the velocity is applied
     * @return a new {@code Point} object with the updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(dx + p.getX(), dy + p.getY());
    }
}
