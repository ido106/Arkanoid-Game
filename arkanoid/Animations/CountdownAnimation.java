// ID: 319024600 IDO AHARON

package arkanoid.Animations;

import arkanoid.Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * @author Ido Aharon
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int initialCount;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long timeToSleep;

    /**
     * Constructor 1.
     * @param numOfSeconds seconds to display the given gameScreen
     * @param countFrom the countdown
     * @param gameScreen the sprites on the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.initialCount = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.timeToSleep = (long) (1000 * numOfSeconds / countFrom);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // set the sleeper for the countdown
        Sleeper sleeper = new Sleeper();
        // draw all sprites of the game on the screen
        this.gameScreen.drawAllOn(d);
        // set the color to red and print the countdown on the screen
        d.setColor(Color.RED);
        d.drawText((d.getWidth() / 2) - 10, 90, this.countFrom + "...", 50);
        // sleep only if it's not the beginning of the count, avoid white screen
        if (this.countFrom != this.initialCount) {
            sleeper.sleepFor(this.timeToSleep);
        }
        // subtract the count by one
        this.countFrom--;
        // if the count reached the end (0), pause the counting animation
        if (this.countFrom == -1) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}