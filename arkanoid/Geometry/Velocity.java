// ID: 319024600 IDO AHARON
package arkanoid.Geometry;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx difference in the x axis
     * @param dy difference in the y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return dx of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return dy of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     * @param p the points to change
     * @return the new point generated
     */
    public Point applyToPoint(Point p) {
        // create the point
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * calculate the dx and dy by angle and speed and return the resulted velocity.
     * @param angle the angle of the movement
     * @param speed the speed of the movement(how many pixels to move)
     * @return the resulted velocity with the calculated dx, dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx, dy, radiansAngle;
        // if the speed is negative, send it back with the opposite direction and positive speed
        if (speed < 0) {
            return fromAngleAndSpeed(angle + 180, -speed);
        }
        // calculate dx, dy with angle and speed
        if (angle < 0) {
            // negative angle- move one cycle forward
            return fromAngleAndSpeed(angle + 360, speed);
        } else if (angle == 0) {
            // go upwards
            return new Velocity(0, -speed);
        } else if (angle < 90) {
            // convert angle to radians
            radiansAngle = Math.toRadians(angle);
            dx = speed * Math.sin(radiansAngle);
            dy = speed * Math.cos(radiansAngle);
            return new Velocity(dx, -dy);
        } else if (angle == 90) {
            // go right
            return new Velocity(speed, 0);
        } else if (angle < 180) {
            radiansAngle = Math.toRadians(180 - angle);
            dx = speed * Math.sin(radiansAngle);
            dy = speed * Math.cos(radiansAngle);
            return new Velocity(dx, dy);
        } else if (angle == 180) {
            // go down
            return new Velocity(0, speed);
        } else if (angle < 270) {
            radiansAngle = Math.toRadians(270 - angle);
            dy = speed * Math.sin(radiansAngle);
            dx = speed * Math.cos(radiansAngle);
            return new Velocity(-dx, dy);
        } else if (angle == 270) {
            // go left
            return new Velocity(-speed, 0);
        } else if (angle < 360) {
            radiansAngle = Math.toRadians(360 - angle);
            dx = speed * Math.sin(radiansAngle);
            dy = speed * Math.cos(radiansAngle);
            return new Velocity(-dx, -dy);
        } else {
            // 360 degrees and above- subtract one cycle
            return fromAngleAndSpeed(angle - 360, speed);
        }
    }

    /**
     * change the dx to a new one.
     * @param newDx the new dx
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * change the dy to a new one.
     * @param newDy the new dy
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }
}