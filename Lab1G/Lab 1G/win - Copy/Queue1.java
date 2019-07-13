import java.util.*;
public class Queue1{
    private int maxLength;
    private ArrayList<Customer> custQueue = new ArrayList<>();
    public Queue1(int maxLength){
        this.maxLength = maxLength;
    }

    public void setQueueMaxLength(int maxLength){
        this.maxLength = maxLength;
    }

    public boolean isQueueFull(){
        if(this.getQueueLength() >= maxLength) return true;
        return false;
    }

    public boolean isQueueEmpty(){
        if(this.getQueueLength() == 0) return true;
        return false;
    }

    //@Override
    public boolean addToQueue(Customer c){
        if(this.isQueueFull()) return false;
        custQueue.add(c);
        return true;
    }

    public boolean removeFromQueue(Customer c){
        for(int i = 0; i < custQueue.size(); i++){
            if(custQueue.get(i).getCustID() == c.getCustID()){
                custQueue.remove(i);
                return true;
            }
        }
        return false;
    }

    //returns the current length of the queue
    public int getQueueLength(){
        return this.custQueue.size();
    }

    public Customer getNextCustomer(){
        return custQueue.get(0);
    }

}