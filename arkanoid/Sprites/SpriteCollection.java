
package arkanoid.Sprites;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * @author Ido Aharon
 * class that represent a sprite collection to ease the printing process
 */
public class SpriteCollection {
    private List<Sprite> spritesList;

    /**
     * Constructor 1: initialize the sprites list.
     */
    public SpriteCollection() {
        spritesList = new ArrayList<>();
    }

    /**
     * Add a sprite to the sprite list.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    // call timePassed() on all sprites.

    /**
     * call timePassed() to all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> sprites = new ArrayList<>(this.spritesList);
        for (Sprite currentSprite: sprites) {
            currentSprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d the draw surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite currentSprite : spritesList) {
            currentSprite.drawOn(d);
        }
    }

    /**
     * remove one sprite from the sprites list.
     * @param s the sprites to remove
     */
    public void removeSprite(Sprite s) {
        this.spritesList.remove(s);
    }
}
