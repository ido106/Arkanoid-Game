

package arkanoid.Animations;

import biuoop.DrawSurface;

/**
 * @author Ido Aharon
 * Takes care of the animation loop- the drawing and the calculation of whether
 * the game should stop or not happens here instead of the main loop.
 */
public interface Animation {
    /**
     * gets the draw surface and draws one frame.
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * checks if the game should stop.
     * @return true if the game should stop, false otherwise.
     */
    boolean shouldStop();
}
