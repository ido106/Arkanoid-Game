
package arkanoid.Sprites;

import arkanoid.Geometry.Point;
import arkanoid.Geometry.Rectangle;
import arkanoid.Geometry.Velocity;

/**
 * @author Ido Aharon
 *
 * The Collidable interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * @return the collision shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity of the ball
     * @param hitter the ball that hit the collidable
     * @return the velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
