package application.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
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
	@FXML
	private Label seatDetail;
	@FXML
	private MenuButton userButton;
	
	//accepts a ride to initialize for summary	
	public void initData(Ride ride) {
		rideConfirmed = ride;
		startDetail.setText(rideConfirmed.getStart());
		destinationDetail.setText(rideConfirmed.getDestination());
		dateDetail.setText(rideConfirmed.getDate());
		timeDetail.setText(rideConfirmed.getTime() + " - " + rideConfirmed.getArrivalTime());
		seatDetail.setText(rideConfirmed.getSeats() + " Seats Available at " + rideConfirmed.getPrice() + "/Seat");
	}
	
	public void backToHomePage(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent searchPageParent = FXMLLoader.load(getClass().getResource("/application/fxml/Home.fxml"));
		Scene scene = new Scene(searchPageParent, 900, 600);
		
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
	
	@FXML
	public void viewMyRides(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/fxml/MyRides.fxml"));
		Parent myRidesPageParent = loader.load();
		Scene myRidesPageScene = new Scene(myRidesPageParent);
		
		//access controller and pass rides that the user offered to My Rides page
		MyRidesController controller = loader.getController();
		controller.initData(Main.viewMyRides());
		
		Stage window = (Stage) userButton.getScene().getWindow();
		myRidesPageScene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		window.setScene(myRidesPageScene);
		window.show();
	}
	
	@FXML
	public void logout(ActionEvent event) throws IOException {
		Stage window = (Stage) userButton.getScene().getWindow();
		Parent loginPageParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		Scene scene = new Scene(loginPageParent, 900, 600);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
}
