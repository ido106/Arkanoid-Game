

package arkanoid.Levels;

import arkanoid.Generators.ColorGenerator;
import arkanoid.Geometry.Point;
import arkanoid.Geometry.Velocity;
import arkanoid.HitListeners.Counter;
import arkanoid.Sprites.Ball;
import arkanoid.Sprites.Block;
import arkanoid.Sprites.Graphics.Background;
import arkanoid.Sprites.Graphics.ColorfullLine;
import arkanoid.Sprites.Graphics.FilledCircle;
import arkanoid.Sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Aharon
 * Class that represent the sun level in the arkanoid game.
 */
public class Sun implements LevelInformation {
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
    public Sun() {
        // create paddle info
        this.paddleSpeed = 3;
        this.paddleHeight = 20;
        this.paddleWidth = 600;
        this.paddleColor = Color.ORANGE;
        // create lists for balls and blocks
        this.balls = new ArrayList<>();
        this.blocks = new ArrayList<>();
        // create counter for balls
        this.numberOfBalls = new Counter();
        // add 8 balls to the level
        addBalls(8);
        // create counter for the blocks
        this.blocksLeft = new Counter();
        // create the blocks
        createBlocks();
        this.background = new Background();
        this.levelName = "The Sun";
        createBackground();
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls.getValue();
    }

    @Override
    public List<Ball> getBalls() {
        return this.balls;
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
        Point positionForBall = new Point(WIDTH / 2, HEIGHT -  (3 * paddleHeight));
        for (int i = 0; i < ballsToAdd; i++) {
            Ball ball = new Ball(positionForBall, ballRadius, Color.ORANGE);
            ball.setRandomVelocity(ballSpeed);
            this.balls.add(ball);
            this.numberOfBalls.increase(1);
        }
    }

    /**
     * create the background to the sun level.
     */
    private void createBackground() {
        // create sun lines
        Point upperPoint = new Point(120, 140);
        for (int i = 40; i <= 720; i += 10) {
            Point lowerPoint = new Point(i, 250);
            ColorfullLine line = new ColorfullLine(upperPoint, lowerPoint, Color.YELLOW);
            this.background.addSprite(line);
        }

        // create the sun
        Color c1 = new Color(239, 231, 176);
        FilledCircle sun1 = new FilledCircle(upperPoint, 50, c1);
        this.background.addSprite(sun1);

        Color c2 = new Color(236, 215, 73);
        FilledCircle sun2 = new FilledCircle(upperPoint, 40, c2);
        this.background.addSprite(sun2);

        Color c3 = new Color(255, 225, 24);
        FilledCircle sun3 = new FilledCircle(upperPoint, 30, c3);
        this.background.addSprite(sun3);
    }

    /**
     * create blocks for the sun level.
     */
    private void createBlocks() {
        // the borders are set according to the paddle height
        double start = paddleHeight();
        // calculate the width of every block, we want 12 blocks
        double width = (WIDTH - (2 * start)) / 12;
        double end = WIDTH - (2 * start);
        for (double i = start; i < end; i += width) {
            Point upperLeft = new Point(i, 250);
            Block b = new Block(upperLeft.getX(), upperLeft.getY(), width, 30,
                    ColorGenerator.generateRandomColor());
            this.blocksLeft.increase(1);
            this.blocks.add(b);
        }
    }
}
