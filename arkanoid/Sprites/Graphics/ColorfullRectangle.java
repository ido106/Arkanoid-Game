

package arkanoid.Sprites.Graphics;

import arkanoid.Geometry.Point;
import arkanoid.Geometry.Rectangle;
import arkanoid.Sprites.Sprite;
import biuoop.DrawSurface;

/**
 * @author Ido Aharon
 * class to represent a colorfull rectangle for the game graphics
 */
public class ColorfullRectangle implements Sprite {
    private Rectangle rectangle;
    private java.awt.Color color;

    /**
     * Constructor.
     * @param upperLeft the upper left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     */
    public ColorfullRectangle(Point upperLeft, double width, double height, java.awt.Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        return;
    }
}
