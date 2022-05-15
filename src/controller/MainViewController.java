package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import application.Main;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
    private VBox clocksGroup;
    
    @FXML
    private Button addTimeZoneButton;
    
    private LocalDateTime mainClockTime;
    
    private boolean flag;

    DateTimeFormatter clockFormat = DateTimeFormatter.ofPattern("hh:mm:ss");
    DateTimeFormatter exClockFormat = DateTimeFormatter.ofPattern("EEE. d 'de' mm - hh:mm:ss a");
	DateTimeFormatter noonFormat = DateTimeFormatter.ofPattern("a");
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE., d 'de' MMM.");
	
    @FXML
    public void initialize() {
    	mainClockTime = LocalDateTime.now();
    	flag = true;
    	
	    Task<Void> mainClockSetter = new Task<Void>() {
	        @Override
	        public Void call() throws InterruptedException {
	        	while (flag) {
	        		Thread.sleep(1000);
	        		LocalDateTime time = LocalDateTime.now();
	        		mainClockTime = time;
	        		updateMessage(time.format(clockFormat));
	        		updateTitle(time.format(noonFormat));
	        	}
	        	
	            return null;
	        }
	    };
	    
	    Task<Void> dateSetter = new Task<Void>() {
	        @Override
	        public Void call() throws InterruptedException {
	        	while (flag) {
	        		Thread.sleep(1000);
	        		LocalDate date = LocalDate.now();
	        		updateMessage(date.format(dateFormat));
	        	}
	            return null;
	        }
	    };

	    mainClock.textProperty().bind(mainClockSetter.messageProperty());
	    middayIndicator.textProperty().bind(mainClockSetter.titleProperty());
	    date.textProperty().bind(dateSetter.messageProperty());

	    (new Thread(mainClockSetter)).start();
	    (new Thread(dateSetter)).start();
    }
    
	public void setMain(Main main) {
		this.main = main;
	}

	@FXML VBox content;
	
	public void timeStamp(int counter) {
		mainClock.setText(counter + "");
	}
	
	public void addClock(String zone) {
		Label zoneLabel = new Label(zone);
		Label clockLabel = new Label();
		HBox hbox = new HBox(zoneLabel);
		hbox.setSpacing(15);
		hbox.setPadding(new Insets(0, 15, 0, 15));
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.getChildren().add(clockLabel);
		
	    Task<Void> clockSetter = new Task<Void>() {	    	
	        @Override
	        public Void call() throws InterruptedException {	    
	        	while (flag) {	 
	        		Thread.sleep(1000);
	        		LocalDateTime exTime = LocalDateTime.now(ZoneId.of(zone));
	        		updateMessage(exTime.format(exClockFormat));
	        	}
	        	
	            return null;
	        }
	    };

	    (new Thread(clockSetter)).start();
	    clockLabel.textProperty().bind(clockSetter.messageProperty());

	    clocksGroup.getChildren().add(hbox);
	}
	
	@FXML
	public void addTimeZone() {
		main.openAddTimeZone();
	}
	
	@FXML
	public void toCronometer() {
		
	}
}
