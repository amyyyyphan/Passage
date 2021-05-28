package application.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MyRidesController {
	@FXML
	private AnchorPane myRideBox1;
	@FXML
	private AnchorPane myRideBox2;
	@FXML
	private AnchorPane myRideBox3;
	@FXML
	private AnchorPane myRideBox4;
	@FXML
	private AnchorPane myRideBox5;
	
	@FXML
	private Label date1;
	@FXML
	private Label date2;
	@FXML
	private Label date3;
	@FXML
	private Label date4;
	@FXML
	private Label date5;
	
	@FXML
	private Label destination1;
	@FXML
	private Label destination2;
	@FXML
	private Label destination3;
	@FXML
	private Label destination4;
	@FXML
	private Label destination5;
	
	@FXML
	private Label seats1;
	@FXML
	private Label seats2;
	@FXML
	private Label seats3;
	@FXML
	private Label seats4;
	@FXML
	private Label seats5;
	
	@FXML
	private Label status;
	
	//accepts a list of rides the user offered and show ride boxes based on how many there are
	//currently can only show up to 5 rides that are closest to the current date
	public void initData(ArrayList<Ride> rides) {
		int myRideOffers = rides.size();
		if (myRideOffers == 0) {
			status.setText("You have not offered any rides.");
		} else {
			status.setVisible(false);
		}
		
		if (myRideOffers >= 1) {
			date1.setText(rides.get(0).getDate());
			destination1.setText(rides.get(0).getDestination());
			seats1.setText(rides.get(0).getSeats());
		} else {
			myRideBox1.setVisible(false);
			myRideBox2.setVisible(false);
			myRideBox3.setVisible(false);
			myRideBox4.setVisible(false);
			myRideBox5.setVisible(false);
		}
		if (myRideOffers >= 2) {
			date2.setText(rides.get(1).getDate());
			destination2.setText(rides.get(1).getDestination());
			seats2.setText(rides.get(1).getSeats());
		} else {
			myRideBox2.setVisible(false);
			myRideBox3.setVisible(false);
			myRideBox4.setVisible(false);
			myRideBox5.setVisible(false);
		}
		if (myRideOffers >= 3) {
			date3.setText(rides.get(2).getDate());
			destination3.setText(rides.get(2).getDestination());
			seats3.setText(rides.get(2).getSeats());
		} else {
			myRideBox3.setVisible(false);
			myRideBox4.setVisible(false);
			myRideBox5.setVisible(false);
		}
		
		if (myRideOffers >= 4) {
			date4.setText(rides.get(3).getDate());
			destination4.setText(rides.get(3).getDestination());
			seats4.setText(rides.get(3).getSeats());
		} else {
			myRideBox4.setVisible(false);
			myRideBox5.setVisible(false);
		}
		
		if (myRideOffers >= 5) {
			date5.setText(rides.get(4).getDate());
			destination5.setText(rides.get(4).getDestination());
			seats5.setText(rides.get(4).getSeats());
		}
	}
	
	public void backToHomePage(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent searchPageParent = FXMLLoader.load(getClass().getResource("/application/fxml/Home.fxml"));
		Scene scene = new Scene(searchPageParent, 900, 600);
		
		scene.getStylesheets().add(getClass().getResource("/application/css/application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
}
