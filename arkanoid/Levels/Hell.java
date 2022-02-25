// ID: 319024600 IDO AHARON

package arkanoid.Levels;

import arkanoid.Geometry.Point;
import arkanoid.Geometry.Velocity;
import arkanoid.HitListeners.Counter;
import arkanoid.Sprites.Ball;
import arkanoid.Sprites.Block;
import arkanoid.Sprites.Graphics.Background;
import arkanoid.Sprites.Graphics.Circle;
import arkanoid.Sprites.Graphics.ColorfullLine;
import arkanoid.Sprites.Graphics.FilledRectangle;
import arkanoid.Sprites.Graphics.FilledCircle;
import arkanoid.Sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Aharon
 * Class that represent the Hell level in the arkanoid game.
 */
public class Hell implements LevelInformation {
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
     * Constructor. make the Hell level.
     */
    public Hell() {
        // set the paddle info
        this.paddleSpeed = 13;
        this.paddleWidth = 120;
        this.paddleHeight = 13;
        this.paddleColor = Color.RED;
        // create the lists for balls and blocks
        this.balls = new ArrayList<>();
        this.blocks = new ArrayList<>();
        // create counter for balls
        this.numberOfBalls = new Counter();
        // add two balls to that level
        addBalls(2);
        // create blocks counter
        this.blocksLeft = new Counter();
        // create the blocks for game
        createBlocks();
        // level name
        this.levelName = "Hell";
        // create background (the target)
        this.background = new Background();
        createBackground();
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls.getValue();
    }

    @Override
    public List<Ball> getBalls() {
        return balls;
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
        return this.background;
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
    public Color getPaddleColor() {
        return this.paddleColor;
    }

    /**
     * method to add sum of balls to the game.
     * @param ballsToAdd the number of balls to add to the game.
     */
    public void addBalls(int ballsToAdd) {
        // the balls info in that level
        int ballSpeed = 8;
        int ballRadius = 5;
        // place the balls in the middle of the screen
        Point positionForBall = new Point(WIDTH / 2, HEIGHT -  (3 * paddleHeight));
        for (int i = 0; i < ballsToAdd; i++) {
            Ball ball = new Ball(positionForBall, ballRadius, Color.RED);
            ball.setRandomVelocity(ballSpeed);
            this.balls.add(ball);
            this.numberOfBalls.increase(1);
        }
    }

    /**
     * private method to create the level background.
     */
    private void createBackground() {
        createOutmostBackground();
        createFace();
        createEyesAndBrows();
        createNose();
        createMouth();
        createLeftHand();
        createRightHand();
        createHorns();
        createTail();
    }

    /**
     * create the outmost background.
     */
    private void createOutmostBackground() {
        // create the outmost background
        FilledRectangle outmostBackground = new FilledRectangle(new Point(0, 0), WIDTH, HEIGHT, Color.BLACK);
        this.background.addSprite(outmostBackground);
    }

    /**
     * create the face.
     */
    private void createFace() {
        // create the face circle of the devil
        Point midWindow = new Point(WIDTH / 2, HEIGHT / 2);
        Circle face = new Circle(midWindow, 220, Color.RED);
        this.background.addSprite(face);
    }

    /**
     * create the eyes and brows.
     */
    private void createEyesAndBrows() {
        // create the left eye
        Point leftEyePoint = new Point((WIDTH / 3) + 50, HEIGHT / 3);
        Circle leftEye = new Circle(leftEyePoint, 20, Color.RED);
        this.background.addSprite(leftEye);
        // create the left pupil
        FilledCircle leftPupil = new FilledCircle(leftEyePoint, 5, Color.RED);
        this.background.addSprite(leftPupil);

        // create the right eye
        Point rightEyePoint = new Point((WIDTH / 3) + 220, HEIGHT / 3);
        Circle rightEye = new Circle(rightEyePoint, 20, Color.RED);
        this.background.addSprite(rightEye);
        // create the right pupil
        FilledCircle rightPupil = new FilledCircle(rightEyePoint, 5, Color.RED);
        this.background.addSprite(rightPupil);

        // create the left brow
        Point leftBrowLeftPoint = new Point(leftEyePoint.getX() - 10, leftEyePoint.getY() - 40);
        Point leftBrowRightPoint = new Point(leftEyePoint.getX() + 30, leftEyePoint.getY() - 5);
        ColorfullLine leftBrow = new ColorfullLine(leftBrowLeftPoint, leftBrowRightPoint, Color.RED);
        this.background.addSprite(leftBrow);

        // create the right brow
        Point rightBrowLeftPoint = new Point(rightEyePoint.getX() - 30, rightEyePoint.getY() - 5);
        Point rightBrowRightPoint = new Point(rightEyePoint.getX() + 10, rightEyePoint.getY() - 40);
        ColorfullLine rightBrow = new ColorfullLine(rightBrowLeftPoint, rightBrowRightPoint, Color.RED);
        this.background.addSprite(rightBrow);
    }

    /**
     * create the nose.
     */
    private void createNose() {
        Point midWindow = new Point(WIDTH / 2, HEIGHT / 2);
        // create the nose
        Point p1 = new Point(midWindow.getX() - 20, midWindow.getY());
        Point p2 = new Point(midWindow.getX(), midWindow.getY() + 40);
        ColorfullLine noseLeftLine = new ColorfullLine(p1, p2, Color.RED);
        Point p3 = new Point(midWindow.getX() + 20, midWindow.getY());
        ColorfullLine noseRightLine = new ColorfullLine(p2, p3, Color.RED);
        this.background.addSprite(noseLeftLine);
        this.background.addSprite(noseRightLine);
    }

    /**
     * create the mouth.
     */
    private void createMouth() {
        Point midWindow = new Point(WIDTH / 2, HEIGHT / 2);
        Point p2 = new Point(midWindow.getX(), midWindow.getY() + 130);
        Point p1 = new Point(midWindow.getX() - 100, midWindow.getY() + 100);
        ColorfullLine l1 = new ColorfullLine(p1, p2, Color.RED);
        Point p3 = new Point(midWindow.getX() + 100, midWindow.getY() + 100);
        ColorfullLine l2 = new ColorfullLine(p2, p3, Color.RED);
        this.background.addSprite(l1);
        this.background.addSprite(l2);
    }

    /**
     * create the left hand.
     */
    private void createLeftHand() {
        Point p1 = new Point((WIDTH / 5) + 20, (HEIGHT / 2) + 30);
        Point p2 = new Point((WIDTH / 5) - 30, (HEIGHT / 2) + 30 + 30);
        ColorfullLine l1 = new ColorfullLine(p1, p2, Color.RED);
        this.background.addSprite(l1);

        Point p3 = new Point(WIDTH / 8, 180);
        Point p4 = new Point((WIDTH / 8) + 50, HEIGHT - 100);
        ColorfullLine l2 = new ColorfullLine(p3, p4, Color.RED);
        this.background.addSprite(l2);

        Point p5 = new Point(p3.getX() - 50, p3.getY() - 5);
        ColorfullLine l3 = new ColorfullLine(p3, p5, Color.RED);
        this.background.addSprite(l3);

        Point p7 = new Point(p3.getX() - 10, p3.getY() - 60);
        ColorfullLine l5 = new ColorfullLine(p3, p7, Color.RED);
        this.background.addSprite(l5);

        Point p6 = new Point(p5.getX() - 5, p5.getY() - 50);
        ColorfullLine l4 = new ColorfullLine(p5, p6, Color.RED);
        this.background.addSprite(l4);

        Point p8 = new Point(p3.getX() + 45, p3.getY() - 18);
        ColorfullLine l6 = new ColorfullLine(p3, p8, Color.RED);
        this.background.addSprite(l6);

        Point p9 = new Point(p8.getX() - 8, p8.getY() - 48);
        ColorfullLine l7 = new ColorfullLine(p8, p9, Color.RED);
        this.background.addSprite(l7);
    }

    /**
     * create the right hand.
     */
    private void createRightHand() {
        Point p1 = new Point((WIDTH - 200), (HEIGHT / 2) + 100);
        Point p2 = new Point(p1.getX() + 40, p1.getY() + 40);
        ColorfullLine l1 = new ColorfullLine(p1, p2, Color.RED);
        this.background.addSprite(l1);

        Point p3 = new Point(p2.getX() - 30, p2.getY() + 40);
        ColorfullLine l2 = new ColorfullLine(p2, p3, Color.RED);
        this.background.addSprite(l2);
    }

    /**
     * create the horns.
     */
    private void createHorns() {
        // create the left horn
        Point p1 = new Point((WIDTH / 4) + 34, 160);
        Point p2 = new Point((WIDTH / 4), 100);
        ColorfullLine l1 = new ColorfullLine(p1, p2, Color.RED);
        this.background.addSprite(l1);

        Point p3 = new Point((WIDTH / 4) + 55, 140);
        ColorfullLine l2 = new ColorfullLine(p2, p3, Color.RED);
        this.background.addSprite(l2);

        // create the right horn
        Point p4 = new Point(WIDTH - p2.getX(), p2.getY());
        Point p5 = new Point(WIDTH - p1.getX(), p1.getY());
        ColorfullLine l3 = new ColorfullLine(p4, p5, Color.RED);
        this.background.addSprite(l3);

        Point p6 = new Point(WIDTH - p3.getX() + 10, p3.getY());
        ColorfullLine l4 = new ColorfullLine(p4, p6, Color.RED);
        this.background.addSprite(l4);
    }

    /**
     * create the tail.
     */
    private void createTail() {
        Point midWindow = new Point(WIDTH / 2, HEIGHT / 2);
        Point p1 = new Point(midWindow.getX() + 220, midWindow.getY());
        Point p2 = new Point(p1.getX() + 60, p1.getY() - 40);
        ColorfullLine l1 = new ColorfullLine(p1, p2, Color.RED);
        this.background.addSprite(l1);

        Point p3 = new Point(p2.getX() + 10, p2.getY() + 20);
        ColorfullLine l2 = new ColorfullLine(p2, p3, Color.RED);
        this.background.addSprite(l2);

        Point p4 = new Point(p3.getX() + 70, p3.getY() - 50);
        ColorfullLine l3 = new ColorfullLine(p3, p4, Color.RED);
        this.background.addSprite(l3);

        Point p5 = new Point(p4.getX() - 10, p4.getY() - 5);
        Point p6 = new Point(p4.getX() + 10, p4.getY() + 5);
        ColorfullLine l4 = new ColorfullLine(p5, p6, Color.RED);
        this.background.addSprite(l4);

        Point p7 = new Point(p5.getX() + 25, p5.getY() - 25);
        ColorfullLine l5 = new ColorfullLine(p5, p7, Color.RED);
        this.background.addSprite(l5);

        Point p8 = new Point(p7.getX() - 5, p7.getY() + 35);
        ColorfullLine l6 = new ColorfullLine(p7, p8, Color.RED);
        this.background.addSprite(l6);
    }

    /**
     * create the block for the level.
     */
    private void createBlocks() {
        int height = 20;
        for (int i = 0; i < 3; i++) {
            createBlocksRow((height * i) + 44, height);
        }
    }

    /**
     * create a row of blocks.
     * @param y the y position of the blocks row
     * @param height the height of every block
     */
    private void createBlocksRow(int y, int height) {
        int width = 80;
        for (int i = 80; i < 720; i += width) {
            Point upperLeft = new Point(i, y);
            Block b = new Block(upperLeft.getX(), upperLeft.getY(), width, height, Color.WHITE);
            this.blocks.add(b);
            this.blocksLeft.increase(1);
        }
    }
}