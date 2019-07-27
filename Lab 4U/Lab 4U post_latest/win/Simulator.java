import java.util.ArrayList;

public class Simulator{

    public static String simulate(
        ArrayList<ICustomer> customers,
        int numArrival,
        int servMaxCust,
        int numHumanServer,
        int numMachineServer,
        int servQueueSize,
        double probRest,
        double restRate, 
        int randomSeed){

        Shop shop = new Shop(customers, numArrival, servMaxCust, numHumanServer, numMachineServer, servQueueSize, probRest, restRate, randomSeed);
        
        return shop.open();
    }
}