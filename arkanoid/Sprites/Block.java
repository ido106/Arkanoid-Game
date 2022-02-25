// ID: 319024600 IDO AHARON
package arkanoid.Sprites;

import arkanoid.DoubleComparator.CompareDoubles;
import arkanoid.Generators.ColorGenerator;
import arkanoid.Geometry.Line;
import arkanoid.Geometry.Point;
import arkanoid.Geometry.Rectangle;
import arkanoid.Geometry.Velocity;
import arkanoid.HitListeners.HitListener;
import arkanoid.HitListeners.HitNotifier;
import arkanoid.Run.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Aharon
 * Block class that implements Collidable interface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private java.awt.Color color;
    private java.awt.Color borderColor;

    /**
     * first constructor, get a rectangle and build the new block with it, with a random color.
     * @param rec the rectangle received
     */
    public Block(Rectangle rec) {
        this.rectangle = rec;
        this.color = ColorGenerator.generateRandomColor();
        this.hitListeners = new ArrayList<>();
        // color of block border is black in default
        this.borderColor = Color.BLACK;
    }

    /**
     * second constructor, get the rectangle parameters and build a new Block with it.
     * @param upperLeft the upper-left point of the Block
     * @param width the width of the block
     * @param height the height of the block
     */
    public Block(Point upperLeft, double width, double height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = ColorGenerator.generateRandomColor();
        this.hitListeners = new ArrayList<>();
        // color of block border is black in default
        this.borderColor = Color.BLACK;
    }

    /**
     * third constructor, make rectangle with x,y of upper left point with width, height.
     * @param x of the upper left point
     * @param y of the upper left point
     * @param width of the block
     * @param height of the block
     */
    public Block(double x, double y, double width, double height) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.color = ColorGenerator.generateRandomColor();
        this.hitListeners = new ArrayList<>();
        // color of block border is black in default
        this.borderColor = Color.BLACK;
    }

    /**
     * fourth constructor, make rectangle with x,y of upper left point with width, height, color.
     * @param x of the upper left point
     * @param y of the upper left point
     * @param width of the block
     * @param height of the block
     * @param color the color of the block
     */
    public Block(double x, double y, double width, double height, java.awt.Color color) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
        // color of block border is black in default
        this.borderColor = Color.BLACK;
    }

    /**
     * get the current collision rectangle.
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity of the ball
     * @param hitter the ball that hit the block
     * @return the velocity expected after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // if the hit occur in the corner
        CollisionInfo info = new CollisionInfo(collisionPoint, this);
        // notify hit to all listeners
        this.notifyHit(hitter);
        if (hitOnCorner(info, currentVelocity) != null) {
            return hitOnCorner(info, currentVelocity);
        }
        // GIVE PRIORITY TO UPPER OR LOWER LINE. IF A HIT DETECTED, RETURN.
        if (this.getCollisionRectangle().hitAtUpperLine(collisionPoint, currentVelocity)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (this.getCollisionRectangle().hitAtLowerLine(collisionPoint, currentVelocity)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (this.getCollisionRectangle().hitAtLeftLine(collisionPoint, currentVelocity)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (this.getCollisionRectangle().hitAtRightLine(collisionPoint, currentVelocity)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * check if the hit occur in one of the block corners.
     * @param hitOccur the hit info
     * @param v the current velocity
     * @return the new velocity
     */
    private Velocity hitOnCorner(CollisionInfo hitOccur, Velocity v) {
        CompareDoubles cmp = new CompareDoubles();
        double hitX = hitOccur.collisionPoint().getX();
        double hitY = hitOccur.collisionPoint().getY();
        Point upperLeft = hitOccur.collisionObject().getCollisionRectangle().getUpperLeft();
        Point upperRight = hitOccur.collisionObject().getCollisionRectangle().getUpperRight();
        Point lowerLeft = hitOccur.collisionObject().getCollisionRectangle().getLowerLeft();
        Point lowerRight = hitOccur.collisionObject().getCollisionRectangle().getLowerRight();
        // check if the hit occur in the upper left corner
        if (cmp.equals(upperLeft.getX(), hitX) && cmp.equals(upperLeft.getY(), hitY)) {
            // if the ball went down
            if (v.getDy() > 0) {
                return new Velocity(v.getDx(), -v.getDy());
            } else if (v.getDy() < 0 && v.getDx() > 0) {
                // if the ball went up
                return new Velocity(-v.getDx(), v.getDy());
            }
        } else if (cmp.equals(upperRight.getX(), hitX) && cmp.equals(upperRight.getY(), hitY)) {
            // if the hit occur in the upper right corner
            if (v.getDy() > 0) {
                // if the ball went down
                return new Velocity(v.getDx(), -v.getDy());
            } else if (v.getDx() < 0 && v.getDy() < 0) {
                // if the ball went up
                return new Velocity(-v.getDx(), v.getDy());
            }
        } else if (cmp.equals(lowerRight.getX(), hitX) && cmp.equals(lowerRight.getY(), hitY)) {
            // if the hit occur in the lower-right corner and the ball went up
            if (v.getDy() < 0) {
                return new Velocity(v.getDx(), -v.getDy());
            } else if (v.getDy() > 0 && v.getDx() < 0) {
                // if the ball went down
                return new Velocity(-v.getDx(), v.getDy());
            }
        } else if (cmp.equals(lowerLeft.getX(), hitX) && cmp.equals(lowerLeft.getY(), hitY)) {
            // if the hit occur in the lower-left corner and the ball went up
            if (v.getDy() < 0) {
                return new Velocity(v.getDx(), -v.getDy());
            } else if (v.getDy() > 0 && v.getDx() > 0) {
                return new Velocity(-v.getDx(), v.getDy());
            }
        }
        return null;
    }

    /**
     * Draw the block on a given surface.
     * @param surface the draw surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // calculate the width and height of every block
        double width = this.rectangle.getWidth();
        double height = this.rectangle.getHeight();
        surface.setColor(this.color);
        surface.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                (int) width, (int) height);
        surface.setColor(this.borderColor);
        surface.drawRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                (int) width, (int) height);
    }

    /**
     * get the upper left point of the block.
     * @return the upper left point
     */
    public Point getUpperLeft() {
        return this.rectangle.getUpperLeft();
    }

    /**
     * get the lower left point of the block.
     * @return the lower left point
     */
    public Point getLowerLeft() {
        return this.rectangle.getLowerLeft();
    }

    /**
     * get the lower right point of the block.
     * @return the lower right point
     */
    public Point getLowerRight() {
        return this.rectangle.getLowerRight();
    }

    /**
     * get the upper right point of the block.
     * @return the upper right point
     */
    public Point getUpperRight() {
        return this.rectangle.getUpperRight();
    }

    /**
     * return the upper line of the Block.
     * @return upper line
     */
    public Line getUpperLine() {
        return this.rectangle.getRecUpperLine();
    }

    /**
     * return the left line of the Block.
     * @return left line
     */
    public Line getLeftLine() {
        return this.rectangle.getRecLeftLine();
    }

    /**
     * return the right line of the Block.
     * @return right line
     */
    public Line getRightLine() {
        return this.rectangle.getRecRightLine();
    }

    /**
     * return the lower line of the Block.
     * @return lower line
     */
    public Line getLowerLine() {
        return this.rectangle.getRecLowerLine();
    }

    /**
     * notify the Sprite that the time passed.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * add the current Block to the game.
     * @param game the current game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * adjust the x axis of the paddle.
     * @param change the change of the x-axis
     */
    public void adjustX(double change) {
        this.rectangle.adjustX(change);
    }

    /**
     * get the width of the block.
     * @return width
     */
    public double getWidth() {
        return this.rectangle.getWidth();
    }

    /**
     * get the height of the block.
     * @return height
     */
    public double getHeight() {
        return this.rectangle.getHeight();
    }

    /**
     * method to change the block color.
     * @param newColor the new color
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * remove the block from the game.
     * @param game the game to remove from
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notifying all of the registered HitListener objects by calling their hitEvent method.
     * @param hitter the ball hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl: listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * method to change the border color to a new color.
     * @param bColor the new color
     */
    public void setBorderColor(Color bColor) {
        this.borderColor = bColor;
    }
}