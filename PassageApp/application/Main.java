package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	private static ArrayList<Ride> rides = new ArrayList<Ride>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Passage");
			Parent root = FXMLLoader.load(getClass().getResource("/application/Home.fxml"));
			Scene scene = new Scene(root, 900, 600);
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
		
		//keeps track of rides that have different time
		//this list will added to the matchingRides list later, matching rides will be shown first
		ArrayList<Ride> similarRides = new ArrayList<Ride>();
		
		//search for rides that matches with description
		for (int i = 0; i < rides.size(); i++) {
			Ride currentRide = rides.get(i);
			if (currentRide.getStart().equalsIgnoreCase(start)) {
				if (currentRide.getDestination().equalsIgnoreCase(destination)) {
					if (currentRide.getDate().equals(date)){
						if (currentRide.getTime().equalsIgnoreCase(time)) {
							matchingRides.add(currentRide);
						} else {
							//add to ArrayList of similarRides if time doesn't match
							similarRides.add(currentRide);
						}
					}
				}
			}
		}
		
		//add similarRides to matchingRides list if there are rides in it
		if (similarRides.size() > 0) {
			//current index in matchingRides ArrayList
			for (int j = 0; j < similarRides.size(); j++) {
				matchingRides.add(similarRides.get(j));
			}
		}
		
		return matchingRides;
	}
}
