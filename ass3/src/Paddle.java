import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class Paddle extends Block implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private int speed = 5;
    public Paddle(Point upperLeft, double width, double height, java.awt.Color color, int speed,biuoop.GUI gui ) {
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
    //public void drawOn(DrawSurface d);
    // Collidable
    //public Rectangle getCollisionRectangle();
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        java.util.Dictionary<String, Line> lines = getLines();
        Velocity velocity = currentVelocity;
        for (String side : SIDES) {
            Line currentLine = lines.get(side);
            if (currentLine.isPointOnLine(collisionPoint)) {
                if (side.equals(TOP) || side.equals(BOTTOM)) { //if its top or bottom
                    velocity = new Velocity(velocity.getDx(), -velocity.getDy());
                } else { //if its left or right
                    if (side.equals(RIGHT) || side.equals(LEFT)) {
                        velocity = new Velocity(-velocity.getDx(), velocity.getDy());
                    }
                }
            }
        }
        return velocity;
    }
    // Add this paddle to the game.
    //public void addToGame(Game g);
}