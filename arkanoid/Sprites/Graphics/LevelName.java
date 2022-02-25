// ID: 319024600 IDO AHARON

package arkanoid.Sprites.Graphics;

import arkanoid.Sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Ido Aharon
 * Class to represent the level name on the screen.
 */
public class LevelName implements Sprite {
    // default screen size for this task
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private String levelName;

    /**
     * Constructor.
     * @param levelName the level name
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        // a little bit to the right
        int x = (WIDTH / 2) + 150;
        int y = 22;
        int fontSize = 20;
        String strLevel = "Level name: " + this.levelName;
        d.drawText(x, y, strLevel, fontSize);
    }

    @Override
    public void timePassed() {
        return;
    }
}