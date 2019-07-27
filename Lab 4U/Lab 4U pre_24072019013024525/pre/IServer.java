import java.util.*;
import java.util.function.*;

public interface IServer extends IUnique<IServer>, Comparable<IServer> {
  /* Constants */
  public static final double SERVE_TIME = 1.0;
  /* Accessors */
  public int getID();
  public double getRestTime();
  public ICustomer getFrontCustomer();
  public int countQueue(Predicate<ICustomer> check);

  /* Predicates */
  public boolean isBusy();
  public boolean isResting();
  public boolean isQueueEmpty();
  public boolean isQueueFull();
  public Optional<IServer> isServing();
  public Optional<IServer> canServe();
  public Optional<IServer> canServeNext();


  /* Mutators */
  public void setRestTime();


  /* Event Handler */
  public Optional<IServer> serve(ICustomer customer, double time);
  public Optional<IServer> wait(ICustomer customer, double time);
  public Optional<IServer> done(ICustomer customer, double time);
  // public double arrive(ICustomer customer, double time); // nothing
  // public double leave(ICustomer customer, double time);  // nothing
  public Optional<IServer> rest(ICustomer customer, double time);
  public Optional<IServer> back(ICustomer customer, double time);
}