import java.util.*;

public class CustomerGenerator {
  /* Attributes */
  private Random typeRand;
  private Random custRand;

  private double probOfElderly;
  private double arrivalRate;
  private double rearrivalRate;
  private double currentTime;

  private int custID;


  /* Constructor */
  public CustomerGenerator(long seed, double probOfElderly, double arrivalRate, double rearrivalRate) {
    this.probOfElderly = probOfElderly;
    this.arrivalRate = arrivalRate;
    this.rearrivalRate = rearrivalRate;

    this.typeRand = new Random(seed);
    this.custRand = new Random(seed + 1);

    this.custID = 0;
    this.currentTime = 0.0;
  }


  /* Generator */
  public ICustomer nextCustomer() {
    ICustomer customer;
    this.currentTime += Util.rng(this.custRand, this.arrivalRate);

    if(this.typeRand.nextDouble() <= this.probOfElderly) {
      customer = new ElderlyCustomer(this.custID, this.currentTime);
    } else {
      customer = new GeneralCustomer(this.custID, this.currentTime, Util.rng(this.custRand, this.rearrivalRate));
    }
    
    this.custID++;
    return customer;
  }
}