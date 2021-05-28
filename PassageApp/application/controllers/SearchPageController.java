package application.controllers;

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
import javafx.scene.control.Label;
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
	@FXML
	private Label error;
	@FXML
	public void buttonChosen(ActionEvent event) throws IOException {
		//gets stage information
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		if (event.getSource() == backButton) {
			//back button clicked, go back to home page
			Parent searchPageParent = FXMLLoader.load(getClass().getResource("/application/fxml/Home.fxml"));
			Scene scene = new Scene(searchPageParent,900,600);
			
			scene.getStylesheets().add(getClass().getResource("/application/css/application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		} else {
			//next button pressed
			//checks if user filled all fields
			if (startInfo.getText().isEmpty() || destinationInfo.getText().isEmpty() || dateInfo.getValue() == null || timeInfo.getText().isEmpty()) {
				error.setText("Please fill out all fields.");
			} else {
				String startingPoint = startInfo.getText();
				String destination = destinationInfo.getText();
				
				//convert date to string
				LocalDate date = dateInfo.getValue();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				String formattedDate = date.format(formatter);
				
				String time = timeInfo.getText();
				
				//search for rides that matches with info given and put into this ArrayList
				ArrayList<Ride> availableRides = Main.searchRide(startingPoint, destination, formattedDate, time);
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/application/fxml/AvailableRides.fxml"));
				Parent availablePageParent = loader.load();
				Scene availablePageScene = new Scene(availablePageParent);
				
				//access controller and pass an ArrayList of matching rides to Available Rides page
				AvailableRidesPageController controller = loader.getController();
				controller.initData(availableRides);
				
				//show Available Rides page
				availablePageScene.getStylesheets().add(getClass().getResource("/application/css/application.css").toExternalForm());
				window.setScene(availablePageScene);
				window.show();
			}
			
		}
	}
}
