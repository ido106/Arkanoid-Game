// ID: 319024600 IDO AHARON
package arkanoid.Sprites.Graphics;

import arkanoid.Geometry.Point;
import arkanoid.Sprites.Sprite;
import biuoop.DrawSurface;

/**
 * @author Ido Aharon
 * create the Filled Circle class to the game graphics.
 */
public class FilledCircle implements Sprite {
    // class variables
    private int radius;
    private java.awt.Color color;
    private Point center;

    /**
     * Constructor.
     * @param center the center point of the circle
     * @param radius the radius of the circle
     * @param color the color of the circle
     */
    public FilledCircle(Point center, int radius, java.awt.Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        return;
    }
}