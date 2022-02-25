
package arkanoid.Sprites;

import arkanoid.DoubleComparator.CompareDoubles;
import arkanoid.Geometry.Point;
import arkanoid.Geometry.Rectangle;
import arkanoid.Geometry.Velocity;
import arkanoid.Run.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Ido Aharon
 * The Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {
    // default screen size for this task
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    // default paddle size
    private int paddleHeight;
    private int paddleWidth;
    private biuoop.KeyboardSensor sensor;
    private Block paddle;
    private int paddleSpeed;
    private java.awt.Color color;
    private java.awt.Color borderColor;

    /**
     * Constructor 1: initialize the keyboard and block paddle.
     * @param sensor the keyboard sensor
     * @param paddleSpeed the paddle speed
     * @param paddleHeight the paddle height
     * @param paddleWidth the paddle width
     */
    public Paddle(biuoop.KeyboardSensor sensor, int paddleSpeed, int paddleWidth, int paddleHeight) {
        this.sensor = sensor;
        this.paddleHeight = paddleHeight;
        this.paddleWidth = paddleWidth;
        this.paddle = new Block(calculatePaddlePosition(), paddleWidth, paddleHeight);
        this.paddleSpeed = paddleSpeed;
        // default colors
        this.color = Color.GRAY;
        this.borderColor = Color.BLACK;
    }

    /**
     * move the paddle to the left.
     */
    public void moveLeft() {
        Point upperLeft = new Point(this.paddle.getUpperLeft().getX() - this.paddleSpeed,
                this.paddle.getUpperLeft().getY());
        this.paddle = new Block(upperLeft, getPaddleWidth(), getPaddleHeight());
    }

    /**
     * move the paddle to the right.
     */
    public void moveRight() {
        Point upperLeft = new Point(this.paddle.getUpperLeft().getX() + this.paddleSpeed,
                this.paddle.getUpperLeft().getY());
        this.paddle = new Block(upperLeft, getPaddleWidth(), getPaddleHeight());
    }

    /**
     * move the paddle if the user asked to.
     */
    @Override
    public void timePassed() {
        double lBound = getPaddleHeight(), rBound = WIDTH - getPaddleHeight();
        if (this.sensor.isPressed(KeyboardSensor.LEFT_KEY) && this.paddle.getUpperLeft().getX() > lBound) {
            moveLeft();
            correctPaddleToBound();

        }
        if (this.sensor.isPressed(KeyboardSensor.RIGHT_KEY) && this.paddle.getUpperRight().getX() < rBound) {
            moveRight();
            correctPaddleToBound();
        }
    }

    /**
     * if the paddle crossed the right or left bound, adjust the paddle to the right place.
     */
    private void correctPaddleToBound() {
        if (this.paddle.getUpperLeft().getX() < getPaddleHeight()) {
            this.paddle.adjustX(getPaddleHeight() - this.paddle.getUpperLeft().getX());
        } else if (this.paddle.getUpperRight().getX() > WIDTH - getPaddleHeight()) {
            this.paddle.adjustX((WIDTH - getPaddleHeight()) - this.paddle.getUpperRight().getX());
        }
    }

    /**
     * Draw the paddle on the screen.
     * @param d the draw surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        // calculate the width and height of the paddle
        double width = this.paddle.getWidth();
        double height = this.paddle.getHeight();
        d.setColor(this.color);
        d.fillRectangle((int) paddle.getUpperLeft().getX(), (int) paddle.getUpperLeft().getY(),
                (int) width, (int) height);
        d.setColor(this.borderColor);
        d.drawRectangle((int) paddle.getUpperLeft().getX(), (int) paddle.getUpperLeft().getY(),
                (int) width, (int) height);
    }

    /**
     * get the rectangle of the paddle.
     * @return the rectangle of the paddle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    /**
     * get the paddle Block.
     * @return the paddle block
     */
    public Block getBlock() {
        return this.paddle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // GIVE PRIORITY TO UPPER OR LOWER LINE. IF A HIT DETECTED, RETURN.
        if (this.paddle.getCollisionRectangle().hitAtUpperLine(collisionPoint, currentVelocity)) {
            return upperLineSectionHit(collisionPoint, currentVelocity);
        } else if (this.paddle.getCollisionRectangle().hitAtLowerLine(collisionPoint, currentVelocity)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (this.paddle.getCollisionRectangle().hitAtLeftLine(collisionPoint, currentVelocity)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (this.paddle.getCollisionRectangle().hitAtRightLine(collisionPoint, currentVelocity)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * if a hit in the upper line of the paddle occur, calculate the new velocity angle
     * accordingly to the line segment sector.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity
     */
    private Velocity upperLineSectionHit(Point collisionPoint, Velocity currentVelocity) {
        CompareDoubles cmp = new CompareDoubles();
        // calculate ball speed
        final double ballSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                + Math.pow(currentVelocity.getDy(), 2));
        // set regions angles
        final double firstRegionAngle = 300;
        final double secondRegionAngle = 330;
        final double fourthRegionAngle = 30;
        final double fifthRegionAngle = 60;
        // calculate the gap between every sector
        double gap = (this.paddle.getWidth()) / 5;
        // get collision point x
        double cpx = collisionPoint.getX();
        // calculate the x of every sector
        double x1 = this.paddle.getUpperLeft().getX();
        double x2 = this.paddle.getUpperLeft().getX() + gap;
        double x3 = this.paddle.getUpperLeft().getX() + (2 * gap);
        double x4 = this.paddle.getUpperLeft().getX() + (3 * gap);
        double x5 = this.paddle.getUpperLeft().getX() + (4 * gap);
        double x6 = this.paddle.getUpperLeft().getX() + (5 * gap);
        // check in which sector the collision happened and return the velocity accordingly
        if (cmp.biggerEqualTo(cpx, x1) && cmp.smallThan(cpx, x2)) {
            currentVelocity = Velocity.fromAngleAndSpeed(firstRegionAngle, ballSpeed);
        } else if (cmp.biggerEqualTo(cpx, x2) && cmp.smallThan(cpx, x3)) {
            currentVelocity = Velocity.fromAngleAndSpeed(secondRegionAngle, ballSpeed);
        } else if (cmp.biggerEqualTo(cpx, x3) && cmp.smallThan(cpx, x4)) {
            // if the hit happened in the middle sector, change the dy
            currentVelocity.setDy(-currentVelocity.getDy());
        } else if (cmp.biggerEqualTo(cpx, x4) && cmp.smallThan(cpx, x5)) {
            currentVelocity = Velocity.fromAngleAndSpeed(fourthRegionAngle, ballSpeed);
        } else {
            // if cpx >= x5 && cpx <= x6
            currentVelocity = Velocity.fromAngleAndSpeed(fifthRegionAngle, ballSpeed);
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     * @param g the current game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * calculate the paddle positioning with the screen size and return the upper- left point.
     * @return the upper left point of the paddle
     */
    private Point calculatePaddlePosition() {
        final int gap = getPaddleHeight();
        // place the paddle in the middle of the x-axis
        double x = (WIDTH / 2) - (getPaddleWidth() / 2);
        // make a little gap for the paddle
        double y = HEIGHT - getPaddleHeight() - gap;
        return new Point(x, y);
    }

    /**
     * return the paddle speed.
     * @return the paddle speed
     */
    public int getPaddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * return the paddle height.
     * @return the paddle height
     */
    public int getPaddleHeight() {
        return this.paddleHeight;
    }

    /**
     * return the paddle width.
     * @return the paddle width
     */
    public int getPaddleWidth() {
        return this.paddleWidth;
    }

    /**
     * method to change the paddle color.
     * @param color1 the new paddle color
     */
    public void setColor(java.awt.Color color1) {
        this.color = color1;
    }

    /**
     * change the paddle border color.
     * @param color1 the new paddle border color
     */
    public void setBorderColor(java.awt.Color color1) {
        this.color = color1;
    }
}
