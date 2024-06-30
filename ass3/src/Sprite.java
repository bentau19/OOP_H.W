// 325394997 Ben Tau (not in the Javadoc)

import biuoop.DrawSurface;

/**
 * The Sprite interface represents objects that can be drawn on a DrawSurface and
 * can update their state over time.
 */
public interface Sprite {

    /**
     * Draws the sprite on a given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is used to update the sprite's state or behavior.
     */
    void timePassed();

}
