package controller;

import java.time.LocalTime;

public class MainClock extends Thread {
	private MainViewController controller;
	
	public MainClock(MainViewController controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {
		try {
			sleep(1000);
			LocalTime time = LocalTime.now();
			controller.setMainClock(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
