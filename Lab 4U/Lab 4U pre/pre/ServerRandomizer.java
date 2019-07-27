import java.util.*;

public class ServerRandomizer {
  /* Attributes */
  private Random serverRand;
  private double restRate;
  private double probRest;

  
  /* Constructor */
  public ServerRandomizer(long seed, double probRest, double restRate) {
    serverRand = new Random(seed + 2);
    this.probRest = probRest;
    this.restRate = restRate;
  }

  
  /* Generator */
  public boolean toRest() {
    return this.serverRand.nextDouble() <= this.probRest;
  }

  public double restTime() {
    return Util.rng(this.serverRand, this.restRate);
  }
}