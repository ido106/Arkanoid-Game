
package arkanoid.DoubleComparator;

/**
 * @author Ido Aharon
 * Class to compare between two doubles. particularly for line, point, paddle, block classes.
 */
public class CompareDoubles {
    /**
     * check equality between two doubles with the epsylon method.
     * @param d1 the first double
     * @param d2 the second double
     * @return true if the doubles are equal, false otherwise
     */
    public boolean equals(double d1, double d2) {
        double epsylon = 0.000001d;
        if (Math.abs(d1 - d2) < epsylon) {
            return true;
        }
        return false;
    }

    /**
     * check if the first double is smaller or equal to the second double.
     * @param d1 the first double
     * @param d2 the second doubles
     * @return true if the first double is smaller or equal to the second double, false otherwise.
     */
    public boolean smallEqual(double d1, double d2) {
        if (this.equals(d1, d2)) {
            return true;
        } else if (d1 < d2) {
            return true;
        }
        return false;
    }

    /**
     * check if the first double is bigger or equal to the second double.
     * @param d1 the first double
     * @param d2 the second doubles
     * @return true if the first double is bigger or equal to the second double, false otherwise.
     */
    public boolean biggerEqualTo(double d1, double d2) {
        if (this.equals(d1, d2)) {
            return true;
        } else if (d1 > d2) {
            return true;
        }
        return false;
    }

    /**
     * check if the first double is bigger than second double.
     * @param d1 the first double
     * @param d2 the second double
     * @return true if the first double is bigger than second double, false otherwise.
     */
    public boolean biggerThan(double d1, double d2) {
        if (!this.smallEqual(d1, d2)) {
            return true;
        }
        return false;
    }

    /**
     * check if the two doubles are different.
     * @param d1 the first double
     * @param d2 the second double
     * @return true if the two doubles are different, false otherwise.
     */
    public boolean different(double d1, double d2) {
        if (this.equals(d1, d2)) {
            return false;
        }
        return true;
    }

    /**
     * check if the first double is smaller than the second double.
     * @param d1 the first double
     * @param d2 the second double
     * @return true if the first double is smaller than the second double, false otherwise.
     */
    public boolean smallThan(double d1, double d2) {
        if (this.equals(d1, d2)) {
            return false;
        } else if (d1 < d2) {
            return true;
        }
        return false;
    }
}
