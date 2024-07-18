package utilities;
/**
 * The {@code Point} class represents a point in a 2D.
 * The x can be any number from 0 to 1000000,and the y
 * can be any number from 0 to 800.
 */
public class Point {
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
        setX(0);
        setY(0);
    }
    public Point(double x, double y) {
        setX(x);
        setY(y);
    }
    public Point(Point other) {
            setY(other.getY());
            setX(other.getX());
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) throws IllegalArgumentException {
        if(0<=x && x<1000000){
            this.x = x;
        }else throw new IllegalArgumentException("x must be between 0 and 1000000");
    }
    public void setY(double y)throws IllegalArgumentException {
        if(0<=y && y<800){
            this.y = y;
        }else throw new IllegalArgumentException("y must be between 0 and 800");
    }
    /**
     * returns a string representation of the point.
     *
     * @return a string representation of the point.
     */
    public String toString (){
        return " (" + x + "," + y + ")";
    }
    /**
     * returns a boolean status of comparing to points.
     *
     * @return boolean expression if two points are equal.
     */
    public boolean equals(Point other) {
        boolean ans = false;
        if (other != null){
            ans = (x==other.x && y==other.y);
        }
        return ans;
    }
}


