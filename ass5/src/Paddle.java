// 325394997 Ben Tau (not in the Javadoc)
import biuoop.KeyboardSensor;

/**
 * The Paddle class represents a paddle in a game. It extends Block and implements Sprite and Collidable.
 * The paddle can move left and right based on keyboard input, and can handle collisions with balls.
 */
public class Paddle extends Block implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final int speed;

    /**
     * Constructs a Paddle object with the specified upper-left corner, dimensions, color, speed, and GUI.
     *
     * @param upperLeft the upper-left corner of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     * @param color the color of the paddle
     * @param speed the speed of the paddle
     * @param gui the GUI environment
     */
    public Paddle(Point upperLeft, double width, double height, java.awt.Color color,
                  int speed, biuoop.GUI gui) {
        super(upperLeft, width, height, color);
        this.speed = speed;
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Moves the paddle to the left by the defined speed.
     * If moving left exceeds the game boundary, wraps the paddle to the right boundary.
     */
    public void moveLeft() {
        if (this.getUpperLeft().getX() - speed - 22 < 0) {
            setUpperLeft(new Point(Game.WIDTH - this.getWidth() - 22, this.getUpperLeft().getY()));
        } else {
            this.setUpperLeft(new Point(this.getUpperLeft().getX() - speed , this.getUpperLeft().getY()));
        }
    }

    /**
     * Moves the paddle to the right by the defined speed.
     * If moving right exceeds the game boundary, wraps the paddle to the left boundary.
     */
    public void moveRight() {
        if (this.getUpperLeft().getX() + this.getWidth() + speed + 22 > Game.WIDTH) {
            setUpperLeft(new Point(22, this.getUpperLeft().getY()));
        } else {
            this.setUpperLeft(new Point(this.getUpperLeft().getX() + speed, this.getUpperLeft().getY()));
        }
    }

    // Sprite

    /**
     * Implements the timePassed method from the Sprite interface.
     * Moves the paddle left or right based on keyboard input.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    // Collidable

    /**
     * Handles the collision between the paddle and a ball.
     * Changes the ball's velocity based on the collision point and current velocity.
     *
     * @param collisionPoint the point of collision between the paddle and the ball
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double part = this.getWidth() / 5;
        double range = Math.abs(collisionPoint.getX() - this.getUpperLeft().getX());
        int currentPart = Math.abs((int) (range / part));
        int angle = 60 - 30 * currentPart;
        if (angle == -90) {
            angle = -60;
        }
        return this.hit(currentVelocity, angle);
    }

    /**
     * Handles the collision between the paddle and a ball.
     * Changes the ball's velocity based on the paddle's current velocity and the angle of return.
     *
     * @param currentVelocity the current velocity of the ball
     * @param returnAngle the angle of return after the collision
     * @return the new velocity of the ball after the collision
     */
    public Velocity hit(Velocity currentVelocity, double returnAngle) {
        Velocity vel = new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
        if (HF.areEqual(returnAngle, 0)) {
            return vel;
        }
        vel = Velocity.fromAngleAndSpeed(returnAngle, vel.getSpeed());
        return vel;
    }
}
