import biuoop.DrawSurface;

import java.awt.*;

public class Block extends Rectangle implements Collidable, Sprite {
    public Block(Point upperLeft, double width, double height,java.awt.Color color) {
        super(upperLeft, width, height, color);
    }

    public Rectangle getCollisionRectangle() {
        return this;
    }
    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
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
    public void drawOn(DrawSurface surface) {

        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());

    }

    public void timePassed() {}
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
