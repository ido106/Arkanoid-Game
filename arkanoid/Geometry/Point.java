
package arkanoid.Geometry;

import arkanoid.DoubleComparator.CompareDoubles;

/**
 * @author Ido Aharon
 *
 * Class that represent a Point. has an x and y values, distance to other points,
 * and function to check whether the point is equal to another one or not.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor 1: build the point with x,y values.
     * @param x is the x value of the point
     * @param y is the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor 2: build the point with an already-made point.
     * @param p the point to copy
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * return the distance of this points to another point.
     * @param other represent the other point
     * @return the distance between the points
     */
    public double distance(Point other) {
        double res;
        res = Math.pow((Math.abs(other.getX() - this.x)), 2)
                + Math.pow((Math.abs(other.getY() - this.y)), 2);
        res = Math.sqrt(res);
        return res;
    }

    /**
     * check if the points are equal.
     * @param other represent the other point
     * @return true if both points are equal, else return false
     */
    public boolean equals(Point other) {
        CompareDoubles cmp = new CompareDoubles();
        if (cmp.equals(other.getX(), this.x) && cmp.equals(other.getY(), this.y)) {
            return true;
        }
        return false;
    }

    /**
     * Get the x value of the points.
     * @return x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get the y value of the point.
     * @return y
     */
    public double getY() {
        return this.y;
    }

    /**
     * set the x point to a new value.
     * @param newX the new x
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * set the y point to a new value.
     * @param newY the new y
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * change the point to a whole new other point.
     * @param p the new point
     */
    public void setPoint(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * adjust the x-axis of the point.
     * @param addition the addition to the x axis
     */
    public void adjustX(double addition) {
        this.x = addition;
    }
}
