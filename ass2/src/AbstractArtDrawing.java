// 325394997 Ben Tau (not in the Javadoc)

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.Random;

/**
 * The {@code AbstractArtDrawing} class is used to create an abstract art drawing composed of lines,
 * their midpoints, intersection points, and triangles formed by the intersections of the lines.
 */
public class AbstractArtDrawing {
    private static final int RADIUS = 3;
    public static final int HEIGHT = 200;
    public static final int WIDTH = 200;
    private static final int LINES_NUM = 10;

    /**
     * Creates a random line and draws it on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     * @return a randomly generated Line
     */
    public static Line makeRandomLine(DrawSurface d) {
        Random rand = new Random();
        int x1 = rand.nextInt(WIDTH) + 1; // get integer in range 1-200
        int y1 = rand.nextInt(HEIGHT) + 1;
        int x2 = rand.nextInt(WIDTH) + 1; // get integer in range 1-200
        int y2 = rand.nextInt(HEIGHT) + 1;
        d.drawLine(x1, y1, x2, y2);
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Draws the midpoint of a given line on the DrawSurface.
     *
     * @param d    the DrawSurface to draw on
     * @param line the Line whose midpoint will be drawn
     */
    public static void pointMid(DrawSurface d, Line line) {
        Point mid = line.middle();
        int x = (int) mid.getX();
        int y = (int) mid.getY();
        d.drawCircle(x, y, RADIUS);
        d.setColor(Color.blue);
        d.fillCircle(x, y, RADIUS);
    }

    /**
     * Finds and draws the intersection points of an array of lines.
     *
     * @param d        the DrawSurface to draw on
     * @param lines    an array of Lines to check for intersections
     * @param linesnum the number of lines to check
     */
    public static void pointintersection(DrawSurface d, Line[] lines, int linesnum) {
        for (int i = 0; i < linesnum; i++) {
            for (int j = i + 1; j < linesnum; j++) {
                Point point = lines[i].intersectionWith(lines[j]);
                if (point != null) {
                    lines[i].getConnectionLines().add(j); // Track the index of intersecting line
                    d.drawCircle((int) point.getX(), (int) point.getY(), RADIUS);
                    d.setColor(Color.red);
                    d.fillCircle((int) point.getX(), (int) point.getY(), RADIUS);
                }
            }
        }
    }

    /**
     * Draws the triangles formed by the intersections of three lines.
     *
     * @param d     the DrawSurface to draw on
     * @param line1 the first Line
     * @param line2 the second Line
     * @param line3 the third Line
     */
    public static void colorizeTringles(DrawSurface d, Line line1, Line line2, Line line3) {
        Point p1 = line1.intersectionWith(line2);
        Point p2 = line1.intersectionWith(line3);
        Point p3 = line2.intersectionWith(line3);
        d.setColor(Color.green);
        d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
        d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p3.getX(), (int) p3.getY());
        d.drawLine((int) p3.getX(), (int) p3.getY(), (int) p2.getX(), (int) p2.getY());
    }

    /**
     * Finds and draws all triangles formed by the intersection points of lines.
     *
     * @param d        the DrawSurface to draw on
     * @param lines    an array of Lines to check for triangles
     * @param linesnum the number of lines to check
     */
    public static void tringlesFinder(DrawSurface d, Line[] lines, int linesnum) {
        for (int i = 0; i < linesnum; i++) {
            for (int j = 0; j < lines[i].getConnectionLines().size(); j++) {
                int idxOfTouchedLine = lines[i].getConnectionLines().get(j); // First intersecting line
                for (int f = j + 1; f < lines[i].getConnectionLines().size(); f++) {
                    int idxOfTouchedLine2 = lines[i].getConnectionLines().get(f); // Second intersecting line
                    for (int w = 0; w < lines[idxOfTouchedLine].getConnectionLines().size(); w++) {
                        if (lines[idxOfTouchedLine].getConnectionLines().get(w) == idxOfTouchedLine2) {
                            // If there's a common intersecting line, draw the triangle
                            colorizeTringles(d, lines[i], lines[idxOfTouchedLine], lines[idxOfTouchedLine2]);
                            break;
                        }
                        if (lines[idxOfTouchedLine].getConnectionLines().get(w) > idxOfTouchedLine2) {
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * The main method to execute the abstract art drawing.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Abstract Art Drawing", WIDTH, HEIGHT);
        Line[] lines = new Line[LINES_NUM];
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < LINES_NUM; i++) {
            lines[i] = makeRandomLine(d);
        }
        for (Line line : lines) {
            pointMid(d, line);
        }
        pointintersection(d, lines, LINES_NUM);
        tringlesFinder(d, lines, LINES_NUM);
        gui.show(d);
    }
}
