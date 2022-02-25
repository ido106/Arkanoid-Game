
package arkanoid.HitListeners;

import arkanoid.Run.GameLevel;
import arkanoid.Sprites.Ball;
import arkanoid.Sprites.Block;

/**
 * @author Ido Aharon
 * BallRemover will be in charge of removing balls, and updating an availabe-balls counter.
 * Register the BallRemover as a listener of the death-region block,
 * so that BallRemover will be notified whenever a ball hits the death-region.
 * Whenever this happens, the BallRemover will remove the ball from the game.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param game the game to remove the blocks from
     * @param removedBalls the block that have been removed
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        decrease(1);
    }


    /**
     * Add the num to the counter.
     * @param num the num to increase
     */
    public void increase(int num) {
        this.remainingBalls.increase(num);
    }

    /**
     * decrease the num from the counter.
     * @param num the num to decrease.
     */
    public void decrease(int num) {
        this.remainingBalls.decrease(num);
    }
}
