
public class Simulator{

    public static String simulate(
        int[] custIDs,
        double[] custArriveTime,
        int numArrival,
        int servMaxCust,
        int numServer,
        int servQueueSize){

        Shop shop = new Shop(custIDs, custArriveTime, numArrival, servMaxCust, numServer, servQueueSize);
        return shop.open();
    }
}