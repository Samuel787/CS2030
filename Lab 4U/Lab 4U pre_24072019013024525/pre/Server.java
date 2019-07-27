import java.util.*;
import java.util.function.*;

/* Basic Server: simply serve customer */
public class Server implements IServer {
  /* Attributes */
  private final int id;    // final to prevent changes
  private int maxCustomer; // maximum number of customers
  private int ctxCustomer; // counter for customer number
  private double duration; // rest duration

  private boolean toRest;  // FLAG: server is about to rest
  private boolean resting; // FLAG: server is resting

  private IQueue<ICustomer> waitingQueue; // customer waiting queue
  private ServerRandomizer randomizer;



  /* Constructor */
  public Server(int id, int maxCustomer, ServerRandomizer randomizer) {
    assert id >= 0;
    assert maxCustomer >= 1;
    assert randomizer != null;

    this.id = id;
    this.maxCustomer = maxCustomer;
    this.ctxCustomer = 0;
    this.duration = 0.0;

    this.toRest  = false;
    this.resting = false;

    this.waitingQueue = new SQueue<ICustomer>(maxCustomer);
    this.randomizer = randomizer;
  }



  /* Accessors */
  @Override public int getID() { 
    return this.id; 
  }
  @Override public double getRestTime() {
    return this.duration;
  }
  @Override public ICustomer getFrontCustomer() {
    assert !this.waitingQueue.isEmpty();
    return this.waitingQueue.get();
  }
  public int countQueue(Predicate<ICustomer> check) {
    return this.waitingQueue.count(check);
  }



  /* Predicates */
  @Override public boolean isBusy() {
    assert Util.range(this.ctxCustomer, 0, this.maxCustomer);
    return this.ctxCustomer == this.maxCustomer; 
  }
  @Override public boolean isResting() { 
    return this.resting; 
  }
  @Override public boolean isQueueEmpty() {
    return this.waitingQueue.isEmpty();
  }
  @Override public boolean isQueueFull() {
    return this.waitingQueue.isFull();
  }
  @Override public Optional<IServer> isServing() {
    return this.ctxCustomer == 0 ? Optional.of(this) : Optional.empty();
  }
  @Override public Optional<IServer> canServe() {
    return (!this.isBusy() && !this.toRest && !this.isResting()) ? Optional.of(this) : Optional.empty();
  }
  @Override public Optional<IServer> canServeNext() {
    return !this.isQueueEmpty() ? Optional.of(this) : Optional.empty();
  }



  /* Mutators */
  @Override public void setRestTime() {
    this.duration = this.randomizer.restTime();
  }



  /* Event Handler */
  @Override public Optional<IServer> serve(ICustomer customer, double time) {
    customer.serve(time);
    this.ctxCustomer++;
    return Optional.empty();
  }
  @Override public Optional<IServer> wait(ICustomer customer, double time) {
    customer.wait(time);
    this.waitingQueue.add(customer);
    return Optional.empty();
  }
  @Override public Optional<IServer> done(ICustomer customer, double time) {
    customer.done(time);
    this.ctxCustomer--;
    
    // Resting
    if(this.toRest || this.randomizer.toRest()) {
      this.toRest = true;
      return Optional.of(this);
    } else {
      return Optional.empty();
    }
  }
  // @Override public double arrive(ICustomer customer, double time); // nothing
  // @Override public double leave(ICustomer customer, double time);  // nothing
  @Override public Optional<IServer> rest(ICustomer customer, double time) {
    assert !this.resting;
    assert this.ctxCustomer == 0;
    this.resting = true;
    this.toRest = false;
    return Optional.empty();
  }
  @Override public Optional<IServer> back(ICustomer customer, double time) {
    assert !this.toRest;
    assert this.ctxCustomer == 0;
    this.resting = false;
    this.maxCustomer = 1;
    return Optional.empty();
  }



  /* IUnique */
  @Override public boolean diff(IServer server) {
    return this.getID() != server.getID();
  }



  /* Comparable */
  @Override public int compareTo(IServer server) {
    return this.getID() - server.getID();
  }
}