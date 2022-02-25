

package arkanoid.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Ido Aharon
 * decorator-class that will wrap an existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param sensor the keyboard sensor
     * @param key the key that has to be pressed.
     * @param animation the animation that runs.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            } else {
                this.isAlreadyPressed = false;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
