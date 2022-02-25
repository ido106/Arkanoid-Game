

package arkanoid.Sprites.Graphics;

import arkanoid.Geometry.Line;
import arkanoid.Geometry.Point;
import arkanoid.Sprites.Sprite;
import biuoop.DrawSurface;

/**
 * @author Ido Aharon
 * colorfull line for game graphics.
 */
public class ColorfullLine implements Sprite {
    private Line line;
    private java.awt.Color color;

    /**
     * Constructor.
     * @param start line start
     * @param end line end
     * @param color line color
     */
    public ColorfullLine(Point start, Point end, java.awt.Color color) {
        this.line = new Line(start, end);
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.line.start().getX(), (int) this.line.start().getY(),
                (int) this.line.end().getX(), (int) this.line.end().getY());
    }

    @Override
    public void timePassed() {
        return;
    }
}
