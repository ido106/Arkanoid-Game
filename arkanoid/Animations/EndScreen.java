

package arkanoid.Animations;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Ido Aharon
 * Once the game is over (either the player died, or he managed to clear all the levels),
 * we will display the final score. If the game ended with the player dying (i.e. all balls fall off the screen),
 * the end screen should display the message "Game Over. Your score is X" (X being the final score).
 * If the game ended by clearing all the levels, the screen should display "You Win! Your score is X".
 * The "end screen" should persist until the space key is pressed.
 * After the space key is pressed, your program should terminate.
 */
public class EndScreen implements Animation {
    private int finalScore;
    private boolean lost;
    private boolean stop;

    /**
     * constructor.
     * @param finalScore the final score of the player
     * @param lost if the game is lost or won
     */
    public EndScreen(int finalScore, boolean lost) {
        this.finalScore = finalScore;
        this.lost = lost;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // draw the pause screen
        if (this.lost) {
            d.setColor(Color.RED);
            d.drawText((d.getWidth() / 2) - 300, d.getHeight() / 2,
                    "Game over :(    your final score is: " + this.finalScore, 32);
        } else {
            d.setColor(Color.GREEN);
            d.drawText((d.getWidth() / 2) - 300, d.getHeight() / 2,
                    "You WON !    your final score is: " + this.finalScore, 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
