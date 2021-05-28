package application.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AvailableRidesPageController {
	//list of available rides
	private ArrayList<Ride> ridesList;
	
	@FXML
	private Label status;
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private AnchorPane firstRecommendation;
	@FXML
	private Label cost1;
	@FXML
	private Label start1;
	@FXML
	private Label destination1;
	@FXML
	private Label vehicle1;
	@FXML
	private Label stopover11;
	@FXML
	private Label stopover12;
	@FXML
	private Label time1;
	@FXML
	private Label arrivalTime1;
	@FXML
	private Label seat1;
	@FXML
	private Label name1;
	@FXML
	private Button selectButton1;
	
	@FXML
	private AnchorPane secondRecommendation;
	@FXML
	private Label cost2;
	@FXML
	private Label start2;
	@FXML
	private Label destination2;
	@FXML
	private Label vehicle2;
	@FXML
	private Label stopover21;
	@FXML
	private Label stopover22;
	@FXML
	private Label time2;
	@FXML
	private Label arrivalTime2;
	@FXML
	private Label seat2;
	@FXML
	private Label name2;
	@FXML
	private Button selectButton2;
	
	@FXML
	private AnchorPane thirdRecommendation;
	@FXML
	private Label cost3;
	@FXML
	private Label start3;
	@FXML
	private Label destination3;
	@FXML
	private Label vehicle3;
	@FXML
	private Label stopover31;
	@FXML
	private Label stopover32;
	@FXML
	private Label time3;
	@FXML
	private Label arrivalTime3;
	@FXML
	private Label seat3;
	@FXML
	private Label name3;
	@FXML
	private Button selectButton3;
	
	@FXML
	private AnchorPane fourthRecommendation;
	@FXML
	private Label cost4;
	@FXML
	private Label start4;
	@FXML
	private Label destination4;
	@FXML
	private Label vehicle4;
	@FXML
	private Label stopover41;
	@FXML
	private Label stopover42;
	@FXML
	private Label time4;
	@FXML
	private Label arrivalTime4;
	@FXML
	private Label seat4;
	@FXML
	private Label name4;
	@FXML
	private Button selectButton4;
	
	//accepts a list of rides that matched with information from Search page
	//currently can only show up to 4 rides
	public void initData(ArrayList<Ride> rides) {
		ridesList = rides;
		if (rides.size() == 0) {
			status.setText("Sorry, there are no rides available.");
			scrollPane.setVisible(false);
		} else if (rides.size() >= 1) {		
			status.setText("One ride is recommended for your travel details...");
			Ride ride = rides.get(0);
			cost1.setText(ride.getPrice() + "/Seat");
			start1.setText(ride.getStart());
			destination1.setText(ride.getDestination());
			vehicle1.setText(ride.getVehicle());
			stopover11.setText(ride.getStopover1());
			stopover12.setText(ride.getStopover2());
			time1.setText(ride.getTime());
			arrivalTime1.setText(ride.getArrivalTime());
			seat1.setText(ride.getSeats());
			name1.setText(Main.getDriverName(ride.getUsername()));
			
			if (rides.size() >= 2) {
				status.setText("Two rides are recommended for your travel details...");
				Ride ride2 = rides.get(1);
				cost2.setText(ride2.getPrice() + "/Seat");
				start2.setText(ride2.getStart());
				destination2.setText(ride2.getDestination());
				vehicle2.setText(ride2.getVehicle());
				stopover21.setText(ride2.getStopover1());
				stopover22.setText(ride2.getStopover2());
				time2.setText(ride2.getTime());
				arrivalTime2.setText(ride2.getArrivalTime());
				seat2.setText(ride2.getSeats());
				name2.setText(Main.getDriverName(ride2.getUsername()));
				
				if (rides.size() >= 3) {
					status.setText("Three rides are recommended for your travel details...");
					Ride ride3 = rides.get(2);
					cost3.setText(ride3.getPrice() + "/Seat");
					start3.setText(ride3.getStart());
					destination3.setText(ride3.getDestination());
					vehicle3.setText(ride3.getVehicle());
					stopover31.setText(ride3.getStopover1());
					stopover32.setText(ride3.getStopover2());
					time3.setText(ride3.getTime());
					arrivalTime3.setText(ride3.getArrivalTime());
					seat3.setText(ride3.getSeats());
					name3.setText(Main.getDriverName(ride3.getUsername()));
					
					if (rides.size() >= 4) {
						status.setText("Four rides are recommended for your travel details...");
						Ride ride4 = rides.get(3);
						cost4.setText(ride4.getPrice() + "/Seat");
						start4.setText(ride4.getStart());
						destination4.setText(ride4.getDestination());
						vehicle4.setText(ride4.getVehicle());
						stopover41.setText(ride4.getStopover1());
						stopover42.setText(ride4.getStopover2());
						time4.setText(ride4.getTime());
						arrivalTime4.setText(ride4.getArrivalTime());
						seat4.setText(ride4.getSeats());
						name4.setText(Main.getDriverName(ride4.getUsername()));
					} else {
						fourthRecommendation.setVisible(false);
					}
				} else {
					scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
				}
			} else {
				//do not allow scroll if there are 2 rides or less
				scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
				secondRecommendation.setVisible(false);
			}
		}
		
	}
	
	@FXML
	public void optionSelected(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/fxml/RiderConfirmation.fxml"));
		Parent confirmationPageParent = loader.load();
		Scene confirmationPageScene = new Scene(confirmationPageParent);
		
		//access controller and pass selected ride to the confirmation page
		RiderConfirmationController controller = loader.getController();
		if (event.getSource() == selectButton1) {
			Main.updateSeats(ridesList.get(0));
			controller.initData(ridesList.get(0));
		} else if (event.getSource() == selectButton2) {
			Main.updateSeats(ridesList.get(1));
			controller.initData(ridesList.get(1));
		} else if (event.getSource() == selectButton3) {
			Main.updateSeats(ridesList.get(2));
			controller.initData(ridesList.get(2));
		} else {
			Main.updateSeats(ridesList.get(3));
			controller.initData(ridesList.get(3));
		}
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		confirmationPageScene.getStylesheets().add(getClass().getResource("/application/css/application.css").toExternalForm());
		window.setScene(confirmationPageScene);
		window.show();
	}
	
	
	@FXML
	public void backToHomePage(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent searchPageParent = FXMLLoader.load(getClass().getResource("/application/fxml/Home.fxml"));
		Scene scene = new Scene(searchPageParent, 900, 600);
		
		scene.getStylesheets().add(getClass().getResource("/application/css/application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}

}
