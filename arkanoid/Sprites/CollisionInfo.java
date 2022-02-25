
package arkanoid.Sprites;

import arkanoid.Geometry.Point;

/**
 * @author Ido Aharon
 * Class that represent a collusion info for the game environment
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObj;

    /**
     * Constructor 1: create the new collision info object. hold the collision point and
     * the cillision object.
     * @param collisionPoint the collision point
     * @param collisionObj the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObj) {
        this.collisionPoint = collisionPoint;
        this.collisionObj = collisionObj;
    }

    /**
     * the point at which the collision occurs.
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     * @return the collision object
     */
    public Collidable collisionObject() {
        return this.collisionObj;
    }
}
