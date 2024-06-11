// 325394997 Ben Tau (not in the Javadoc)

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Ball class represents a ball with a position, radius, color, and velocity.
 * The ball can move within a specified border and can avoid certain bad areas.
 */
public class Ball {
    private Point point;
    private final int radius;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);
    private Border border;
    private final ArrayList<Border> badAreas = new ArrayList<>();

    /**
     * Constructs a Ball object with a specified center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        radius = r;
        this.color = color;
        setBorder(new Border(0, 0, HF.SCREEN_WIDTH, HF.SCREEN_HEIGHT));
    }

    /**
     * Constructs a Ball object with specified position, radius, color, border, and bad areas.
     *
     * @param x        the x-coordinate of the ball's center
     * @param y        the y-coordinate of the ball's center
     * @param r        the radius of the ball
     * @param color    the color of the ball
     * @param b        the border within which the ball can move
     * @param badAreas the list of bad areas the ball should avoid
     */
    public Ball(int x, int y, int r, java.awt.Color color, Border b, ArrayList<Border> badAreas) {
        this.point = new Point(x, y);
        radius = r;
        this.color = color;
        setBorder(b);
        for (Border area : badAreas) {
            addBadArea(area);
        }
    }

    /**
     * Adds a random Ball object within the specified grey screen border, avoiding bad areas.
     *
     * @param greyScreen the border within which the ball can move
     * @param rad        the radius of the ball
     * @param rand       the Random object used to generate random positions
     * @param badAreas   the list of bad areas the ball should avoid
     * @return a new Ball object
     */
    public static Ball addRandBall(Border greyScreen, int rad, Random rand, ArrayList<Border> badAreas) {
        int x1 = rand.nextInt(greyScreen.getEndX() - rad
                - (greyScreen.getStartY() + rad)) + 1 + greyScreen.getStartX() + rad;
        int y1 = rand.nextInt(greyScreen.getEndY() - rad
                - (greyScreen.getStartY() + rad)) + 1 + greyScreen.getStartY() + rad;
        Ball ball = new Ball(x1, y1, rad, Color.GREEN, greyScreen, badAreas);
        for (Border area : badAreas) {
            Border a = new Border(area.getStartX() - rad,
                    area.getStartY() - rad, area.getEndX() + rad,
                    area.getEndY() + rad);
            if (ball.isInBadArea(a, ball.point)) {
                return addRandBall(greyScreen, rad, rand, badAreas);
            }
        }
        return ball;
    }

    /**
     * Constructs a Ball object with specified position, radius, and color.
     *
     * @param x     the x-coordinate of the ball's center
     * @param y     the y-coordinate of the ball's center
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        radius = r;
        this.color = color;
        setBorder(new Border(0, 0, HF.SCREEN_WIDTH, HF.SCREEN_HEIGHT));
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
        velocity = new Velocity(dx, dy);
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
     * Sets the border within which the ball can move.
     *
     * @param border the new border
     */
    public void setBorder(Border border) {
        int x2 = border.getEndX() - this.radius;
        int y2 = border.getEndY() - this.radius;
        int x1 = border.getStartX() + this.radius;
        int y1 = border.getStartY() + this.radius;
        this.border = new Border(x1, y1, x2, y2);
    }

    /**
     * Adds a bad area to the list of bad areas the ball should avoid.
     *
     * @param border the bad area to add
     */
    public void addBadArea(Border border) {
        badAreas.add(border);
    }

    /**
     * Checks if the ball is in a bad area.
     *
     * @param border the bad area to check
     * @param point  the current point of the ball
     * @return true if the ball is in the bad area, false otherwise
     */
    public Boolean isInBadArea(Border border, Point point) {
        return point.getX() < border.getEndX() && point.getX() > border.getStartX()
                && point.getY() < border.getEndY() && point.getY() > border.getStartY();
    }

    /**
     * Finds the closest point on the border to the given point.
     *
     * @param border the border to find the closest point on
     * @param point  the point to find the closest point to
     * @return the closest point on the border
     */
    private Point closestPoint(Border border, Point point) {
        double x = point.getX();
        double y = point.getY();
        if (x < border.getStartX()) {
            x = border.getStartX();
        } else if (x > border.getEndX()) {
            x = border.getEndX();
        }
        if (y < border.getStartY()) {
            y = border.getStartY();
        } else if (y > border.getEndY()) {
            y = border.getEndY();
        }
        return new Point(x, y);
    }

    /**
     * Gets the direction of the ball based on its velocity.
     *
     * @return an array representing the direction ('T' for top, 'B' for bottom, 'R' for right, 'L' for left)
     */
    public char[] getDir() {
        char[] dir = new char[2];
        if (this.velocity.getDy() > 0) {
            dir[0] = 'T';
        } else {
            dir[0] = 'B';
        }
        if (this.velocity.getDx() > 0) {
            dir[1] = 'R';
        } else {
            dir[1] = 'L';
        }
        return dir;
    }

    /**
     * Moves the ball one step, checking for collisions with borders and bad areas.
     */
    public void moveOneStep() {
        this.point = velocity.applyToPoint(point);
        int dx = 1;
        int dy = 1;

        // Check for border collision and adjust direction
        if (this.point.getX() > this.border.getEndX() || this.point.getX() < this.border.getStartX()) {
            dx = -1;
        }
        if (point.getY() > this.border.getEndY() || point.getY() < this.border.getStartY()) {
            dy = -1;
        }

        for (Border border : badAreas) {
            char[] dir = getDir();
            if (isInBadArea(border, this.point)) {
                double vertDis;
                double horizDis;
                if (dir[0] == 'B') {
                    vertDis = border.getEndY() - point.getY();
                } else {
                    vertDis = point.getY() - border.getStartY();
                }

                if (dir[1] == 'R') {
                    horizDis = point.getX() - border.getStartX();
                } else {
                    horizDis = border.getEndX() - point.getX();
                }
                // Determine which distance is smaller to decide the bounce direction
                if (horizDis < vertDis) {
                    dx = -1;
                } else {
                    dy = -1;
                }

            } else {
                Point closest = closestPoint(border, this.point);
                if (closest.distance(this.point) <= this.radius) {
                    if (HF.areEqual(closest.getX() - this.point.getX(), 0)) {
                        dy = -1;
                    }
                    if (HF.areEqual(closest.getY() - this.point.getY(), 0)) {
                        dx = -1;
                    } else if (!HF.areEqual(closest.getX() - this.point.getX(), 0)) {
                        dx = -1;
                        dy = -1;
                    }
                }
            }
        }

        // Adjust velocity and position based on collision
        if (dx == -1 || dy == -1) {
            setVelocity(-1 * this.velocity.getDx(), -1 * this.velocity.getDy());
            this.point = velocity.applyToPoint(point);
            setVelocity(-dx * this.velocity.getDx(), -dy * this.velocity.getDy());
            this.point = velocity.applyToPoint(point);
        }
    }

}
