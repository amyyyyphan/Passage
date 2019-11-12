package application;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AvailableRidesPageController {
	private ArrayList<Ride> ridesList;
	
	@FXML
	private Label status;
	
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
	
	//need to fix code
	public void initData(ArrayList<Ride> rides) {
		ridesList = rides;
		if (rides.size() == 0) {
			status.setText("Sorry, there are no rides available.");
			firstRecommendation.setVisible(false);
			secondRecommendation.setVisible(false);
		} else if (rides.size() == 1) {
			secondRecommendation.setVisible(false);
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
		} else {
			status.setText("Two rides are recommended for your travel details...");
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
			
			
			Ride ride2 = rides.get(1);
			cost2.setText(ride2.getPrice() + "/Seat");
			start2.setText(ride2.getStart());
			destination2.setText(ride2.getDestination());
			vehicle2.setText(ride2.getVehicle());
			stopover21.setText(ride2.getStopover1());
			stopover22.setText(ride2.getStopover2());
			time2.setText(ride2.getTime());
			arrivalTime2.setText(ride.getArrivalTime());
			seat2.setText(ride2.getSeats());
			name2.setText(Main.getDriverName(ride2.getUsername()));
		}
		
	}
	
	@FXML
	public void optionSelected(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("RiderConfirmation.fxml"));
		Parent confirmationPageParent = loader.load();
		Scene confirmationPageScene = new Scene(confirmationPageParent);
		
		//access controller and pass selected ride to the confirmation page
		RiderConfirmationController controller = loader.getController();
		if (event.getSource() == selectButton1) {
			Main.updateSeats(ridesList.get(0));
			controller.initData(ridesList.get(0));
		} else {
			Main.updateSeats(ridesList.get(1));
			controller.initData(ridesList.get(1));
		}
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		confirmationPageScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(confirmationPageScene);
		window.show();
	}
	
	
	@FXML
	public void backToHomePage(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Parent searchPageParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
		Scene scene = new Scene(searchPageParent, 900, 600);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}

}
