// 325394997 Ben Tau (not in the Javadoc)
/**
 * The CollisionInfo class holds information about a collision event.
 * It stores the point at which the collision occurs and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;
    /**
     * Constructs a CollisionInfo object with the specified collision point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurs
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
