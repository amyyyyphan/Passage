package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DriverConfirmationPageController {
	private Ride rideConfirmed;

	@FXML
	private Label startDetail;
	
	@FXML
	private Label destinationDetail;
	
	@FXML
	private Label dateDetail;
	
	@FXML
	private Label timeDetail;
	
	//accepts a ride to initialize for summary	
	public void initData(Ride ride) {
		rideConfirmed = ride;
		startDetail.setText(rideConfirmed.getStart());
		destinationDetail.setText(rideConfirmed.getDestination());
		dateDetail.setText(rideConfirmed.getDate());
		timeDetail.setText(rideConfirmed.getTime());
	}
	
	public void backToHomePage(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent searchPageParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
		Scene scene = new Scene(searchPageParent,600,400);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
}
