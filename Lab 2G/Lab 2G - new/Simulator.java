
public class Simulator{

    public static String simulate(
        int[] custIDs,

        int[] customerType,
        double[] customerReArrivalTime,
        
        double[] custArriveTime,
        int numArrival,
        int servMaxCust,
        int numServer,
        int servQueueSize){

        Shop shop = new Shop(custIDs, customerType, customerReArrivalTime, custArriveTime, numArrival, servMaxCust, numServer, servQueueSize);
        return shop.open();
    }
}