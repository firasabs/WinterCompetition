package utilities;
/**
 * The {@code Point} class represents a point in a 2D.
 * The x can be any number from 0 to 1000000,and the y
 * can be any number from 0 to 800.
 */
public class Point {
    public static final int MAX_X = 1000000;
    public static final int MIN_X = 0;
    public static final int MAX_Y = 800;
    public static final int MIN_Y = 0;

    private double x;
    private double y;
    /**
     * Constructs a new point with the specified coordinates.
     *
     *  x the x-coordinate of the point, which must be between 0 and 1,000,000.
     * y the y-coordinate of the point, which must be between 0 and 800.
     * @throws IllegalArgumentException if x or y are out of the specified ranges.
     */
    public Point() {
    this(0,0);
    }
    public Point(double x, double y) {
        setX(x);
        setY(y);
    }
    /**
     * Copy Ctor
     * @param other other instance
     * @throws IllegalArgumentException if other instance is null
     */
    public Point(Point other) throws IllegalArgumentException {
        ValidationUtils.assertNotNull(other);
        this.setX(other.x);
        this.setY(other.y);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    // Setters

    /**
     * @param x the x position of the point
     * @throws IllegalArgumentException if x is out of range [{@value MIN_X},{@value MAX_X}]
     */
    private void setX(double x) throws IllegalArgumentException {
        ValidationUtils.assertInRange(x,MIN_X,MAX_X);
        this.x = x;
    }
    /**
     * @param y the y position of the point
     * @throws IllegalArgumentException if y is out of range [{@value MIN_Y},{@value MAX_Y}]
     */
    private void setY(double y) throws IllegalArgumentException {
        ValidationUtils.assertInRange(y,MIN_Y,MAX_Y);
        this.y = y;
    }
    /**
     * returns a string representation of the point.
     *
     * @return a string representation of the point.
     */
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
    /**
     * offset from the current point
     * @param xOffset offset at the x axis
     * @param yOffset offset at the y axis
     * @return a new clone of the current point with an x and y offset
     */
    public Point offset(double xOffset, double yOffset){
        return new Point(this.x+xOffset, this.y+yOffset);
    }
}


