package controller;

import java.time.ZoneId;
import java.util.ArrayList;

import application.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

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
    	if (timeZoneOptions.getValue() != null) {
    		controller.addClock(timeZoneOptions.getValue());
    		main.closePopUp();
    	}
    }
    
    @FXML
    public void initialize() {
    	ArrayList<String> arr = new ArrayList<>();
    	
    	for (String zoneId : ZoneId.getAvailableZoneIds()) {
    		arr.add(zoneId);
    	}
    	
    	timeZoneOptions.setItems(FXCollections.observableList(arr));
    }
}
