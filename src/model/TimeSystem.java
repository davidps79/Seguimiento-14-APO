package model;

import java.util.ArrayList;

public class TimeSystem {
	private ArrayList<Lap> laps;
	
	public TimeSystem() {
		this.laps = new ArrayList<Lap>();
	}
	
	public void addLap(int number, long duration, long total) {
		laps.add(new Lap(number, duration, total));
	}
	
	public ArrayList<Lap> getLaps() {
		return laps;
	}

	public void clearLaps() {
		laps.clear();
	}
}
