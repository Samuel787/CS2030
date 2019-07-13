public class Log{
    private int logServedCust;
    private int logTotalCust;
    private double logTotalWait;

	public int getLogServedCust() {
		return this.logServedCust;
	}

	public void setLogServedCust(int logServedCust) {
		this.logServedCust = logServedCust;
	}
    
	public int getLogTotalCust() {
		return this.logTotalCust;
	}

	public void setLogTotalCust(int logTotalCust) {
		this.logTotalCust = logTotalCust;
	}

	public double getLogTotalWait() {
		return this.logTotalWait;
	}

	public void setLogTotalWait(double logTotalWait) {
		this.logTotalWait = logTotalWait;
	}

    public Log(int logServedCust, int logTotalCust, double logTotalWait){
        this.logServedCust = logServedCust;
        this.logTotalCust = logTotalCust;
        this.logTotalWait = logTotalWait;
    }

    public void incrementTotalCust(int x){
        this.logTotalCust += x;
    }
    public void incrementServedCust(int x){
        this.logServedCust += x;
    }

    public void addToTotalWait(double wait){
        this.logTotalWait += wait;
    }
}