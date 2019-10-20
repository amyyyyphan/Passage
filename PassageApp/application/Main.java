package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static ArrayList<Ride> rides = new ArrayList<Ride>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Home.fxml"));
			//BorderPane root = new BorderPane();
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void addRide(Ride ride) {
		rides.add(ride);
	}
	
	public static ArrayList<Ride> searchRide(String start, String destination, String date, String time) {
		//keeps track of rides that matches with description
		ArrayList<Ride> matchingRides = new ArrayList<Ride>();
		
		//search for rides that matches with description
		for (int i = 0; i < rides.size(); i++) {
			Ride currentRide = rides.get(i);
			if (currentRide.getStart().equalsIgnoreCase(start)) {
				if (currentRide.getDestination().equalsIgnoreCase(destination)) {
					if (currentRide.getDate().equals(date)){
						if (currentRide.getTime().equals(time)) {
							matchingRides.add(currentRide);
						}
					}
				}
			}
		}
		
		//more ride options but does not match time description
		if (matchingRides.size() == 0) {
			for (int j = 0; j < rides.size(); j++) {
				Ride currentRide = rides.get(j);
				if (currentRide.getStart().equalsIgnoreCase(start)) {
					if (currentRide.getDestination().equalsIgnoreCase(destination)) {
						if (currentRide.getDate().equals(date)){
							matchingRides.add(currentRide);
						}
					}
				}
			}
			
		}
		return matchingRides;
	}
}
