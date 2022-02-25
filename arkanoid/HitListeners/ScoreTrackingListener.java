
package arkanoid.HitListeners;

import arkanoid.Sprites.Ball;
import arkanoid.Sprites.Block;

/**
 * @author Ido Aharon
 * ScoreTrackingListener is a listener used to update the score
 * counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter the first score to initialize
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
