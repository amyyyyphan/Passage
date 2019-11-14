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

public class RiderConfirmationController {	
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
	private Label driverName;
	
	@FXML
	private Label driverNumber;
	
	@FXML
	private Label driverEmail;
	
	//accepts a ride to initialize for summary	
	public void initData(Ride ride) {
		startDetail.setText(ride.getStart());
		destinationDetail.setText(ride.getDestination());
		dateDetail.setText(ride.getDate());
		timeDetail.setText(ride.getTime());
		seatDetail.setText(ride.getSeats() + " Seats Available at " + ride.getPrice() + "/Seat");
		driverName.setText(Main.getDriverName(ride.getUsername()));
		driverNumber.setText(Main.getDriverNumber(ride.getUsername()));
		driverEmail.setText(ride.getUsername());
	}
	
	public void backToHomePage(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent searchPageParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
		Scene scene = new Scene(searchPageParent, 900, 600);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
}
