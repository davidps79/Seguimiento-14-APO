package controller;

import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Lap;

public class MainViewController2 {

    @FXML
    private TableColumn<Lap, LocalTime> lapDuration;

    @FXML
    private TableColumn<Lap, Integer> lapNumber;

    @FXML
    private TableColumn<Lap, LocalTime> lapTotal;

    @FXML
    private TableView<Lap> tableData;
    
    @FXML
    private Label mainClock;

    @FXML
    private Label middayIndicator;

    @FXML
    void addLap(ActionEvent event) {

    }

    @FXML
    void stopCronometer(ActionEvent event) {

    }

    @FXML
    void toClock(ActionEvent event) {

    }

    @FXML
    void toggleCronometer(ActionEvent event) {

    }

	@FXML
	public void toClock() {
		
	}
}
