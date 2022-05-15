package controller;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;

import application.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;

public class AddTimeZoneController {
	private Main main;
	
	public void setLinks(Main main, MainViewController controller) {
		this.main = main;
		this.controller = controller;
	}
	
	private MainViewController controller;
	
    @FXML
    private Button addTimeZoneButton;

    @FXML
    private ComboBox<String> timeZoneOptions;

    @FXML
    void addTimeZone() {
    	Alert alert;
    	if (timeZoneOptions.getValue() != null) {
    		alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText(null);
    		alert.setTitle("Zona agregada");
    		alert.setContentText("Se ha agregado una nueva zona horaria");
    		controller.addClock(timeZoneOptions.getValue());
    		main.closePopUp();
    	} else {
    		alert = new Alert(AlertType.WARNING);
    		alert.setHeaderText(null);
    		alert.setTitle("Campos vacíos");
    		alert.setContentText("Debe ingresar una zona horaria");
    	}
    	
		alert.showAndWait();
    }
    
    @FXML
    public void initialize() {
    	ArrayList<String> arr = new ArrayList<>();
    	
    	for (String zoneId : ZoneId.getAvailableZoneIds()) {
    		arr.add(zoneId);
    	}
    	
    	Collections.sort(arr);
    	timeZoneOptions.setItems(FXCollections.observableList(arr));
    }
}
