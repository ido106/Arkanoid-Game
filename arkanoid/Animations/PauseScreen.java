

package arkanoid.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Ido Aharon
 * Class to allow a break in the game when the p key is pressed.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor 1.
     * @param k the keyboard of the game.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // draw the pause screen
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
