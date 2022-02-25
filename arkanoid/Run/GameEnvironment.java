

package arkanoid.Run;

import arkanoid.DoubleComparator.CompareDoubles;
import arkanoid.Geometry.Line;
import arkanoid.Geometry.Point;
import arkanoid.Geometry.Rectangle;
import arkanoid.Sprites.Collidable;
import arkanoid.Sprites.CollisionInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Aharon
 * class that represents our game environment
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor 1: initialize the collidables array list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     * @param c the collidable we have to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     * @param trajectory the trajectory vector (line)
     * @return the closest collision point and the object that we collided with, as CollisionInfo object.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CompareDoubles cmp = new CompareDoubles();
        // we will cast the collidable object to rectangle
        Rectangle rec;
        Point closestHitPoint = null, hitPoint;
        CollisionInfo closestHitPointInfo = null;
        // Make a copy of the Collidables before iterating over them.
        List<Collidable> col1 = new ArrayList<>(this.collidables);
        for (Collidable c: col1) {
            // get the collidable rectangle
            rec = c.getCollisionRectangle();
            // get the closest hit point of line and the current rectangle
            hitPoint = trajectory.closestIntersectionToStartOfLine(rec);
            // if there is no hit point with the rectangle, continue the loop
            if (hitPoint == null) {
                continue;
            }
            /*
             * if we got to this part, we know a collision occur.
             * if there is no closest hit point- set the current hit point as the closest one.
             * else, if the current hit point is closer than the current closest one, replace the
             * closest hit point.
             */
            if (closestHitPoint == null) {
                closestHitPoint = hitPoint;
                closestHitPointInfo = new CollisionInfo(closestHitPoint, c);
            } else {
                double oldDistance = closestHitPoint.distance(trajectory.start());
                double currentDistance = hitPoint.distance(trajectory.start());
                if (cmp.biggerThan(oldDistance, currentDistance)) {
                    closestHitPoint = hitPoint;
                    closestHitPointInfo = new CollisionInfo(closestHitPoint, c);
                }
            }
        }
        // if there in no collision points, the closestHitPointInfo is null anyway.
        return closestHitPointInfo;
    }

    /**
     * the same as the original getClosestCollision method, only with start and end point instead
     * of Line.
     * @param start the beginning of the line
     * @param end the end of the line
     * @return the collision info with the line
     */
    public CollisionInfo getClosestCollision(Point start, Point end) {
        return getClosestCollision(new Line(start, end));
    }

    /**
     * a method to return the collidables list.
     * @return the collidables list
     */
    public List<Collidable> getCollidablesList() {
        return this.collidables;
    }

    /**
     * method to remove a collidable from the collidables list.
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}
