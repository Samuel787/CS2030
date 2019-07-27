public class Simulator {
  public static String simulate(Shop shop, IQueue<ICustomer> incoming) {
    while(shop.hasNextEvent() || !incoming.isEmpty()) {
      if(!shop.hasNextEvent()) {
        // Take from Incoming
        ICustomer customer = incoming.get();
        shop.run(new EventArrive(customer.getArrivalTime(), null, customer));
      } else if(incoming.isEmpty()) {
        // Take from Shop
        shop.run();
      } else if(shop.getNextTime() < incoming.peek().getArrivalTime()) {
        // Take from Shop
        shop.run();
      } else {
        // Take from Incoming
        ICustomer customer = incoming.get();
        shop.run(new EventArrive(customer.getArrivalTime(), null, customer));
      }
    }

    return shop.getStatistics();
  }
}