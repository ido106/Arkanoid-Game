

package arkanoid.Levels;

import arkanoid.Geometry.Point;
import arkanoid.Geometry.Velocity;
import arkanoid.HitListeners.Counter;
import arkanoid.Sprites.Ball;
import arkanoid.Sprites.Block;
import arkanoid.Sprites.Graphics.Background;
import arkanoid.Sprites.Graphics.ColorfullLine;
import arkanoid.Sprites.Graphics.FilledRectangle;
import arkanoid.Sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Aharon
 * Class that represent the heart level in the arkanoid game.
 */
public class Heart implements LevelInformation {
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
     * Constructor.
     */
    public Heart() {
        // create the paddle info
        this.paddleSpeed = 10;
        this.paddleWidth = 700;
        this.paddleHeight = 20;
        this.paddleColor = Color.WHITE;
        // create the lists for ball and blocks
        this.balls = new ArrayList<>();
        this.blocks = new ArrayList<>();
        // create the counters for balls
        this.numberOfBalls = new Counter();
        // add 3 balls to that level
        addBalls(25);
        // create counter for blocks
        this.blocksLeft = new Counter();
        // create the blocks
        createBlocks();
        this.background = new Background();
        this.levelName = "Heart";
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
        int ballSpeed = 10;
        int ballRadius = 5;
        // place the balls in the middle of the screen
        arkanoid.Geometry.Point positionForBall = new Point(WIDTH / 2, HEIGHT -  (3 * paddleHeight));
        for (int i = 0; i < ballsToAdd; i++) {
            Ball ball = new Ball(positionForBall, ballRadius, Color.WHITE);
            ball.setRandomVelocity(ballSpeed);
            this.balls.add(ball);
            this.numberOfBalls.increase(1);
        }
    }

    /**
     * create all blocks for the heart level.
     */
    private void createBlocks() {
        int width = 15;
        // create block columns like heart
        createBlocksColumn(150, 200, 3);
        createBlocksColumn(165, 185, 5);
        createBlocksColumn(180, 170, 7);
        createBlocksColumn(195, 170, 8);
        createBlocksColumn(210, 170, 9);
        createBlocksColumn(225, 185, 9);
        createBlocksColumn(240, 200, 9);
        createBlocksColumn(255, 185, 9);
        createBlocksColumn(270, 170, 9);
        createBlocksColumn(285, 170, 8);
        createBlocksColumn(300, 170, 7);
        createBlocksColumn(315, 185, 5);
        createBlocksColumn(330, 200, 3);
    }

    /**
     * create a blocks column at (x, y), and the width of every square.
     * @param x the x of the block
     * @param y the starting y
     * @param numBlocks number of block in the column.
     */
    private void createBlocksColumn(int x, int y, int numBlocks) {
        int width = 15;
        for (int i = 0; i < numBlocks; i++) {
            Point upperLeft = new Point(x, y + (i * width));
            Block b = new Block(upperLeft.getX(), upperLeft.getY(), width, width, Color.WHITE);
            this.blocksLeft.increase(1);
            this.blocks.add(b);
        }
    }

    /**
     * create the background.
     */
    private void createBackground() {
        // create the outmost background
        Color color = new Color(227, 27, 35);
        FilledRectangle outmostBackground = new FilledRectangle(new Point(0, 0), WIDTH, HEIGHT, color);
        this.background.addSprite(outmostBackground);

        // draw the arrow lines
        Point p1 = new Point(450, 340);
        Point p2 = new Point(700, 500);
        ColorfullLine l1 = new ColorfullLine(p1, p2, Color.WHITE);
        this.background.addSprite(l1);

        // the tips
        Point p3 = new Point(p1.getX(), p1.getY() + 50);
        ColorfullLine l2 = new ColorfullLine(p1, p3, Color.WHITE);
        this.background.addSprite(l2);

        Point p4 = new Point(p1.getX() + 50, p1.getY() - 11);
        ColorfullLine l3 = new ColorfullLine(p1, p4, Color.WHITE);
        this.background.addSprite(l3);

        Point p5 = new Point(720, 540);
        Point p6 = new Point(700, 500);
        ColorfullLine l4 = new ColorfullLine(p5, p6, Color.WHITE);
        this.background.addSprite(l4);

        Point p7 = new Point(740, 500);
        ColorfullLine l5 = new ColorfullLine(p6, p7, Color.WHITE);
        this.background.addSprite(l5);

        Point p8 = new Point(740, 492);
        Point p9 = new Point(690, 492);
        ColorfullLine l6 = new ColorfullLine(p8, p9, Color.WHITE);
        this.background.addSprite(l6);

        Point p10 = new Point(711, 539);
        ColorfullLine l7 = new ColorfullLine(p9, p10, Color.WHITE);
        this.background.addSprite(l7);
    }
}
