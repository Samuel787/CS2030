import java.util.*;

public class Main {

  public static void main(String[] args) {
    // INPUT
    Scanner scanner = new Scanner(System.in);
    int individualID = 0;
    int[] customersID = new int[1101];
    int[] customerType = new int[1101];

    double[] customerArrival = new double[1101];
    double[] customerReArrival = new double[1101];

    int servMaxCust = scanner.nextInt();
    int maxServer = scanner.nextInt();
    int servQueueSize = scanner.nextInt();
   
    while(scanner.hasNextDouble()) {
      customersID[individualID] = individualID;
      //get the customer type
      int custType = scanner.nextInt();
      customerType[individualID] = custType;
      
      //get the customer arrival time
      double arrivalTime = scanner.nextDouble();
      customerArrival[individualID] = arrivalTime;

      //get the customer reCust Time if GeneralCustomer
      if(custType == 1){
        customerReArrival[individualID] = -1; //elderly won't come back
      } else if(custType == 2){
        customerReArrival[individualID] = scanner.nextDouble(); //general customers will come back
      }

      individualID++;
    }

    // PROCESS
    String stats = Simulator.simulate(customersID, customerType, customerReArrival, customerArrival, individualID, servMaxCust, maxServer, servQueueSize);
 
    // OUTPUT
    System.out.println(stats);
    scanner.close();
  }
}