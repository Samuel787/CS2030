import java.util.*;

public class Main {

  public static void main(String[] args) {
    // INPUT
    Scanner scanner = new Scanner(System.in);

    //read in the total number of customers
    int numArrival = scanner.nextInt();


    //reading in second line of input
    int servMaxCust = scanner.nextInt();
    int numHumanServer = scanner.nextInt();
    int servQueueSize = scanner.nextInt();
    int numMachineServer = scanner.nextInt();
    
    //reading in the third line of input
    int randomSeed = scanner.nextInt();
    double probElderly = scanner.nextDouble();
    double arrivalRate = scanner.nextDouble();
    double reArriveRate = scanner.nextDouble();
    double probRest = scanner.nextDouble();
    double restRate = scanner.nextDouble();

    //Generate all the customers
    ArrayList<ICustomer> customers = new ArrayList<>();
    CustomerGenerator customerGenerator = new CustomerGenerator(randomSeed, probElderly, arrivalRate, reArriveRate);
    for(int i=0; i<numArrival; i++){
      customers.add(customerGenerator.nextCustomer());
    }

    // PROCESS
    String stats = Simulator.simulate(customers, numArrival, servMaxCust, numHumanServer, numMachineServer, servQueueSize, probRest, restRate, randomSeed);
 
    // OUTPUT
    System.out.println(stats);
    scanner.close();
  }
}