public class Server implements IServer{
    
    private CustQueue custQueue; //Queue of Customers
    private int serverID; //ID of the server
    private int numCustPerTime; //Maximum number of concurrent customers the server can serve
    private int numCustServing;
    
    // The next time the server is available to serve
    private double serverNext;

    private double currTime; //current time 

    private double probRest;
    private ServerRandomizer serverRandomizer;

    private boolean aboutToRest; //when rest is initiated, this will be flagged to true
    private boolean isResting; //while the server is resting, this will be flaggd to true

    private double restTime;

    public Server(double currTime, int serverID, int numCustPerTime, int maxQueueLength, double probRest, ServerRandomizer serverRandomizer){
        //create a customer queue for the server
        custQueue = new CustQueue(serverID, maxQueueLength);

        //setting the server ID
        this.serverID = serverID;

        //setting the specification of this server - Maximum number of customers it can serve
        this.numCustPerTime = numCustPerTime;

        //server just started
        this.numCustServing = 0;
        this.serverNext = 0;
        //giving server a clock
        this.currTime = currTime;

        this.probRest = probRest;
        this.serverRandomizer = serverRandomizer;
        this.aboutToRest = false;
        this.isResting = false;
    }

    public double getServerNext() {
		return this.serverNext;
	}

	public void setServerNext(double serverNext) {
		this.serverNext = serverNext;
	}

    /**
     * After the server returns from rest, it can only serve one customer per time
     * 
     * @param n is the number of customers that the server can serve
     */
    public void setNumCustPerTime(int n){
        this.numCustPerTime = n;
    }


    @Override
    public boolean isBusy() {
        if(numCustServing >= numCustPerTime) return true;
        return false;
    }

    @Override
    public int getCurrQueueLength() {
        return custQueue.size();
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
    public double getDoneTime() {
        return this.currTime + SERVICE_TIME;
    }

    @Override
    public ICustomer getFrontCustomer() {
        return this.custQueue.remove();
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
        }
    }

    @Override
    public boolean getIsResting(){
        return this.isResting;
    }

    public void stopResting(){
        this.isResting = false; // recovers the server from resting
        this.aboutToRest = false; // turns the abouttorest flag off also
    }

    public void startResting(){
        this.isResting = true;
        this.aboutToRest = false;
    }

    @Override
    public boolean getAboutToRest(){
        return this.aboutToRest;
    }

    public boolean checkAboutToRest(){
        if(!this.aboutToRest){
            double toRest = this.serverRandomizer.toRest();
            if(toRest <= this.probRest){
                this.aboutToRest = true;
            } else {
                this.aboutToRest = false;
            }
        }
        return this.aboutToRest;
    }

    public double getRestTime(){
        return this.restTime;
    }

    public void generateRestTime(){
        this.restTime = this.serverRandomizer.restTime();
    }

    public boolean isStillServing(){
        if(this.numCustServing > 0)
            return true;
        else
            return false;
    }

    public int getNumElderly(){
        return this.custQueue.getNumElderly();
    }
}