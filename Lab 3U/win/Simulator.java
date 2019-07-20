import java.util.ArrayList;

public class Simulator{

    public static String simulate(
        /*
        int[] custIDs,

        int[] customerType,
        double[] customerReArrivalTime,
        
        double[] custArriveTime,
        */
        ArrayList<ICustomer> customers,
        int numArrival,
        int servMaxCust,
        int numServer,
        int servQueueSize){

        Shop shop = new Shop(/*custIDs, customerType, customerReArrivalTime, custArriveTime, */ customers, numArrival, servMaxCust, numServer, servQueueSize);
        
        return shop.open();
    }
}