
import java.util.Dictionary;
import java.util.Hashtable;
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
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height, java.awt.Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    // Return a (possibly empty) List of intersection points
    // with the specified line.
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

    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.Dictionary<String, Line> lines = getLines();
        java.util.List<Point> points = new java.util.ArrayList<>();
        for (String s : SIDES) {
            Line currentLine = lines.get(s);
            Point currentPoint = currentLine.intersectionWith(line);
            if (currentPoint != null) {
                boolean flag = true;
                for (Point p : points) { //check if the Point already exist.
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
    // Return the width and height of the rectangle
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public java.awt.Color getColor() {
        return color;
    }
    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return upperLeft;
    }
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }
}