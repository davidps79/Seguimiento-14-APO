package model;

public class Lap {
	private int number;
	private long duration;
	private long total;
	
	public Lap(int number, long duration, long total) {
		this.number = number;
		this.duration = duration;
		this.total = total;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
