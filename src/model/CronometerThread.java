package model;

import controller.MainViewController2;

public class CronometerThread extends Thread {
	private long count;
	private MainViewController2 controller;
	
	public CronometerThread(MainViewController2 controller) {
		count = 0;
		this.controller = controller;
	}
	
	@Override
	public void run() {
		while (controller.getToggle()) {
			try {
				sleep(1000);
				count++;
				controller.updateCronometer(count);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
