// 325394997 Ben Tau (not in the Javadoc)

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * The SpriteCollection class represents a collection of sprites.
 * It provides methods to add sprites, notify all sprites about the passage of time,
 * and draw all sprites on a DrawSurface.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;

    /**
     * Constructs an empty SpriteCollection.
     */
    public SpriteCollection() {
        sprites = new ArrayList<>();
    }

    /**
     * Constructs a SpriteCollection with an initial list of sprites.
     *
     * @param sprites the initial list of sprites to add to the collection
     */
    public SpriteCollection(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notifies all sprites in the collection that a unit of time has passed.
     * This method invokes the timePassed() method on each sprite.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on a given DrawSurface.
     * This method invokes the drawOn(d) method on each sprite.
     *
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
