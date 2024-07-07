// 325394997 Ben Tau (not in the Javadoc)
/**
 * The Collidable interface represents objects that can be collided with.
 * It provides methods to get the collision shape and to notify the object of a collision.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return the collision rectangle of the object
     */
    Rectangle getCollisionRectangle();
    /**
     * Notifies the object that a collision has occurred at a specific point with a given velocity.
     * The method calculates and returns the new velocity expected after the hit.
     *
     * @param collisionPoint the point where the collision occurred
     * @param currentVelocity the current velocity of the object hitting the collidable
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter,Point collisionPoint, Velocity currentVelocity);
}
