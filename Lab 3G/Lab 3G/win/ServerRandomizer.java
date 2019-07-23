import java.util.Random;

public class ServerRandomizer{
    
    private double restRate;
    private Random serverRand;
    
    public ServerRandomizer(long seed, double restRate){
        this.restRate = restRate;
        serverRand = new Random(seed + 2);
    }

    private double rng(Random rand, double rate) { 
        return -Math.log(rand.nextDouble())/rate; 
     }

    public double toRest(){
        return serverRand.nextDouble();
    }

    public double restTime(){
        return rng(serverRand, restRate);
    }
}