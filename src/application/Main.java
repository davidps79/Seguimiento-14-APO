package application;
	
import java.time.LocalDateTime;
import java.time.ZoneId;
import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.TimeSystem;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	private final int WINDOW_WIDTH = 852;
	private final int WINDOW_HEIGHT = 480;
	
	private Stage currentStage;
	private Stage popUpStage;
	private MainViewController mainViewController;
	private TimeSystem back;
	
	@Override
	public void start(Stage primaryStage) {
		back = new TimeSystem(); 

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/MainView.fxml"));
			BorderPane root = (BorderPane) loader.load();
			MainViewController controller = loader.getController();
			mainViewController = controller;
			controller.setMain(this);
			Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Seguimiento 14");
			//primaryStage.getIcons().add(new Image(""));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openAddTimeZone() {
		try {
			Stage stage = new Stage();
			popUpStage = stage;
			stage.initModality(Modality.APPLICATION_MODAL);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/AddTimeZoneView.fxml"));
			BorderPane root = (BorderPane) loader.load();
			AddTimeZoneController controller = loader.getController();
			controller.setLinks(this, mainViewController);
			Scene scene = new Scene(root, 300, 200);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Agregar zona horaria");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void closePopUp() {
		popUpStage.close();
		mainViewController = null;
	}
}