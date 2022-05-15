package controller;

import java.time.LocalTime;

import application.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CronometerThread;
import model.Lap;

public class MainViewController2 {
	private Main main;
    @FXML
    private TableColumn<Lap, LocalTime> lapDuration;

    @FXML
    private TableColumn<Lap, Integer> lapNumber;

    @FXML
    private TableColumn<Lap, LocalTime> lapTotal;

    @FXML
    private TableView<Lap> tableData;
    
    @FXML
    private Label mainTimer;

    @FXML
    private Label secondsTimer;
    
    @FXML
    private Button toggleButton;
    private int counter;
    private CronometerThread cronometerThread;
    
    private boolean toggle;
    private boolean run;
    
    @FXML
    void initialize() {
    	counter = 1;
    	run = false; // No está activo
    	toggle = false; 
    	lapDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    	lapNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
    	lapTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    @FXML
    void addLap(ActionEvent event) {
    	//main.getBack().addLap(counter, CronometerThread.getDuration(), CronometerThread.getTotal());
    	counter++;
    }

    @FXML
    void stopCronometer(ActionEvent event) {
		toggle = false;
		run = false;
		toggleButton.setText("Iniciar");
		mainTimer.setText("00:00");
		secondsTimer.setText("00");
    }

    @FXML
    void toClock(ActionEvent event) {
    	main.openClock();
    }

    @FXML
    void toggleCronometer(ActionEvent event) {
    	if (toggle) {
    		if (run) { 
    			toggle = false;
        		run = false;
        		toggleButton.setText("Iniciar");
        	} else { // Activar
        		cronometerThread.notify();
        		toggleButton.setText("Pausar");
        		run = true;
        	}
    	} else {
        	toggle = true;
        	run = true;
    		toggleButton.setText("Pausar");
        	cronometerThread = new CronometerThread(this);
        	cronometerThread.start();
    	}
    }

    public void updateCronometer(long count) {
    	Platform.runLater(new Runnable() {
            @Override public void run() {
            	int hours = (int) (count/3600);
            	int minutes = (int) (count/60);
            	int seconds = (int) (count%60);

            	String hh = "00";
            	String mm = "00";
            	String ss = "00";
            	
            	if (hours < 10) hh = "0" + hours;
            	else hh = hours + "";
            	
            	if (minutes < 10) mm = "0" + minutes;
            	else mm = minutes + "";
            	
            	if (seconds < 10) ss = "0" + seconds;
            	else ss = seconds + "";
            	
            	mainTimer.setText(hh + ":" + mm);
            	secondsTimer.setText(ss);
            }
        });
    }
    
	public void setMain(Main main) {
		this.main = main;
    	tableData.setItems(FXCollections.observableList(main.getBack().getLaps()));
	}

	public boolean getToggle() {
		return toggle;
	}

	public boolean getRun() {
		return run;
	}
}
