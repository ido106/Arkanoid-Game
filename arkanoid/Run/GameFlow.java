

package arkanoid.Run;

import arkanoid.Animations.Animation;
import arkanoid.Animations.AnimationRunner;
import arkanoid.Animations.EndScreen;
import arkanoid.Animations.KeyPressStoppableAnimation;
import arkanoid.HitListeners.Counter;
import arkanoid.Levels.LevelInformation;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Ido Aharon
 * in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private Counter allLevelsScore;
    private boolean lost;

    /**
     * Constructor.
     * @param ar the animation runner
     */
    public GameFlow(AnimationRunner ar) {
        this.animationRunner = ar;
        this.allLevelsScore = new Counter();
        this.lost = false;
    }

    /**
     * class to run the levels.
     * @param levels all game levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        // run all levels
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.allLevelsScore);
            level.initialize();
            level.run();
            // check if the game is lost
            if (level.allBallsOut()) {
                this.lost = true;
                break;
            }

        }
        // run the end screen until the space button is pressed
        if (this.lost) {
            // print a lose screen
            Animation endScreen = new EndScreen(this.allLevelsScore.getValue(), true);
            Animation stoppableEndScreen = new KeyPressStoppableAnimation(this.animationRunner.getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY, endScreen);
            this.animationRunner.run(stoppableEndScreen);
        } else {
            // print a lose screen
            Animation endScreen = new EndScreen(this.allLevelsScore.getValue(), false);
            Animation stoppableEndScreen = new KeyPressStoppableAnimation(this.animationRunner.getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY, endScreen);
            this.animationRunner.run(stoppableEndScreen);
        }
        this.animationRunner.closeGuiWindow();
    }
}
