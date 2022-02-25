// ID: 319024600 IDO AHARON
package arkanoid.Geometry;

import arkanoid.DoubleComparator.CompareDoubles;
import java.util.List;

/**
 * @author Ido Aharon
 * Class that represent a finite line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor 1.
     * @param start the beginning of the line by point
     * @param end the end of the line by point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor 2.
     * @param x1 x-coordinate of the first point
     * @param y1 y-coordinate of the first point
     * @param x2 x-coordinate of the second point
     * @param y2 y-coordinate of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * function to check the length of the line.
     * @return the length of the line
     */
    public double length() {
        double res = this.start.distance(this.end);
        return res;
    }

    /**
     * function to return the middle point of the line.
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        Point result = new Point(x, y);
        return result;
    }

    /**
     * Returns the start point of the line.
     * @return start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     * @return end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * check if two lines are intersecting.
     * @param other the second line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // if there is no mutual point
        if (this.intersectionWith(other) == null) {
            return false;
        }
        // if there is mutual point
        return true;
    }

    /**
     * another method to invoke isIntersecting but with points and not line.
     * @param p1 the first point of the line
     * @param p2 the second point of the line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Point p1, Point p2) {
        return isIntersecting(new Line(p1, p2));
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     * @param other the second point
     * @return intersection point if exist, null otherwise
     */
    public Point intersectionWith(Line other) {
        CompareDoubles cmp = new CompareDoubles();
        // mutual (x,y) and a1,a2,b1,b2 of the formulas
        double xM, yM, a1, a2, b1, b2;
        // maximum and minimum of x values of points
        double thisMinX = Math.min(this.start.getX(), this.end.getX());
        double thisMaxX = Math.max(this.start.getX(), this.end.getX());
        double otherMinX = Math.min(other.start().getX(), other.end().getX());
        double otherMaxX = Math.max(other.start().getX(), other.end().getX());
        // maximum and minimum y values
        double thisMinY = Math.min(this.start.getY(), this.end.getY());
        double thisMaxY = Math.max(this.start.getY(), this.end.getY());
        double otherMinY = Math.min(other.start().getY(), other.end().getY());
        double otherMaxY = Math.max(other.start().getY(), other.end().getY());
        // check if segments not in range of each other
        if (cmp.smallThan(thisMaxX, otherMinX) || cmp.smallThan(otherMaxX, thisMinX)) {
            //there is no mutual point
            return null;
        }
        // if the first line is parallel to the y axis
        if (cmp.equals(this.start.getX(), this.end.getX())) {
            // if the second line is parallel to the y axis
            if (cmp.equals(other.start().getX(), other.end().getX())) {
                // if they have the same x, check for mutual point
                if (cmp.equals(thisMaxY, otherMinY)) {
                    return new Point(this.start.getX(), thisMaxY);
                } else if (cmp.equals(otherMaxY, thisMinY)) {
                    return new Point(other.start().getX(), otherMaxY);
                } else if (this.start.equals(this.end) && cmp.smallEqual(otherMinY, this.start.getY())
                        && cmp.smallEqual(this.start.getY(), otherMaxY)) {
                    // if the first line is a dot
                    return new Point(this.start.getX(), this.start.getY());
                } else if (other.start().equals(other.end())
                        && cmp.smallEqual(thisMinY, other.start().getY())
                        && cmp.smallEqual(other.start().getY(), thisMaxY)) {
                    //if the second line is a dot
                    return new Point(other.start().getX(), other.start().getY());
                } else {
                    // the case both lines parallel to y axis, but not connecting, or overlapping.
                    return null;
                }
            } else {
                // if the second line is not parallel to the y axis
                a2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
                b2 = other.start().getY() - (a2 * other.start().getX());
                yM = (a2 * this.start.getX()) + b2;
                // check if the point is in the first segment range
                if (cmp.smallEqual(thisMinY, yM) && cmp.smallEqual(yM, thisMaxY)) {
                    return new Point(this.start.getX(), yM);
                }
                return null;
            }
        } else if (cmp.equals(other.start().getX(), other.end().getX())) {
            // if only the second line is parallel to the y axis
            a1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            b1 = this.start.getY() - (a1 * this.start.getX());
            yM = (a1 * other.start().getX()) + b1;
            // check if the point is in the second segment range
            if (cmp.smallEqual(otherMinY, yM) && cmp.smallEqual(yM, otherMaxY)) {
                return new Point(other.start().getX(), yM);
            }
            return null;
        }
        // a1*x + b1 = y, a2*x + b2 = y - it's not possible to divide by zero, it was checked above
        a1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        a2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
        b1 = this.start.getY() - (a1 * this.start.getX());
        b2 = other.start().getY() - (a2 * other.start().getX());

        // if the segments are parallel, then a1 == a2;
        if (cmp.equals(a1, a2)) {
            // if the segments are parallel but not on the same line, there is no mutual point
            if (cmp.different(b1, b2)) {
                return null;
            }
            // check if segments connect only in one point
            if (cmp.equals(thisMaxX, otherMinX)) {
                // return the corresponding point
                if (cmp.equals(thisMaxX, this.start.getX())) {
                    return new Point(thisMaxX, this.start.getY());
                }
                return new Point(thisMaxX, this.end.getY());
            } else if (cmp.equals(otherMaxX, thisMinX)) {
                // return the corresponding point
                if (cmp.equals(otherMaxX, other.start().getX())) {
                    return new Point(otherMaxX, other.start().getY());
                }
                return new Point(otherMaxX, other.end().getY());
            }
            // if the lines are not connecting, or overlapping return null
            return null;
        }
        // calculate mutual x,y
        xM = (b2 - b1) / (a1 - a2);
        yM = (a1 * xM) + b1;
        // check if the point is included in the segments
        if (cmp.smallThan(xM, Math.max(thisMinX, otherMinX))
                || cmp.smallThan(Math.min(thisMaxX, otherMaxX), xM)) {
            return null;
        }
        return new Point(xM, yM);
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     * @param other the other line to compare to
     * @return true if the line are identical, false if not
     */
    public boolean equals(Line other) {
        if ((other.start().equals(this.start) && other.end().equals(this.end))
                || (other.start().equals(this.end) && other.end().equals(this.start))) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect the rectangle to check the intersection points with
     * @return the closest intersection point to the beginning of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        CompareDoubles cmp = new CompareDoubles();
        double firstPointDistance, secondPointDistance;
        List<Point> allIntersectionPoints = rect.intersectionPoints(new Line(this.start, this.end));
        if (allIntersectionPoints.isEmpty()) {
            // if there are no intersection points between the line and the rectangle
            return null;
        } else if (allIntersectionPoints.size() == 1) {
            // if there is only one intersection points
            return allIntersectionPoints.get(0);
        } else {
            // if there are two intersection points, return the closest one
            firstPointDistance = (allIntersectionPoints.get(0)).distance(this.start);
            secondPointDistance = (allIntersectionPoints.get(1)).distance(this.start);
            if (cmp.smallEqual(firstPointDistance, secondPointDistance)) {
                return allIntersectionPoints.get(0);
            }
            return allIntersectionPoints.get(1);
        }
    }
}