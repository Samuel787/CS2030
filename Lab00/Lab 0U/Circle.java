import java.lang.*;

public class Circle  {
  /**
   * Fields
   */
  private Point center;
  private double r;
  
  /**
   * Create a circle with radius r at (0,0)
   * @param r The radius
   */
  public Circle(double r) {
    this.r = r;
    this.center = new Point(0,0);
  }
  /**
   * Create a circle with radius r at the given point as center
   * @param r The radius
   * @param center The center
   */
  public Circle(double r, Point center) {
    this.r = r;
    this.center = center;
  }
  /**
   * Create a circle with radius r given two points p and q in its perimeter
   * @param p
   * @param q
   * @param r
   */
  public Circle(Point p, Point q, double r) {
    // TODO: Question 2.1
  }

  /**
   * Get the center of the point
   * @return The center of the point
   */
  public Point getCenter() {
    return this.center;
  }
  
  /**
   * Compute the area of circle
   * @return The area of circle
   */
  public double area() {
    return Math.PI * r * r;
  }
  /**
   * Compute the perimeter of circle
   * @return The perimeter of circle
   */
  public double perimeter() {
    return 2 * Math.PI * r;
  }
  
  /**
   * toString method
   * @return String representation of Center
   */
  @Override
  public String toString() {
    return center.toString() + ": " + String.format("%.3f", this.r);
  }
  

  /**
   * Checking if the circle is valid
   * @return True if the circle is valid, otherwise false
   */
  public boolean isValid() {
    // TODO: Question 2.2.
    return false;
  }
}