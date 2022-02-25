

import arkanoid.Animations.AnimationRunner;
import arkanoid.Levels.DirectHit;
import arkanoid.Levels.Heart;
import arkanoid.Levels.LevelInformation;
import arkanoid.Levels.Sun;
import arkanoid.Levels.Hell;
import arkanoid.Run.GameFlow;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Aharon
 * Creates a main to run the game
 */
public class Ass6Game {
    /**
     * run the game.
     * @param args every argument in args in a game level asked to be run.
     */
    public static void main(String[] args) {
        // create the animation runner and the game flow
        AnimationRunner animationRunner = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(animationRunner);
        List<LevelInformation> levelsFromUser = new ArrayList<>();
        // scan all parameters from args
        for (String s : args) {
            // the level order is: 1- sun, 2-Direct Hit, 3- Heart, 4- Hell.
            switch (s) {
                case "1":
                    levelsFromUser.add(new Sun());
                    break;
                case "2":
                    levelsFromUser.add(new DirectHit());
                    break;
                case "3":
                    levelsFromUser.add(new Heart());
                    break;
                case "4":
                    levelsFromUser.add(new Hell());
                    break;
                default:
                    // if the string was not an existing level continue the loop
                    continue;
            }
        }
        // check if the list is empty. if it is- run the levels by order. else, run as users request.
        if (levelsFromUser.isEmpty()) {
            levelsFromUser.add(new Sun());
            levelsFromUser.add(new DirectHit());
            levelsFromUser.add(new Heart());
            levelsFromUser.add(new Hell());
            gameFlow.runLevels(levelsFromUser);
        } else {
            gameFlow.runLevels(levelsFromUser);
        }
    }
}
