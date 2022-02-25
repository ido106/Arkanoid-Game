// ID: 319024600 IDO AHARON
package arkanoid.Sprites;

import arkanoid.HitListeners.Counter;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Ido Aharon
 * in charge of displaying the current score.
 * The ScoreIndicator will hold a reference to the scores counter,
 * and will be added to the game as a sprite positioned at the top of the screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    // default screen size for this task
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * Constructor.
     * @param score the first score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        // a little bit to the left
        int x = (WIDTH / 2) - 50;
        int y = 22;
        int fontSize = 20;
        String strScore = "Score: " + this.score.getValue();
        d.drawText(x, y, strScore, fontSize);
    }

    @Override
    public void timePassed() {
        return;
    }
}
