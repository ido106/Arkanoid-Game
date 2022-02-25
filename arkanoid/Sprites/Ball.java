// ID: 319024600 IDO AHARON
package arkanoid.Sprites;

import arkanoid.Generators.ColorGenerator;
import arkanoid.Geometry.Point;
import arkanoid.Geometry.Rectangle;
import arkanoid.Geometry.Velocity;
import arkanoid.Run.GameLevel;
import arkanoid.Run.GameEnvironment;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * @author Ido Aharon
 * class that represents a ball. Ball contains center point, radius, color, and velocity.
 */
public class Ball implements Sprite {
    // default screen size for this task only
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    // class variables
    private int radius;
    private java.awt.Color color;
    private Point center;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructor 1.
     * @param center the center point of the ball
     * @param r the size of the ball- the radius
     * @param color the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Constructor 2 - make a new ball with a random color and default center point.
     * @param radius the size of the ball- the radius
     */
    public Ball(int radius) {
        this.center = new Point(radius, radius);
        this.radius = radius;
        this.color = ColorGenerator.generateRandomColor();
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Constructor 3.
     * @param x the x coordinate of the center point
     * @param y the y coordinate of the center point
     * @param radius the size- the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int radius, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Constructor 4 - the same as the 3rd one except color is random.
     * @param x the x coordinate of the center point
     * @param y the y coordinate of the center point
     * @param radius the size- the radius of the ball
     */
    public Ball(int x, int y, int radius) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = ColorGenerator.generateRandomColor();
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * set the velocity to a new one.
     * @param v the velocity to change to
     */
    public void setVelocity(Velocity v) {
        // set the velocity with existed velocity
        this.velocity = v;
    }

    /**
     * set random velocity to the ball from 35 to 145 degrees.
     * @param speed the ball speed
     */
    public void setRandomVelocity(int speed) {
        Random rand = new Random();
        Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(111) - 55, speed);
        setVelocity(v);
    }

    /**
     * set the velocity as the dx, dy given.
     * @param dx the velocity dx
     * @param dy the velocity dy
     */
    public void setVelocity(double dx, double dy) {
        // set the velocity with dx, dy
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the current ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * move the ball one step with the current center point and velocity.
     * @param xBoundL the LEFT x-coordinate bound for the window
     * @param xBoundR the RIGHT x-coordinate bound for the window
     * @param yBoundU the UPPER y-coordinate bound for the window
     * @param yBoundL the LOWER y-coordinate bound for the window
     */
    public void moveOneStep(int xBoundL, int xBoundR, int yBoundU, int yBoundL) {
        // if there is no velocity for ball, set default velocity (5,5)
        if (this.velocity == null) {
            this.setVelocity(5, 5);
        }
        // catch the before and after movement points
        Point beforeMovement = getCenter();
        Point afterMovement;
        // apply velocity to center point movement
        this.center = this.velocity.applyToPoint(this.center);
        // save the changed point after movement
        afterMovement = getCenter();
        // check if an Block or Obstacle hit occur
        CollisionInfo hitOccur = this.gameEnvironment.getClosestCollision(beforeMovement, afterMovement);
        // if indeed a hit occur, apply velocity and center point movement
        applyIfHitOccur(hitOccur, afterMovement);
    }
    /**
     * Assistant function to moveOneStep - check if a hit of an block or an obstacle occur.
     * if indeed, apply the velocity.
     * @param hitOccur the hit information - if null, no hit occur.
     * @param afterMovement the after movement center point
     */
    private void applyIfHitOccur(CollisionInfo hitOccur, Point afterMovement) {
        if (hitOccur != null) {
            useOppVelocity(hitOccur);
            // get the collision object as Collidable
            Collidable b = hitOccur.collisionObject();
            // set the new velocity
            Velocity v = b.hit(this, hitOccur.collisionPoint(), this.velocity);
            this.setVelocity(v);
        }
    }

    /**
     * private method to use the opposite velocity to correct the ball positioning.
     * @param hitOccur the collision info
     */
    private void useOppVelocity(CollisionInfo hitOccur) {
        Rectangle collisionRectangle;
        collisionRectangle = hitOccur.collisionObject().getCollisionRectangle();
        boolean b1 = collisionRectangle.hitAtUpperLine(hitOccur.collisionPoint(), this.velocity)
                && this.velocity.getDy() > 0;
        boolean b2 = collisionRectangle.hitAtLowerLine(hitOccur.collisionPoint(), this.velocity)
                && this.velocity.getDy() < 0;
        boolean b3 = collisionRectangle.hitAtLeftLine(hitOccur.collisionPoint(), this.velocity)
                && this.velocity.getDx() > 0;
        boolean b4 = collisionRectangle.hitAtRightLine(hitOccur.collisionPoint(), this.velocity)
                && this.velocity.getDx() < 0;
        if (b1 || b2 || b3 || b4) {
            Velocity oppVelocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
            // apply velocity to center point movement
            this.center = oppVelocity.applyToPoint(this.center);
        }
    }

    /**
     * Assistant function to moveOneStep - if the ball passed the borders of the frames,
     * perform a correction to center point.
     * @param xBoundL the LEFT x-coordinate bound for the window
     * @param xBoundR the RIGHT x-coordinate bound for the window
     * @param yBoundU the UPPER y-coordinate bound for the window
     * @param yBoundL the LOWER y-coordinate bound for the window
     */
    private void centerToFrameCorrection(int xBoundL, int xBoundR, int yBoundU, int yBoundL) {
        // correction to frame bounds if the ball passed the window borders
        if (this.center.getX() < (xBoundL + this.radius)) {
            setX(xBoundL + getSize());
        }
        if (this.center.getX() > (xBoundR - this.radius)) {
            setX(xBoundR - getSize());
        }
        if (this.center.getY() < (yBoundU + this.radius)) {
            setY(yBoundU + getSize());
        }
        if (this.center.getY() > (yBoundL - this.radius)) {
            setY(yBoundL - getSize());
        }
    }

    /**
     * Assistant function to moveOneStep - correct the velocity of the ball due to hit of the borders.
     * Also, if the ball are out of bound- apply a correction to the center point.
     * @param xBoundL the LEFT x-coordinate bound for the window
     * @param xBoundR the RIGHT x-coordinate bound for the window
     * @param yBoundU the UPPER y-coordinate bound for the window
     * @param yBoundL the LOWER y-coordinate bound for the window
     */
    private void moveOneStepBorders(int xBoundL, int xBoundR, int yBoundU, int yBoundL) {
        // change direction when the ball touches the left border
        if (this.center.getX() <= (xBoundL + this.radius) && this.velocity.getDx() < 0) {
            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        // change direction when the ball touches the right border
        if (this.center.getX() >= (xBoundR - this.radius) && this.velocity.getDx() > 0) {
            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        // change direction when the ball touches the upper border
        if (this.center.getY() <= (yBoundU + this.radius) && this.velocity.getDy() < 0) {
            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
        }
        // change direction when the ball touches the lower border
        if (this.center.getY() >= (yBoundL - this.radius) && this.velocity.getDy() > 0) {
            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
        }
    }

    /**
     * function that calls the moveOneStep method with the default size of the window (in ball class).
     */
    public void moveOneStep() {
        moveOneStep(0, WIDTH, 0, HEIGHT);
    }

    /**
     * change the center point to the ball.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setCenterPoint(int x, int y) {
        //change the center point to the (x,y) received
        this.center = new Point(x, y);
    }

    /**
     * @return the x coordinate of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y coordinate of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the size of the ball- the radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface the draw surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * change the ball radius.
     * @param newRadius the desired ball radius
     */
    public void setRadius(int newRadius) {
        this.radius = newRadius;
    }

    /**
     * function that checks if the current ball is larger than the borders received.
     * @param width the width of the required window
     * @param height the height of the required window
     * @return true if the ball is too big for the window, false otherwise.
     */
    public boolean ballTooBig(int width, int height) {
        if (2 * (this.radius) > width || 2 * (this.radius) > height) {
            return true;
        }
        return false;
    }

    /**
     * return the game environment of the ball.
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * set new x to the ball center point.
     * @param x the new x
     */
    public void setX(double x) {
        this.center.setX(x);
    }

    /**
     * set new y to the ball center point.
     * @param y the new y
     */
    public void setY(double y) {
        this.center.setY(y);
    }

    /**
     * get the center point of the ball.
     * @return center point
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * notify the Sprite that the time passed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * method to change to game environment of the ball.
     * @param game the new game environment
     */
    public void setEnvironment(GameEnvironment game) {
        this.gameEnvironment = game;
    }

    /**
     * add the ball to the game.
     * @param game the current game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * set a new color to the ball.
     * @param newColor the new color
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * method to change to game environment of the ball.
     * @param game the new game environment
     */
    public void setGameEnvironment(GameEnvironment game) {
        this.gameEnvironment = game;
    }

    /**
     * remove the current ball from the game, activated when the ball hits
     * the death block on the lower border.
     * @param g the current game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}