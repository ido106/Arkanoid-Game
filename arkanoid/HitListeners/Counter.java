
package arkanoid.HitListeners;

/**
 * @author Ido Aharon
 * Counter is a simple class that is used for counting things.
 */
public class Counter {
    private int count;

    /**
     * Constructor. set the count to 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * add number to current count.
     * @param number the number to add
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * subtract number from current count.
     * @param number the number to subtract
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * get current count.
     * @return the current count
     */
    public int getValue() {
        return this.count;
    }
}
