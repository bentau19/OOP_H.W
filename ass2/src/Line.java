// 325394997 Ben Tau (not in the Javadoc)

import java.util.ArrayList;

/**
 * This class represents a line defined by two points (start and end).
 * It includes methods to calculate the length of the line, find the middle point,
 * check for intersections with other lines, and more.
 */
public class Line {
    private final Point end, start;
    private ArrayList<Integer> connectionLines = new ArrayList<>();

    /**
     * Constructs a Line with the given start and end points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a Line with the given coordinates.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double midx = (start.getX() + end.getX()) / 2;
        double midy = (start.getY() + end.getY()) / 2;
        return new Point(midx, midy);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculates the value used for intersection computations.
     *
     * @param other the other line for comparison
     * @return the computed value
     */
    private double getB(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        return ((x4 - x3) * (y2 - y1) - (y4 - y3) * (x2 - x1));
    }

    /**
     * Calculates another value used for intersection computations.
     *
     * @param other  the other line for comparison
     * @param first  the first value for calculation
     * @param second the second value for calculation
     * @return the computed value
     */
    private double getA(Line other, double first, double second) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        return (first * (y3 - y1) - second * (x3 - x1));
    }


    /**
     * Checks if the start and end points of the given line are the same(soo it's not line is a point).
     *
     * @param other the other line to compare with
     * @return true if the start and end points of the other
     * line are the same(soo it's not line is a point), false otherwise
     */
    public boolean isOtherPoint(Line other) {
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        // Check if the start and end points of the other line are the same
        return HF.areEqual(x3, x4) && HF.areEqual(y3, y4);
    }

    /**
     * Checks if this line collides with another line.
     *
     * @param other the other line to check for collision
     * @return true if the lines collide, false otherwise
     */
    public boolean isCollide(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        // Check if other line is completely to the left or right
        if ((x3 > x1 && x3 > x2 && x4 > x2 && x4 > x1) || (x3 < x1 && x3 < x2 && x4 < x2 && x4 < x1)) {
            return false;
        }
        // Check if other line is completely above or below
        return (!(y3 > y1) || !(y3 > y2) || !(y4 > y2)
                || !(y4 > y1)) && (!(y3 < y1) || !(y3 < y2) || !(y4 < y2)
                || !(y4 < y1));
    }

    /**
     * Checks if a given point is between the x-coordinates of the start and end points of this line.
     *
     * @param point the point to check
     * @return true if the point's x-coordinate is between the x-coordinates
     * of the start and end points, false otherwise
     */
    public boolean isBetweenx(Point point) {
        double startX = this.start.getX();
        double endX = this.end.getX();
        double pointX = point.getX();
        // Check if point's x-coordinate is between start and end x-coordinates
        return (pointX >= Math.min(startX, endX) && pointX <= Math.max(startX, endX));
    }

    /**
     * Checks if a given point is between the y-coordinates of the start and end points of this line.
     *
     * @param point the point to check
     * @return true if the point's y-coordinate is between the y-coordinates
     * of the start and end points, false otherwise
     */
    public boolean isBetweenY(Point point) {
        double startY = this.start.getY();
        double endY = this.end.getY();
        double pointY = point.getY();
        // Check if point's y-coordinate is between start and end y-coordinates
        return (pointY >= Math.min(startY, endY) && pointY <= Math.max(endY, startY));
    }

    /**
     * Checks if a given point is on this line.
     *
     * @param point the point to check
     * @return true if the point is on the line, false otherwise
     */
    public boolean isPointOnLine(Point point) {
        double startX = this.start.getX();
        double startY = this.start.getY();
        double endX = this.end.getX();
        double endY = this.end.getY();
        double pointX = point.getX();
        double pointY = point.getY();

        // Handle vertical line case
        if (HF.areEqual(startX - endX, 0)) {
            return isBetweenY(point) && HF.areEqual(startX, pointX);
        }
        // Calculate slope (m) and intercept (b)
        double m = (startY - endY) / (startX - endX);
        double b = startY - m * startX;
        System.out.println(m * pointX + b);
        // Check if the point is on the line y = mx + b
        boolean onLine = HF.areEqual(pointY, m * pointX + b);

        // Check if the point is between the start and end points
        return onLine && isBetweenx(point);
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double a = getA(other, x4 - x3, y4 - y3); // Vector cross product part 1
        double b = getB(other); // Vector cross product part 2
        double c = getA(other, x2 - x1, y2 - y1); // Vector cross product part 3

        // Handle special case where lines are collinear or parallel
        if (HF.areEqual(b, 0)) {
            if (HF.areEqual(a, 0)) {
                if (isOtherPoint(other)) {
                    return isPointOnLine(new Point(x3, y3));
                }
                return isCollide(other);
            } else {
                return false;
            }
        }
        double alpha = a / b; // Parameter for this line
        double beta = c / b; // Parameter for other line
        return alpha >= 0 && beta >= 0 && beta <= 1 && alpha <= 1;
    }


    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 the first other line to check for intersection
     * @param other2 the second other line to check for intersection
     * @return true if this line intersects with both other lines, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * Returns the point that line is touch, and null otherwise.
     *
     * @param other the other line to check for touch
     * @return the point of touching :), null otherwise
     */
    public Point isLineTouch(Line other) {
        if (other.start.equals(start)) {
            return other.start;
        }
        if (other.start.equals(end)) {
            return other.start;
        }
        if (other.end.equals(start)) {
            return other.end;
        }
        if (other.end.equals(end)) {
            return other.end;
        }
        return null;
    }

    /**
     * Returns the intersection point if this line intersects with another line, and null otherwise.
     *
     * @param other the other line to check for intersection
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        if (!isIntersecting(other)) {
            return null;
        }
        if (isOtherPoint(other)) {
            return new Point(other.end.getX(), other.end.getY());
        }
        if (isOtherPoint(new Line(start, end))) {
            return new Point(this.end.getX(), this.end.getY());
        }
        double a = getA(other, x4 - x3, y4 - y3); // Vector cross product part 1
        double b = getB(other); // Vector cross product part 2
        double alpha = a / b; // Parameter for this line

        if (HF.areEqual(b, 0)) {
            if (HF.areEqual(a, 0)) {
                return isLineTouch(other);
            }
        }
        return new Point(x1 + alpha * (x2 - x1), y1 + alpha * (y2 - y1));
    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other line to compare with
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        return (HF.areEqual(x1, x3) && HF.areEqual(y1, y3) && HF.areEqual(x2, x4) && HF.areEqual(y2, y4))
                || (HF.areEqual(x1, x4) && HF.areEqual(y1, y4) && HF.areEqual(x2, x3) && HF.areEqual(y2, y3));
    }

    /**
     * Get Connection Lines.
     *
     * @return Connection Lines
     */
    public ArrayList<Integer> getConnectionLines() {
        return connectionLines;
    }
}
