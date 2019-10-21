package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchPageController {
	@FXML
	private Button backButton;
	
	@FXML
	private Button nextButton;
	
	@FXML
	private TextField startInfo;
	
	@FXML
	private TextField destinationInfo;
	
	@FXML
	private DatePicker dateInfo;
	
	@FXML
	private TextField timeInfo;
	
	public void buttonChosen(ActionEvent event) throws IOException {
		//gets stage information
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		if (event.getSource() == backButton) {
			Parent searchPageParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(searchPageParent,600,400);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		} else {
			String startingPoint = startInfo.getText();
			String destination = destinationInfo.getText();
			
			//convert date to string
			LocalDate date = dateInfo.getValue();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String formattedDate = date.format(formatter);
			
			String time = timeInfo.getText();
			
			//search for rides that matches with info given
			ArrayList<Ride> availableRides = Main.searchRide(startingPoint, destination, formattedDate, time);
			
			//pass rides to the confirmation page
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AvailableRides.fxml"));
			Parent availablePageParent = loader.load();
			Scene availablePageScene = new Scene(availablePageParent);
			
			//access controller
			AvailableRidesPageController controller = loader.getController();
			controller.initData(availableRides);
			
			availablePageScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(availablePageScene);
			window.show();
			
		}
	}
}
