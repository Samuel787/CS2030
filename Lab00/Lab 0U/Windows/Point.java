import java.lang.*;
import java.Lang.Math.*;
public class Point {
  /**
   * Fields
   */
  private double x;
  private double y;

  /**
   * Create a point with coordinate (x,y)
   * @param x The x-coordinate
   * @param y The y-coordinate
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get the x-coordinate
   * @return The x-coordinate
   */
  public double getX() {
    return this.x;
  }
  /**
   * Get the y-coordinate
   * @return The y-coordinate
   */
  public double getY() {
    return this.y;
  }

  /**
   * Compute the Cartesian distance between this point and the other point
   * @param otherPoint The other point
   * @return The distance between this point and the other point
   */
  public double distance(Point otherPoint) {
    double distX = this.x - otherPoint.getX();
    double distY = this.y - otherPoint.getY();
    return Math.sqrt(distX * distX + distY * distY);
  }

  /**
   * @return String representation of Point
   */
  @Override
  public String toString() {
    return "(" + String.format("%.3f", this.getX()) + "," + 
                 String.format("%.3f", this.getY()) + ")";
  }
  

  /**
   * Given two points p and q, create and return the midpoint of p and q
   * @param p A given point
   * @param q A given point
   * @return The midpoint
   */
  public static Point midpoint(Point p, Point q) {
    // TODO: Question 1.1
    double x = (p.getX() + q.getX())/2;
    double y = (p.getY() + q.getY())/2;
    return new Point(x, y);
  }
  /**
   * Given another point otherPoint, compute and return the angle between the 
   * current (this) point and point otherPoint
   * @param otherPoint The other point
   * @return The angle between this point and the other point
   */
  public double angle(Point otherPoint) {
    // TODO: Question 1.2
    double newX = q.getX() - this.x;
    double newY = q.getY() - this.y;
    return Math.atan2(newX, newY);
  }
  /**
   * Given an angle theta (in radian) and a distance d, we should move the point
   * by the given distance at the direction of the given angle
   * @param theta The given angle
   * @param d The given distance
   */
  public void move(double theta, double d) {
    // TODO: Question 1.3
    this.x = this.x + d*Math.cos(theta);
    this.y = this.y + d*Math.sin(theta);
    return;
  }
}