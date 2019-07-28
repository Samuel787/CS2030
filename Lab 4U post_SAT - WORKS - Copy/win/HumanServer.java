public class HumanServer extends Server implements IServer{
    
    /**
     * The attributes below are unique to HumanServer
     */
    private double probRest;
    private ServerRandomizer serverRandomizer;

    private boolean aboutToRest; //when rest is initiated, this will be flagged to true
    private boolean isResting; //while the server is resting, this will be flaggd to true

    private double restTime;

    public HumanServer(double currTime, int serverID, int numCustPerTime, int maxQueueLength, double probRest, ServerRandomizer serverRandomizer){
        super(currTime, serverID, numCustPerTime, maxQueueLength);
        
        this.probRest = probRest;
        this.serverRandomizer = serverRandomizer;
        this.aboutToRest = false;
        this.isResting = false;
    }
   
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

    /**
     * After the server returns from rest, it can only serve one customer per time
     * 
     * @param n is the number of customers that the server can serve
     */
    public void setNumCustPerTime(int n){
        this.numCustPerTime = n;
    }
    
    public int getNumCustPerTime(){
        return this.numCustPerTime;
    }

}