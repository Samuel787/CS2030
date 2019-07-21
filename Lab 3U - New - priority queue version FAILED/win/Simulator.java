import java.util.ArrayList;

public class Simulator{

    public static String simulate(
        ArrayList<ICustomer> customers,
        int numArrival,
        int servMaxCust,
        int numServer,
        int servQueueSize){

        Shop shop = new Shop(customers, numArrival, servMaxCust, numServer, servQueueSize);
        
        return shop.open();
    }
}