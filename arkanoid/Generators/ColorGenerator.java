
package arkanoid.Generators;

import java.util.Random;
import java.awt.Color;

/**
 * @author Ido Aharon
 * simple class to generate colors
 */
public class ColorGenerator {

    /**
     * Generate random color.
     * @return random color
     */
    public static java.awt.Color generateRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        Color randomColor = new Color(r, g, b);
        return randomColor;
    }
}
