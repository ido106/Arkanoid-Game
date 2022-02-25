
package arkanoid.HitListeners;

import arkanoid.Sprites.Ball;
import arkanoid.Sprites.Block;

/**
 * @author Ido Aharon
 * Objects that want to be notified of hit events should implement the HitListener interface,
 * and register themselves with a HitNotifier object using its addHitListener method.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the object that got hit
     * @param hitter the ball that hit the object
     */
    void hitEvent(Block beingHit, Ball hitter);
}
