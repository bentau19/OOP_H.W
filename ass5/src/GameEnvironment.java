// 325394997 Ben Tau (not in the Javadoc)
import java.util.ArrayList;

/**
 * The GameEnvironment class is responsible for managing all the collidable objects in the game.
 * It allows adding collidables and finding the closest collision that an object will encounter along a trajectory.
 */
public class GameEnvironment {
    private final java.util.List<Collidable> collidables;

    /**
     * Constructs an empty GameEnvironment.
     */
    public GameEnvironment() {
        collidables = new ArrayList<>();
    }

    /**
     * Constructs a GameEnvironment with a given list of collidables.
     *
     * @param collidables the list of collidables to initialize the environment with
     */
    public GameEnvironment(java.util.List<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * Removes the given collidable from the environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c){collidables.remove(c);}
    /**
     * Returns information about the closest collision that is going to occur
     * if an object moves from the start to the end of the given trajectory.
     * If there is no collision, returns null.
     *
     * @param trajectory the line representing the object's path
     * @return the information about the closest collision, or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo currentCollision = null;
        for (Collidable c : collidables) {
            java.util.List<Point> points = c.getCollisionRectangle().intersectionPoints(trajectory);
            if (points != null) {
                for (Point p : points) {
                    if (currentCollision == null) {
                        currentCollision = new CollisionInfo(p, c);
                    } else {
                        double distFromCur = trajectory.start().distance(currentCollision.collisionPoint());
                        double distFromNew = trajectory.start().distance(p);
                        if (distFromNew < distFromCur) {
                            currentCollision = new CollisionInfo(p, c);
                        }
                    }
                }
            }
        }
        return currentCollision;
    }
}
