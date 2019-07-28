public class MachineServer extends Server implements IServer{
    public MachineServer(double currTime, int serverID, int numCustPerTime, int maxQueueLength){
        super(currTime, serverID, numCustPerTime, maxQueueLength);
    }
}