// ID: 319024600 IDO AHARON
package arkanoid.Levels;

import arkanoid.Geometry.Velocity;
import arkanoid.Sprites.Ball;
import arkanoid.Sprites.Block;
import arkanoid.Sprites.Sprite;
import java.util.List;

/**
 * @author Ido Aharon
 * specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * method to return the number of balls in the game.
     * @return the number of balls in the game
     */
    int numberOfBalls();

    /**
     * return a list with all balls of the game.
     * @return all balls of the game
     */
    List<Ball> getBalls();

    /**
     * The initial velocity of each ball. Note that initialBallVelocities().size() == numberOfBalls()
     * @return list with the velocities of all balls
     */
    List<Velocity> initialBallVelocities();

    /**
     * returns the paddle speed in the game.
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * returns the paddle width.
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * return the paddle height.
     * @return the paddle height
     */
    int paddleHeight();

    /**
     * the level name will be displayed at the top of the screen.
     * @return the level name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return the list of all blocks in the level
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return the number of blocks left in the level
     */
    int numberOfBlocksToRemove();

    /**
     * method to get the paddle color.
     * @return the paddle color
     */
    java.awt.Color getPaddleColor();
}