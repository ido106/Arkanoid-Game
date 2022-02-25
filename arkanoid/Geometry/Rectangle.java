
package arkanoid.Geometry;

import arkanoid.DoubleComparator.CompareDoubles;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Aharon
 *
 * A class that represents a rectangle. Rectangle is represented with upperLeft point,
 * width and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    /**
     * Create a new rectangle with location of upper-left point and width/height.
     * @param upperLeft the upper left point
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line the line to calculate intersection points with
     * @return list with all intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // list for rectangle-line intersection points
        List<Point> allIntersectionPoints = new ArrayList<>();
        // new array for all rectangle lines
        Line[] allRecLines = new Line[4];
        // The left rectangle line
        allRecLines[0] = this.getRecLeftLine();
        // The right rectangle line
        allRecLines[1] = this.getRecRightLine();
        // The upper rectangle line
        allRecLines[2] = this.getRecUpperLine();
        // The lower rectangle line
        allRecLines[3] = this.getRecLowerLine();
        // add intersection points to list
        for (Line recLine : allRecLines) {
            if (line.isIntersecting(recLine)) {
                allIntersectionPoints.add(line.intersectionWith(recLine));
            }
        }
        return allIntersectionPoints;
    }

    /**
     * @return the lower line of the rectangle
     */
    public Line getRecLowerLine() {
        Point lowerLeftPoint = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point lowerRightPoint = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height);
        return new Line(lowerLeftPoint, lowerRightPoint);

    }

    /**
     * @return the upper right line of the rectangle
     */
    public Line getRecUpperLine() {
        Point upperRightPoint = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return new Line(this.upperLeft, upperRightPoint);
    }

    /**
     * @return the rectangle right line
     */
    public Line getRecRightLine() {
        Point upperRightPoint = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerRightPoint = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height);
        // The right rectangle line
        return new Line(upperRightPoint, lowerRightPoint);
    }

    /**
     * @return the rectangle left line
     */
    public Line getRecLeftLine() {
        Point lowerLeftPoint = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        // The left rectangle line
        return new Line(this.upperLeft, lowerLeftPoint);
    }

    /**
     * get the width of the rectangle.
     * @return width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * get the height of the rectangle.
     * @return height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return the upper-left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the lower-left point of the rectangle.
     * @return the lower-left point
     */
    public Point getLowerLeft() {
        if (getRecLowerLine().start().getX() < getRecLowerLine().end().getX()) {
            return this.getRecLowerLine().start();
        } else {
            return this.getRecLeftLine().end();
        }
    }

    /**
     * Returns the lower-right point of the rectangle.
     * @return the lower-right point
     */
    public Point getLowerRight() {
        if (getRecLowerLine().start().getX() < getRecLowerLine().end().getX()) {
            return this.getRecLowerLine().end();
        } else {
            return this.getRecLeftLine().start();
        }
    }

    /**
     * Returns the upper-right point of the rectangle.
     * @return the upper-right point
     */
    public Point getUpperRight() {
        Point p = new Point(this.getUpperLeft().getX() + this.width, this.getUpperLeft().getY());
        return p;
    }

    /**
     * change the x- positioning of the rectangle.
     * @param change the change of the x axis
     */
    public void adjustX(double change) {
        this.upperLeft.setX(this.upperLeft.getX() + change);
    }

    /**
     * private method to check if the collision occur in the upper line.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @return true if the hit happened in the upper line, false otherwise
     */
    public boolean hitAtUpperLine(Point collisionPoint, Velocity currentVelocity) {
        CompareDoubles cmp = new CompareDoubles();
        // check if the x of the collision point is in the bound of the block x
        if (cmp.smallEqual(this.getUpperLeft().getX(), collisionPoint.getX())) {
            if (cmp.smallEqual(collisionPoint.getX(), this.getUpperRight().getX())) {
                // check if the y is in the bounds
                if (cmp.equals(this.getUpperLeft().getY(), collisionPoint.getY())) {
                    // check if the ball goes down
                    if (currentVelocity.getDy() > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * private method to check if the collision occur in the lower line.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @return true if the hit happened in the lower line, false otherwise
     */
    public boolean hitAtLowerLine(Point collisionPoint, Velocity currentVelocity) {
        CompareDoubles cmp = new CompareDoubles();
        // check if the x of the collision point is in the bound of the block x
        if (cmp.smallEqual(this.getLowerLeft().getX(), collisionPoint.getX())) {
            if (cmp.smallEqual(collisionPoint.getX(), this.getLowerRight().getX())) {
                // check if the y is in the bounds
                if (cmp.equals(this.getLowerLeft().getY(), collisionPoint.getY())) {
                    // check if the ball goes up
                    if (currentVelocity.getDy() < 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * private method to check if the collision occur in the left line.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @return true if the hit happened in the left line, false otherwise
     */
    public boolean hitAtLeftLine(Point collisionPoint, Velocity currentVelocity) {
        CompareDoubles cmp = new CompareDoubles();
        // check if the y of the collision point is in the bound of the block
        if (cmp.smallThan(this.getUpperLeft().getY(), collisionPoint.getY())) {
            if (cmp.smallThan(collisionPoint.getY(), this.getLowerLeft().getY())) {
                // check if the x is in the bounds
                if (cmp.equals(this.getUpperLeft().getX(), collisionPoint.getX())) {
                    // check if the ball goes right
                    if (currentVelocity.getDx() > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * private method to check if the collision occur in the right line.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @return true if the hit happened in the upper right, false otherwise
     */
    public boolean hitAtRightLine(Point collisionPoint, Velocity currentVelocity) {
        CompareDoubles cmp = new CompareDoubles();
        // check if the y of the collision point is in the bound of the block
        if (cmp.smallThan(this.getUpperRight().getY(), collisionPoint.getY())) {
            if (cmp.smallThan(collisionPoint.getY(), this.getLowerRight().getY())) {
                // check if the x is in the bounds
                if (cmp.equals(this.getUpperRight().getX(), collisionPoint.getX())) {
                    if (currentVelocity.getDx() < 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
