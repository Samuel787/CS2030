import java.util.*;

public abstract class Customer implements ICustomer {
  /* Attributes */
  private final int id;
  private double arriveTime;
  private double servedTime;

  /* Constructor */
  public Customer(int id, double arriveTime) {
    assert id >= 0;
    assert arriveTime >= 0;
    
    this.id = id;
    this.arriveTime = arriveTime;
    this.servedTime = 0.0;

    assert this.id == id;
    assert this.arriveTime == arriveTime;
    assert this.servedTime == 0.0;
  }

  

  /* Accessors */
  @Override public int getID() {
    assert this.id >= 0;
    return this.id;
  }
  @Override public double getArrivalTime() {
    assert this.arriveTime >= 0;
    return this.arriveTime;
  }
  @Override public double getWaitTime() {
    assert this.servedTime == 0.0 || this.servedTime >= this.arriveTime;
    return this.servedTime - this.arriveTime;
  }



  /* Strategies */
  @Override public Optional<IServer> chooseServe(IServer[] allServers) {
    for(int i=0; i<allServers.length; i++) {
      if(allServers[i].canServe().isPresent()) {
        return Optional.of(allServers[i]);
      }
    }
    return Optional.empty();
  }
  @Override public Optional<IServer> chooseWaits(IServer[] allServers) {
    for(int i=0; i<allServers.length; i++) {
      if(!allServers[i].isQueueFull()) {
        return Optional.of(allServers[i]);
      }
    }
    return Optional.empty();
  }
  @Override public Optional<ICustomer> leaving() {
    return Optional.empty();
  }



  /* Event Handler */
  public double serve(double time) {
    assert time >= 0 && time >= this.servedTime;
    assert this.servedTime == 0.0;
    this.servedTime = time;
    assert this.servedTime >= 0 && this.servedTime == time;
    return time;
  }
  public double wait(double time) {
    assert time >= 0 && time == this.arriveTime;
    assert this.servedTime == 0.0;
    return time;
  }
  public double done(double time) {
    assert time >= 0 && time == this.servedTime + IServer.SERVE_TIME;
    assert this.servedTime >= this.arriveTime;
    return time;
  }
  public double arrive(double time) {
    assert time >= 0 && time == this.arriveTime;
    assert this.servedTime == 0.0;
    return time;
  }
  public double leave(double time) {
    assert time >= 0 && time == this.arriveTime;
    assert this.servedTime == 0.0;
    return time;
  }
  // public double rest(double time); // nothing
  // public double back(double time); // nothing



  /* IUnique */
  @Override public boolean diff(ICustomer customer) {
    assert customer != null;
    return this.getID() != customer.getID();
  }



  /* Comparable */
  @Override public int compareTo(ICustomer customer) {
    assert customer != null;
    return this.getID() - customer.getID();
  }
}