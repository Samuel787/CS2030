import java.util.*;

public class Lab0 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int numPoint = scanner.nextInt();
    Point[] points = new Point[numPoint];
    for(int i=0; i<numPoint; i++) {
      points[i] = new Point(scanner.nextDouble(), scanner.nextDouble());
    }

    int solution = 0;
    // TODO: Question 3

    System.out.println(solution);
  }
}