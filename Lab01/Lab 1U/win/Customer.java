public class Customer{
    private int custID;
    private double custArrivalTime;
    private double custServedTime;

	public Customer(int custID, double custArrivalTime, double custServedTime){
		this.custID = custID;
		this.custArrivalTime = custArrivalTime;
		this.custServedTime = custServedTime;
	}
	public double getCustServedTime() {
		return this.custServedTime;
	}
	
	public void setCustServedTime(double custServedTime) {
		this.custServedTime = custServedTime;
	}

	public int getCustID() {
		return this.custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public double getCustArrivalTime() {
		return this.custArrivalTime;
	}

	public void setCustArrivalTime(double custArrivalTime) {
		this.custArrivalTime = custArrivalTime;
	}



}
