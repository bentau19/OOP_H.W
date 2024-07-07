// 325394997 Ben Tau (not in the Javadoc)
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * The Rectangle class represents a rectangle in a 2D space defined by an upper-left corner,
 * width, height, and color. It provides methods to retrieve its sides as lines, find intersection
 * points with a line, and manage its dimensions and position.
 */
public class Rectangle {
    public static final String TOP = "top";
    public static final String BOTTOM = "bottom";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    private Point upperLeft;
    private final double width;
    private final double height;
    private final java.awt.Color color;
    protected static final String[] SIDES = {TOP, BOTTOM, LEFT, RIGHT};

    /**
     * Constructs a Rectangle object with the specified upper-left corner, width, height, and color.
     *
     * @param upperLeft the upper-left corner of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, java.awt.Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Returns a dictionary of lines representing the sides of the rectangle.
     *
     * @return a dictionary mapping side names (e.g., "top", "bottom") to Line objects
     */
    public java.util.Dictionary<String, Line> getLines() {
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperRight.getX(), lowerLeft.getY());
        Dictionary<String, Line> dict = new Hashtable<>();
        dict.put(TOP, new Line(upperLeft, upperRight));
        dict.put(BOTTOM, new Line(lowerLeft, lowerRight));
        dict.put(LEFT, new Line(upperLeft, lowerLeft));
        dict.put(RIGHT, new Line(upperRight, lowerRight));
        return dict;
    }

    /**
     * Finds intersection points between the rectangle and a given line.
     *
     * @param line the line to find intersections with
     * @return a list of intersection points with the rectangle, or null if no intersections exist
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.Dictionary<String, Line> lines = getLines();
        java.util.List<Point> points = new java.util.ArrayList<>();
        for (String s : SIDES) {
            Line currentLine = lines.get(s);
            Point currentPoint = currentLine.intersectionWith(line);
            if (currentPoint != null) {
                boolean flag = true;
                for (Point p : points) { // check if the Point already exists
                    if (currentPoint.distance(p) == 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    points.add(currentPoint);
                }
            }
        }
        return points.isEmpty() ? null : points;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the color of the rectangle.
     *
     * @return the color of the rectangle
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Returns the upper-left corner point of the rectangle.
     *
     * @return the upper-left corner point of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Sets the upper-left corner point of the rectangle.
     *
     * @param upperLeft the new upper-left corner point of the rectangle
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }
}
