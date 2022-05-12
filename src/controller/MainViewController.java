package controller;

import java.io.IOException;
import java.time.LocalTime;

import application.Main;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainViewController {
	private Main main;
	
    @FXML
    private Label date;

    @FXML
    private Label mainClock;

    @FXML
    private Label middayIndicator;

    @FXML
    public void initialize() {
	    Task<Void> task = new Task<Void>() {
	        @Override
	        public Void call() throws InterruptedException {
	        	for (int i=0; i<50; i++) {
	        		Thread.sleep(1000);
	        		updateMessage(i + ":");
	        	}
	            return null ;
	        }
	    };

	    mainClock.textProperty().bind(task.messageProperty());
	    (new Thread(task)).start();
    }
    
	public void setMain(Main main) {
		this.main = main;
	}

	@FXML VBox content;

	public void setMainClock(LocalTime time) {
	}
	
	public void setTime(LocalTime time, Label mainClock) {
		String s = "" + time.getHour();
		s += ":";
		s += time.getSecond() + "";
	}
	
	public void timeStamp(int counter) {
		mainClock.setText(counter + "");
	}
}
