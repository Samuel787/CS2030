import java.util.*;

public class Main {

  
  public static void main(String[] args) {
    // INPUT
    Scanner scanner = new Scanner(System.in);

    /*
    int individualID = 0;
    int[] customersID = new int[1101];
    int[] customerType = new int[1101];

    double[] customerArrival = new double[1101];
    double[] customerReArrival = new double[1101];*/

    //read in the total number of customers
    int numArrival = scanner.nextInt();

    int servMaxCust = scanner.nextInt();
    int maxServer = scanner.nextInt();
    int servQueueSize = scanner.nextInt();
    
    int randomSeed = scanner.nextInt();
    double probElderly = scanner.nextDouble();
    double arrivalRate = scanner.nextDouble();
    double reArriveRate = scanner.nextDouble();

    //Generate all the customers
    ArrayList<ICustomer> customers = new ArrayList<>();
    CustomerGenerator customerGenerator = new CustomerGenerator(randomSeed, probElderly, arrivalRate, reArriveRate);
    for(int i=0; i<numArrival; i++){
      customers.add(customerGenerator.nextCustomer());
    }
    /*
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
        customerReArrival[individualID] = rand.nextDouble(); //general customers will come back
      }

      individualID++;
    }
    */
    // PROCESS
    String stats = Simulator.simulate(/*customersID, customerType, customerReArrival, customerArrival,*/ customers, numArrival, servMaxCust, maxServer, servQueueSize);
 
    // OUTPUT
    System.out.println(stats);
    scanner.close();
  }
}