package model;

import java.time.LocalTime;

public class Lap {
	private int number;
	private LocalTime duration;
	private LocalTime total;
	
	public Lap(int number, LocalTime duration, LocalTime total) {
		this.number = number;
		this.duration = duration;
		this.total = total;
	}
}
