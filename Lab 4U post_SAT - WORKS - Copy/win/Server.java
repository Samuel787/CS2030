public abstract class Server implements IServer{
    private CustQueue custQueue; //Queue of Customers
    private int serverID; //ID of the server
    protected int numCustPerTime; //Maximum number of concurrent customers the server can serve
    protected int numCustServing;
    
    // The next time the server is available to serve
    private double serverNext;

    private double currTime; //current time 

    public Server(double currTime, int serverID, int numCustPerTime, int maxQueueLength){
        custQueue = new CustQueue(serverID, maxQueueLength);
        this.serverID = serverID;
        this.numCustPerTime = numCustPerTime;
        this.numCustServing = 0;
        this.serverNext = 0;
        this.currTime = currTime;
    }

    @Override
    public boolean isBusy() {
        if(numCustServing >= numCustPerTime) return true;
        return false;
    }
    @Override
    public boolean isQueueFull() {
        return this.custQueue.isFull();
    }
    @Override
    public boolean isQueueEmpty() {
        return this.custQueue.isEmpty();
    }
    @Override
    public int getID() {
        return this.serverID;
    }

    @Override
    public int getCurrQueueLength() {
        return custQueue.size();
    }
    @Override
    public double getDoneTime() {
        return this.currTime + SERVICE_TIME;
    }
    @Override
    public ICustomer getFrontCustomer() {
        return this.custQueue.remove();
    }
    @Override
    public double getServerNext() {
		return this.serverNext;
    }
    @Override
    public void setServerNext(double serverNext) {
		this.serverNext = serverNext;
    }
    @Override
    public void addServing(double serveStartTime){
        numCustServing++;
        //check if the server is now fully occupied
        if(this.isBusy()) serverNext += SERVICE_TIME;
        else serverNext = serveStartTime; //still can accomodate more people
    }
    @Override
    public void removeServing(){
        numCustServing--;
    }
    @Override
    public void addToQueue(ICustomer customer){
        if(!this.custQueue.isFull()){
            this.custQueue.insert(customer);
        } else {
            System.out.println("Customer added to full queue, ERROR!");
        }
    }
    @Override
    public int getNumElderly(){
        return this.custQueue.getNumElderly();
    }


}