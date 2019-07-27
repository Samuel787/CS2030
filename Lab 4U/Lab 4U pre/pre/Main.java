import java.util.*;

public class Main {
  /* Globals */
  private static IQueue<ICustomer> incoming;
  private static CustomerGenerator gen;
  private static ServerRandomizer rand;
  private static Shop shop;

  public static void init(Scanner scanner) {
    // First Line
    int numOfCustomers = scanner.nextInt();

    // Second Line
    int maxCustomerServed = scanner.nextInt();
    int numOfServers = scanner.nextInt();
    int serverQueueSize = scanner.nextInt();

    // Third Line
    long seed = scanner.nextLong();
    double probOfElderly = scanner.nextDouble();
    double arrivalRate = scanner.nextDouble();
    double rearrivalRate = scanner.nextDouble();
    double probOfRest = scanner.nextDouble();
    double restingRate = scanner.nextDouble();
    
    incoming = new SQueue<ICustomer>(numOfCustomers);
    gen = new CustomerGenerator(seed, probOfElderly, arrivalRate, rearrivalRate);
    rand = new ServerRandomizer(seed, probOfRest, restingRate);
    shop = new Shop(numOfServers, maxCustomerServed, serverQueueSize, rand);

    // Initialize Customers
    for(int i=0; i<numOfCustomers; i++) {
      incoming.add(gen.nextCustomer());
    }
  }

  public static void main(String[] args) {
    try(Scanner scanner = new Scanner(System.in)) {
      Main.init(scanner); // best to pass scanner here
    } catch(Exception e) {
      assert false; // should never come here
    }

    String stats = Simulator.simulate(shop, incoming);

    System.out.println(stats);
  }
}