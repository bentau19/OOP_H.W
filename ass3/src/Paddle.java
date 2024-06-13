import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class Paddle extends Block implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private int speed = 5;
    public Paddle(Point upperLeft, double width, double height, java.awt.Color color,
                  int speed,biuoop.GUI gui) {
        super(upperLeft, width, height, color);
        this.speed = speed;
        this.keyboard = gui.getKeyboardSensor();

    }

    public void moveLeft() {
        if (this.getUpperLeft().getX() - speed < 0) {
            setUpperLeft(new Point(Game.WIDTH - this.getWidth(), this.getUpperLeft().getY()));
        } else {
            this.setUpperLeft(new Point(this.getUpperLeft().getX() - speed, this.getUpperLeft().getY()));
        }
    }
    public void moveRight() {
        if (this.getUpperLeft().getX() + this.getWidth() + speed > Game.WIDTH) {
            setUpperLeft(new Point(0, this.getUpperLeft().getY()));
        } else {
            this.setUpperLeft(new Point(this.getUpperLeft().getX() + speed, this.getUpperLeft().getY()));
        }
    }
    // Sprite
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    public Velocity hit(Velocity currentVelocity, double returnAngle) {
        Velocity vel = new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
        if (HF.areEqual(returnAngle, 0)) {
            return vel;
        }
        vel = Velocity.fromAngleAndSpeed(returnAngle, vel.getSpeed());
        return vel;
    }

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
}