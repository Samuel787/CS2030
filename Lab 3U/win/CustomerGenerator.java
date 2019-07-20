import java.util.Random;

public class CustomerGenerator{

    private Random typeRand; //RNG for choosing customer tyep
    private Random custRand; //RNG for customer attributes
    private double probEldery;
    private double arrivalRate;
    private double reArrivalRate;
    private double currTime;
    private int individualID;

    public CustomerGenerator(long seed, double probElderly, double arrivalRate, double reArrivalRate){
        typeRand = new Random(seed); 
        custRand = new Random(seed+1);

        this.probEldery = probElderly;
        this.arrivalRate = arrivalRate;
        this.reArrivalRate = reArrivalRate;

        this.individualID = 0;
        this.currTime = 0.0;
    }

    private double rng(Random rang, double rate){
        return -Math.log(rang.nextDouble())/rate;
    }

    public ICustomer nextCustomer(){
        individualID++;
        //first generate the customer type
        double custTypeProb = typeRand.nextDouble();  
        if(custTypeProb <= this.probEldery){
            //Generate elderly customer
            double eldTime = currTime + rng(this.custRand, this.arrivalRate);
            currTime = eldTime;
            return new ElderlyCustomer(individualID-1, eldTime, null);
            
        } else {
            //Generate general customer
            double arrivalTime = currTime +rng(this.custRand, this.arrivalRate);
            currTime = arrivalTime;
            double reArrivalTime = rng(this.custRand, this.reArrivalRate);
            return new GeneralCustomer(individualID-1, arrivalTime, reArrivalTime, null);
        }
    }
}