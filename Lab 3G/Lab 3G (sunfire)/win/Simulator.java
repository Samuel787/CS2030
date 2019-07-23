import java.util.ArrayList;

public class Simulator{

    public static String simulate(
        ArrayList<ICustomer> customers,
        int numArrival,
        int servMaxCust,
        int numServer,
        int servQueueSize,
        double probRest,
        double restRate, 
        int randomSeed){

        Shop shop = new Shop(customers, numArrival, servMaxCust, numServer, servQueueSize, probRest, restRate, randomSeed);
        
        return shop.open();
    }
}