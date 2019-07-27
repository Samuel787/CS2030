import java.util.*;

public interface ICustomer extends IUnique<ICustomer>, Comparable<ICustomer> {
  /* Accessors */
  public int getID();
  public double getArrivalTime();
  public double getWaitTime();


  /* Event Handler */
  public double serve(double time);
  public double wait(double time);
  public double done(double time);
  public double arrive(double time);
  public double leave(double time);
  // public double rest(double time); // nothing
  // public double back(double time); // nothing

  
  /* Strategy */
  public Optional<IServer> chooseServe(IServer[] server);
  public Optional<IServer> chooseWaits(IServer[] server);
  public Optional<ICustomer> leaving();
}