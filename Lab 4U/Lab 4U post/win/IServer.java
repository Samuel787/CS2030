public interface IServer {
  public static final double SERVICE_TIME = 1.0;
  public static final double REST_TIME = 0.0; 

  public boolean isBusy();
  public boolean isQueueFull();
  public boolean isQueueEmpty();

  public int getID();
  public int getCurrQueueLength();
  public double getDoneTime();
  public ICustomer getFrontCustomer();
  public double getServerNext();
  public void setServerNext(double serverNext);

  public void addServing(double serveStartTime); //Server serves 1 more person
  public void removeServing(); //Server serves 1 less person

  public void addToQueue(ICustomer customer);

  public int getNumElderly();


  // doArrives has nothing to do with Server
  /*
  public void doWaits(ICustomer cust, double time);
  public void doServed(ICustomer cust, double time);
  // doLeaves has nothing to do with Server
  public void doDone(ICustomer cust, double time);
  */
}