// 325394997 Ben Tau (not in the Javadoc)
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Block class represents a rectangular block that can be drawn on a surface,
 * can collide with other objects, and can notify objects of collisions.
 * It implements the Collidable and Sprite interfaces.
 */
public class Block extends Rectangle implements Collidable, Sprite {
    /**
     * Constructs a Block object with a specified upper-left point, width, height, and color.
     *
     * @param upperLeft the upper-left point of the block
     * @param width     the width of the block
     * @param height    the height of the block
     * @param color     the color of the block
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        super(upperLeft, width, height, color);
    }
    /**
     * Returns the collision rectangle of the block.
     *
     * @return the collision rectangle of the block
     */
    public Rectangle getCollisionRectangle() {
        return this;
    }
    /**
     * Notifies the block that a collision has occurred at a specific point with a given velocity.
     * The method calculates and returns the new velocity expected after the hit.
     *
     * @param collisionPoint the point where the collision occurred
     * @param currentVelocity the current velocity of the object hitting the block
     * @return the new velocity expected after the hit
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        java.util.Dictionary<String, Line> lines = getLines();
        Velocity velocity = currentVelocity;
        for (String side : SIDES) {
            Line currentLine = lines.get(side);
            if (currentLine.isPointOnLine(collisionPoint)) {
                if (side.equals(TOP) || side.equals(BOTTOM)) {
                    velocity = new Velocity(velocity.getDx(), -velocity.getDy());
                } else { // if it's left or right
                    if (side.equals(RIGHT) || side.equals(LEFT)) {
                        velocity = new Velocity(-velocity.getDx(), velocity.getDy());
                    }
                }
            }
        }
        return velocity;
    }
    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface the surface to draw the block on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }
    /**
     * Updates the block's state. Currently, this method does nothing.
     */
    public void timePassed() {
    }
    /**
     * Adds the block to the game as both a sprite and a collidable object.
     *
     * @param g the game to add the block to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
