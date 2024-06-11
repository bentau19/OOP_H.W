// 325394997 Ben Tau (not in the Javadoc)
/**
 * The {@code Border} class represents a rectangular border defined by its start and end coordinates.
 * It includes the lines forming the border and calculates the middle point of the rectangle.
 */
public class Border {
    private final int startX, startY, endX, endY;
    /**
     * Constructs a {@code Border} with the specified start and end coordinates.
     * It initializes the lines forming the border and calculates the middle point of the rectangle.
     *
     * @param x1 the `x` coordinate of the start point
     * @param y1 the `y` coordinate of the start point
     * @param x2 the `x` coordinate of the end point
     * @param y2 the `y` coordinate of the end point
     */
    public Border(int x1, int y1, int x2, int y2) {
        this.startY = y1;
        this.startX = x1;
        this.endY = y2;
        this.endX = x2;
    }
    /**
     * getter for endX.
     * @return endX
     */
    public int getEndX() {
        return endX;
    }
    /**
     * getter for endY.
     * @return endY
     */
    public int getEndY() {
        return endY;
    }
    /**
     * getter for startX.
     * @return startX
     */
    public int getStartX() {
        return startX;
    }
    /**
     * getter for startY.
     * @return startY
     */
    public int getStartY() {
        return startY;
    }
}
