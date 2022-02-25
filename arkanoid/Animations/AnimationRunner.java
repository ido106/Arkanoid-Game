// ID: 319024600 IDO AHARON

package arkanoid.Animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Ido Aharon
 * the animation runner of the game. execute the running loop.
 */
public class AnimationRunner {
    // default screen size for this task
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor 1.
     * the frames per second is set as 60 as default.
     */
    public AnimationRunner() {
        this.gui = new biuoop.GUI("Arkanoid", WIDTH, HEIGHT);
        this.framesPerSecond = 60;
    }

    /**
     * a method to run the game loop.
     * @param animation the animation to draw
     */
    public void run(Animation animation) {
        // make the sleeper and calculate the ms per frame
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        // continue the drawing till the animation tells to stop
        while (!animation.shouldStop()) {
            // timing
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            // tell the animation to draw one frame
            animation.doOneFrame(d);
            this.gui.show(d);
            // calculate the used time and keep sleeping the remaining time
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * get the keyboard sensor of the game.
     * @return the keyboard sensor
     */
    public biuoop.KeyboardSensor getKeyboardSensor() {
        return this.gui.getKeyboardSensor();
    }

    /**
     * method to close the gui window.
     */
    public void closeGuiWindow() {
        this.gui.close();
    }
}
