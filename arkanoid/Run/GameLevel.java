// ID: 319024600 IDO AHARON
package arkanoid.Run;

import arkanoid.Animations.Animation;
import arkanoid.Animations.AnimationRunner;
import arkanoid.Animations.CountdownAnimation;
import arkanoid.Animations.PauseScreen;
import arkanoid.Animations.KeyPressStoppableAnimation;
import arkanoid.Generators.ColorGenerator;
import arkanoid.Geometry.Point;
import arkanoid.HitListeners.BallRemover;
import arkanoid.HitListeners.BlockRemover;
import arkanoid.HitListeners.Counter;
import arkanoid.HitListeners.ScoreTrackingListener;
import arkanoid.Levels.LevelInformation;
import arkanoid.Sprites.Collidable;
import arkanoid.Sprites.Graphics.LevelName;
import arkanoid.Sprites.Paddle;
import arkanoid.Sprites.Sprite;
import arkanoid.Sprites.SpriteCollection;
import arkanoid.Sprites.ScoreIndicator;
import arkanoid.Sprites.Ball;
import arkanoid.Sprites.Block;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Ido Aharon
 * holds the sprites and the collidables, and will be in charge of the animation.
 */
public class GameLevel implements Animation {
    // default screen size for this task
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * Constructor 1: initialize new sprites list, game environment, and ball.
     * @param levelInformation the level information
     * @param animationRunner the animation runner
     * @param allLevelsScore the all levels score
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner animationRunner, Counter allLevelsScore) {
        this.levelInformation = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.score = allLevelsScore;
        this.running = false;
        this.runner = animationRunner;
        this.keyboard = this.runner.getKeyboardSensor();
    }

    /**
     * Add a collidable to the game environment.
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add a sprite to the sprites list.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        // create the paddle of the game
        Paddle paddle = new Paddle(this.keyboard, this.levelInformation.paddleSpeed(),
                this.levelInformation.paddleWidth(), this.levelInformation.paddleHeight());
        paddle.setColor(this.levelInformation.getPaddleColor());
        Sprite scoreIndicator = new ScoreIndicator(this.score);
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        // create the ball remover listener
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        // create the score tracking listener
        ScoreTrackingListener gameScore = new ScoreTrackingListener(this.score);
        // add the background to the game
        this.sprites.addSprite(this.levelInformation.getBackground());
        // add the score window with the private method
        createScoreWindow();
        // add the ball remover the balls count
        ballRemover.increase(this.levelInformation.numberOfBalls());
        // create the bound blocks with the lower one as the ball killer
        createBoundBlocks(ballRemover, this.levelInformation.paddleHeight());
        // add all blocks to the game
        for (Block b: this.levelInformation.blocks()) {
            blockRemover.increase(1);
            b.addHitListener(blockRemover);
            b.addHitListener(gameScore);
            b.addToGame(this);
        }
        // add the paddle to the game
        paddle.addToGame(this);
        // set the balls environment to the current game environment, also add them to the game
        for (Ball ball: this.levelInformation.getBalls()) {
            ball.setEnvironment(getEnvironment());
            ball.addToGame(this);
        }
        // we add it here because we want it to be drawn after the blocks are drawn
        this.sprites.addSprite(scoreIndicator);
        // add the level name
        Sprite displayName = new LevelName(this.levelInformation.levelName());
        this.sprites.addSprite(displayName);
    }

    /**
     * private method to create six rows of blocks. first block is 12, next 11, etc.
     * the first row y is 100, every line below the previous.
     * the first line number of blocks is 12, next is 11, etc.
     * @param blockRemover the block hit listener
     * @param gameScore the score listener of the game
     * @param paddleHeight the paddle height
     */
    private void addSixBlockRows(BlockRemover blockRemover, ScoreTrackingListener gameScore, int paddleHeight) {
        int rowY = 100;
        int numBlocks = 12;
        int blockWidth = 50;
        int blockHeight = blockWidth / 2;
        for (int i = 0; i < 6; i++) {
            addBlocksRow(gameScore, blockRemover, rowY, numBlocks, ColorGenerator.generateRandomColor(), paddleHeight);
            rowY +=  blockHeight;
            numBlocks--;
        }
    }

    /**
     * creates a row of 40x20 blocks.
     * @param rowY the block row y
     * @param numBlocks the number of the block to draw
     * @param color the color of the blocks
     * @param blockRemover the block hit listener
     * @param gameScore the score listener of the game
     * @param paddleHeight the paddle height
     */
    private void addBlocksRow(ScoreTrackingListener gameScore, BlockRemover blockRemover,
                              int rowY, int numBlocks, java.awt.Color color, int paddleHeight) {
        int blockWidth = 50;
        int blockHeight = blockWidth / 2;
        // THE RIGHTEST BLOCK UPPER LEFT POINT
        Point upperLeft = new Point(WIDTH - paddleHeight, rowY);
        for (int i = 0; i < numBlocks; i++) {
            upperLeft = new Point(upperLeft.getX() - blockWidth, rowY);
            Block b = new Block(upperLeft.getX(), upperLeft.getY(), blockWidth, blockHeight, color);
            blockRemover.increase(1);
            b.addHitListener(blockRemover);
            b.addHitListener(gameScore);
            b.addToGame(this);
        }
    }

    /**
     * method to create the upper score window.
     */
    private void createScoreWindow() {
        int scoreWindowHeight = 30;
        Block b = new Block(0, 0, WIDTH, scoreWindowHeight, Color.WHITE);
        this.sprites.addSprite(b);
    }

    /**
     * create the block bounds in the game.
     * @param ballRemover the hit listener to the lower block- set to the lower block
     * @param paddleHeight the paddle height
     */
    private void createBoundBlocks(BallRemover ballRemover, int paddleHeight) {
        int pHeight = paddleHeight;
        int scoreWindowHeight = 30;
        java.awt.Color color = Color.gray;
        /*
         * the bound blocks will depend on the game paddle (lower block will be under the paddle)
         * the first upper left is for the lower block
         * UPDATE- the lower block is defined as the Death Block- and being set under the gui window
         * the right and the left blocks will be extended to the bottom of the screen
         * UPDATE - the bounds will be lower in order to insert the score place
         */
        Point upperLeft = new Point(0, HEIGHT);
        Block lowerBlock = new Block(upperLeft.getX(), upperLeft.getY(), WIDTH, pHeight, color);
        lowerBlock.addHitListener(ballRemover);
        // change the block border color to be the same as the block color itself
        lowerBlock.setBorderColor(color);
        lowerBlock.addToGame(this);
        // create the upper block
        upperLeft = new Point(0, scoreWindowHeight);
        Block upperBlock = new Block(upperLeft.getX(), upperLeft.getY(), WIDTH, pHeight, color);
        // change the block border color to be the same as the block color itself
        upperBlock.setBorderColor(color);
        // add the upper bound block to the game
        upperBlock.addToGame(this);
        // create the left block
        upperLeft = new Point(0, pHeight + scoreWindowHeight);
        Block leftBlock = new Block(upperLeft.getX(), upperLeft.getY(), pHeight,
                HEIGHT - scoreWindowHeight - pHeight, color);
        // change the block border color to be the same as the block color itself
        leftBlock.setBorderColor(color);
        // add the left block to the game
        leftBlock.addToGame(this);
        // create the right block
        upperLeft = new Point(WIDTH - pHeight,  pHeight + scoreWindowHeight);
        Block rightBlock = new Block(upperLeft.getX(), upperLeft.getY(), pHeight,
                HEIGHT - scoreWindowHeight - pHeight, color);
        // change the block border color to be the same as the block color itself
        rightBlock.setBorderColor(color);
        rightBlock.addToGame(this);
    }

    /**
     * method to return the sprites collection.
     * @return the sprite collection
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * method to return the game environment.
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * remove a collidable from the game.
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove a sprite from the game.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // if there is no more blocks, add 100 points to the score and close the game
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        // if there is no more balls- you lost. close the game
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
        }
        // if the p button is pressed, pause the game
        if (this.keyboard.isPressed("p")) {
            // make a new stoppable pause screen animation
            Animation pauseScreen = new PauseScreen(this.keyboard);
            Animation stopabblePauseScreen = new KeyPressStoppableAnimation(this.runner.getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY, pauseScreen);
            this.runner.run(stopabblePauseScreen);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }


    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        // run the initial countdown before the game begins
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * check if there are balls left in the game.
     * @return true if there are no balls
     */
    public boolean allBallsOut() {
        // check if all balls are spent
        if (this.ballCounter.getValue() == 0) {
            return true;
        }
        return false;
    }
}