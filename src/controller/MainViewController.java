package controller;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import application.Main;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainViewController {
	private Main main;
	
    @FXML
    private Label date;

    @FXML
    private Label mainClock;

    @FXML
    private Label middayIndicator;
    
    private boolean flag;

    @FXML
    public void initialize() {
    	flag = true;
    	DateTimeFormatter clockFormat = DateTimeFormatter.ofPattern("hh:mm:ss");
    	DateTimeFormatter noonFormat = DateTimeFormatter.ofPattern("a");
    	
	    Task<Void> task = new Task<Void>() {
	        @Override
	        public Void call() throws InterruptedException {
	        	while (flag) {
	        		Thread.sleep(1000);
	        		LocalTime time = LocalTime.now();
	        		updateMessage(time.format(clockFormat));
	        		updateTitle(time.format(noonFormat));
	        	}
	        	
	            return null;
	        }
	    };

	    mainClock.textProperty().bind(task.messageProperty());
	    middayIndicator.textProperty().bind(task.titleProperty());

	    (new Thread(task)).start();
    }
    
	public void setMain(Main main) {
		this.main = main;
	}

	@FXML VBox content;

	public void setTime(LocalTime time, Label mainClock) {
		String s = "" + time.getHour();
		s += ":";
		s += time.getSecond() + "";
	}
	
	public void timeStamp(int counter) {
		mainClock.setText(counter + "");
	}
}
