// ID: 319024600 IDO AHARON
package arkanoid.Sprites.Graphics;

import arkanoid.Sprites.Sprite;
import arkanoid.Sprites.SpriteCollection;
import biuoop.DrawSurface;

/**
 * @author Ido Aharon
 * class to represent a background to a game
 */
public class Background implements Sprite {
    private SpriteCollection allBackgroundSprites;

    /**
     * Constructor.
     */
    public Background() {
        allBackgroundSprites = new SpriteCollection();
    }

    /**
     * add the sprite to the background.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.allBackgroundSprites.addSprite(s);
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.allBackgroundSprites.drawAllOn(d);
    }

    @Override
    public void timePassed() {
        return;
    }
}
