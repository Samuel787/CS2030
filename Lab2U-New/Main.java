import java.util.*;

public class Main {

  public static void main(String[] args) {
    // INPUT
    Scanner scanner = new Scanner(System.in);
    int individualID = 0;
    int[] customersID = new int[1101];
    double[] customerArrival = new double[1101];

    int servMaxCust = scanner.nextInt();
    int maxServer = scanner.nextInt();
    int servQueueSize = scanner.nextInt();
   
    while(scanner.hasNextDouble()) {
      customersID[individualID] = individualID;
      customerArrival[individualID] = scanner.nextDouble();
      individualID++;
    }

    // PROCESS
    String stats = Simulator.simulate(customersID, customerArrival, individualID, servMaxCust, maxServer, servQueueSize);
 
    // OUTPUT
    System.out.println(stats);
    scanner.close();
  }
}