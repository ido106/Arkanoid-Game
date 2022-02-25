// ID: 319024600 IDO AHARON
package arkanoid.HitListeners;

import arkanoid.Run.GameLevel;
import arkanoid.Sprites.Ball;
import arkanoid.Sprites.Block;

/**
 * @author Ido Aharon
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game the game to remove the blocks from
     * @param removedBlocks the blocks left to remove
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * Also remove this listener from the block that is being removed from the game.
     * @param beingHit the object that got hit
     * @param hitter the ball that hit the object
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        decrease(1);
    }

    /**
     * method to return the counter blocks.
     * @return the counter blocks
     */
    public Counter getCounter() {
        return this.remainingBlocks;
    }

    /**
     * Add the num to the counter.
     * @param num the num to increase
     */
    public void increase(int num) {
        this.remainingBlocks.increase(num);
    }

    /**
     * decrease the num from the counter.
     * @param num the num to decrease.
     */
    public void decrease(int num) {
        this.remainingBlocks.decrease(num);
    }
}
