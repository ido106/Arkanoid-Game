// ID: 319024600 IDO AHARON
package arkanoid.Sprites;

import biuoop.DrawSurface;

/**
 * @author Ido Aharon
 * represents game object that can be drawn to the screen.
 */
public interface Sprite {
    /**
     * method to draw an object(sprite) on the draw surface(screen).
     * @param d the draw surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
