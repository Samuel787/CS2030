import java.util.*;

public class Main {

  public static void main(String[] args) {
    // INPUT
    Scanner scanner = new Scanner(System.in);
    int individualID = 0;
    int[] customersID = new int[Simulator.MAX_EVENTS+1];
    double[] customerArrival = new double[Simulator.MAX_EVENTS+1];
    
    while(scanner.hasNextDouble()) {
      customersID[individualID] = individualID;
      customerArrival[individualID] = scanner.nextDouble();
      individualID++;
    }

    //remember to close the scanner
    scanner.close();

    // PROCESS
    String stats = Simulator.simulate(customersID, customerArrival, individualID);

    // OUTPUT
    System.out.println(stats);
  }
}