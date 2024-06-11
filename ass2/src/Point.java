// 325394997 Ben Tau (not in the Javadoc)
/**
 * This is the Point class representing a point in a 2D coordinate system.
 */
public class Point {
    private final double x, y;
    /**
     * Constructs a new Point object with the specified coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Calculates and returns the Euclidean distance between this point and another point.
     *
     * @param other The other point to calculate the distance to.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other The other point to compare with.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return HF.areEqual(this.x, other.getX()) && HF.areEqual(this.y, other.getY());
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return The x-coordinate of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return The y-coordinate of this point.
     */
    public double getY() {
        return y;
    }

    /**
     * Returns a string representation of the object.
     * The string representation consists of the x and y coordinates
     * enclosed in parentheses and separated by a comma and a space.
     *
     * @return a string representation of the object in the format "(x, y)"
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
