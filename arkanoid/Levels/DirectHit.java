// ID: 319024600 IDO AHARON
package arkanoid.Levels;

import arkanoid.Geometry.Point;
import arkanoid.Geometry.Velocity;
import arkanoid.HitListeners.Counter;
import arkanoid.Sprites.Ball;
import arkanoid.Sprites.Block;
import arkanoid.Sprites.Graphics.Background;
import arkanoid.Sprites.Graphics.FilledRectangle;
import arkanoid.Sprites.Graphics.Circle;
import arkanoid.Sprites.Graphics.ColorfullLine;
import arkanoid.Sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Aharon
 * class that implements the Direct Hit level in the arkanoid game.
 */
public class DirectHit implements LevelInformation {
    // default screen size for this task
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private int paddleSpeed;
    private int paddleHeight;
    private int paddleWidth;
    private java.awt.Color paddleColor;
    private List<Ball> balls;
    private List<Block> blocks;
    private Background background;
    private String levelName;
    private Counter blocksLeft;
    private Counter numberOfBalls;

    /**
     * Constructor. make the Direct Hit level.
     */
    public DirectHit() {
        // set the paddle info
        this.paddleSpeed = 10;
        this.paddleWidth = 80;
        this.paddleHeight = 13;
        this.paddleColor = Color.BLUE;
        // create the lists of the balls and blocks
        this.balls = new ArrayList<>();
        this.blocks = new ArrayList<>();
        // level name
        this.levelName = "Direct Hit";
        // create counter for blocks and balls
        this.blocksLeft = new Counter();
        this.numberOfBalls = new Counter();
        // only one ball in that level
        addBalls(1);
        // create the block in the middle of the target
        createBlock();
        // create background (the target)
        this.background = new Background();
        createBackground();
    }

    /**
     * method to add sum of balls to the game.
     * @param ballsToAdd the number of balls to add to the game.
     */
    public void addBalls(int ballsToAdd) {
        int ballSpeed = 10;
        int ballRadius = 5;
        // place the balls in the middle of the screen
        Point positionForBall = new Point(WIDTH / 2, HEIGHT -  (3 * paddleHeight));
        for (int i = 0; i < ballsToAdd; i++) {
            Ball ball = new Ball(positionForBall, ballRadius, Color.BLUE);
            ball.setRandomVelocity(ballSpeed);
            this.balls.add(ball);
            this.numberOfBalls.increase(1);
        }
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls.getValue();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> allVelocities = new ArrayList<>();
        for (Ball ball: this.balls) {
            allVelocities.add(ball.getVelocity());
        }
        return allVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public int paddleHeight() {
        return this.paddleHeight;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return (Sprite) this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksLeft.getValue();
    }

    @Override
    public List<Ball> getBalls() {
        return this.balls;
    }

    @Override
    public java.awt.Color getPaddleColor() {
        return this.paddleColor;
    }

    /**
     * private method to create the background.
     */
    private void createBackground() {
        // create the outmost background
        FilledRectangle outmostBackground = new FilledRectangle(new Point(0, 0), WIDTH, HEIGHT, Color.CYAN);
        this.background.addSprite(outmostBackground);
        Point centerPoint = new Point((WIDTH / 2) + 70, (HEIGHT / 3));
        // create circles
        for (int i = 150; i >= 50; i -= 50) {
            Circle circle = new Circle(centerPoint, i, Color.BLUE);
            this.background.addSprite(circle);
        }
        // create first line
        Point lineStart = new Point(centerPoint.getX() - 180, centerPoint.getY());
        Point lineEnd = new Point(centerPoint.getX() + 180, centerPoint.getY());
        ColorfullLine line1 = new ColorfullLine(lineStart, lineEnd, Color.BLUE);
        this.background.addSprite(line1);
        // create second line
        Point secondLineStart = new Point(centerPoint.getX(), centerPoint.getY() - 180);
        Point secondLineEnd = new Point(centerPoint.getX(), centerPoint.getY() + 180);
        ColorfullLine line2 = new ColorfullLine(secondLineStart, secondLineEnd, Color.BLUE);
        this.background.addSprite(line2);
    }

    /**
     * private method to create the only block in that level- in the middle of the target.
     */
    private void createBlock() {
        // the same mid point calculation from the CreateBackground method
        Point centerPoint = new Point((WIDTH / 2) + 70, (HEIGHT / 3));
        // adjust the upper left point of the rectangle
        Point upperLeft = new Point(centerPoint.getX() - 20, centerPoint.getY() - 20);
        Block block = new Block(upperLeft.getX(), upperLeft.getY(), 40, 40, Color.RED);
        this.blocks.add(block);
    }
}