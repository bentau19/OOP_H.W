import java.util.ArrayList;

public class GameEnvironment {
    private java.util.List<Collidable> collidables;
    public GameEnvironment() {
        collidables = new ArrayList<>();
    }
    public GameEnvironment(java.util.List<Collidable> collidables) {
        this.collidables = collidables;
    }
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
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
