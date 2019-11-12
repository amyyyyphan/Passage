package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DriveDetailsController {
	@FXML
	private TextField stopover1;

	@FXML
	private TextField stopover2;
	
	@FXML
	private TextField arrivalTime;
	
	@FXML
	private TextField vehicle;
	
	@FXML
	private TextField seatInfo;
	
	@FXML
	private TextField priceInfo;
	
	@FXML
	private Label error;
	
	private String start;
	private String destination;
	private String date;
	private String time;
	
	public void travelDetails(String start, String destination, String date, String time) {
		this.start = start;
		this.destination = destination;
		this.date = date;
		this.time = time;
	}
	
	@FXML
	public void nextButtonChosen(ActionEvent event) throws IOException {
		if (arrivalTime.getText().isEmpty() || vehicle.getText().isEmpty() || seatInfo.getText().isEmpty() || priceInfo.getText().isEmpty()) {
			error.setText("Please fill out all required fields.");
		} else {
			//gets the current user's username to save along with the ride offered in database
			String driverUsername = Main.getCurrentUsername();
			Ride newRide = new Ride(driverUsername, start, destination, date, time, stopover1.getText(), stopover2.getText(), arrivalTime.getText(), vehicle.getText(), seatInfo.getText(), priceInfo.getText());
			//save new ride into database
			Main.addRide(newRide);
			
			//pass ride to the confirmation page
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("DriverConfirmation.fxml"));
			Parent confirmationPageParent = loader.load();
			Scene confirmationPageScene = new Scene(confirmationPageParent);
			
			//access controller
			DriverConfirmationPageController controller = loader.getController();
			controller.initData(newRide);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			confirmationPageScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(confirmationPageScene);
			window.show();
		}
	}
	
	@FXML
	public void backButtonChosen(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent offerPageParent = FXMLLoader.load(getClass().getResource("OfferPage.fxml"));
		Scene scene = new Scene(offerPageParent, 900, 600);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
}
