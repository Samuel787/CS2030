import java.util.*;
import java.lang.Math.*;

public class Point {
	private double x;
	private double y;

	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}

	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}

	//returns the mid point
	public static Point midpoint(Point p, Point q){
		double x = (p.getX() + q.getX())/2;
		double y = (p.getY() + q.getY())/2;
		return new Point(x, y);
	}

	//gets the angle of point q relative to this point
	public double angle(Point q){
		double newX = q.getX() - this.x;
		double newY = q.getY() - this.y;
		return Math.atan2(newX, newY);
	}

	public void move(double theta, double d){
		this.x = this.x + d*Math.cos(theta);
		this.y = this.y + d*Math.sin(theta);
	}
}