public class Queue1 extends Queue{
    private int maxLength;
    
    public Queue1(int maxLength){
        this.maxLength = maxLength;
    }

    public void setQueueMaxLength(int maxLength){
        this.maxLength = maxLength;
    }

    public boolean isQueueFull(){
        if(super.getQueueLength() >= maxLength) return true;
        return false;
    }

    @Override
    public boolean addToQueue(Customer c){
        if(this.isQueueFull()) return false;
        super.addToQueue(c);
        return true;
    }
}