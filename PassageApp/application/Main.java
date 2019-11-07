package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	private static Connection con;
	
	//private static ArrayList<Ride> rides = new ArrayList<Ride>();
	
	@Override
	public void start(Stage primaryStage) {
		//connect to database
		connectDB();
		
		try {
			primaryStage.setTitle("Passage");
//			Parent root = FXMLLoader.load(getClass().getResource("/application/Home.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("/application/LoginPage.fxml"));
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
	
	public static void addUser(String firstName, String lastName, String username, String password, String phone) {		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/passage_db", "root", "");
			String query = "INSERT INTO user (firstName, lastName, username, password, phone) values(?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,  firstName);
			ps.setString(2,	 lastName);
			ps.setString(3,  username);
			ps.setString(4,  password);
			ps.setString(5,  phone);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addRide(Ride ride) {			
		try {
			String query = "INSERT INTO ride (name, start, destination, date, time, seats, price) values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, ride.getDriverName());
			ps.setString(2, ride.getStart());
			ps.setString(3, ride.getDestination());
			ps.setString(4, ride.getDate());
			ps.setString(5, ride.getTime());
			ps.setString(6, ride.getSeats());
			ps.setString(7, ride.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//rides.add(ride);
	}
	
	public static ArrayList<Ride> searchRide(String start, String destination, String date, String time) {
		//keeps track of rides that matches with description
		ArrayList<Ride> matchingRides = new ArrayList<Ride>();
		
		//keeps track of rides that have different time
		//this list will added to the matchingRides list later, matching rides will be shown first
		ArrayList<Ride> similarRides = new ArrayList<Ride>();
		
//		//search for rides that matches with description
//		for (int i = 0; i < rides.size(); i++) {
//			Ride currentRide = rides.get(i);
//			if (currentRide.getStart().equalsIgnoreCase(start)) {
//				if (currentRide.getDestination().equalsIgnoreCase(destination)) {
//					if (currentRide.getDate().equals(date)){
//						if (currentRide.getTime().equalsIgnoreCase(time)) {
//							matchingRides.add(currentRide);
//						} else {
//							//add to ArrayList of similarRides if time doesn't match
//							similarRides.add(currentRide);
//						}
//					}
//				}
//			}
//		}
		
		//add similarRides to matchingRides list if there are rides in it
		if (similarRides.size() > 0) {
			//current index in matchingRides ArrayList
			for (int j = 0; j < similarRides.size(); j++) {
				matchingRides.add(similarRides.get(j));
			}
		}
		
		try {
			Statement statement = con.createStatement();
			String sql = ("SELECT `name`, `start`, `destination`, `date`, `time`, `seats`, `price` FROM `ride` WHERE start = '" + start + "' and destination = '" + destination + "' and date = '" + date +"'");
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String name1 = rs.getString("name");
				String start1 = rs.getString("start");
				String destination1 = rs.getString("destination");
				String date1 = rs.getString("date");
				String time1 = rs.getString("time");
				String seat1 = rs.getString("seats");
				String price1 = rs.getString("price");
				Ride rideFound = new Ride(name1, start1, destination1, date1, time1, seat1, price1);
				matchingRides.add(rideFound);
				System.out.println("Passed");
			}
			rs.close();
			
//			//for similar rides
//			String sql1 = "SELECT `name`, `start`, `destination`, `date`, `time`, `seats`, `price` FROM `ride` WHERE ";
//			//+ "//start = %s and destination = %s and date = %s", start, destination, date);
//			ResultSet rs1 = statement.executeQuery(sql1);
//			while(rs1.next()) {
//				String name1 = rs1.getString("name");
//				String start1 = rs1.getString("start");
//				String destination1 = rs1.getString("destination");
//				String date1 = rs1.getString("date");
//				String time1 = rs1.getString("time");
//				String seat1 = rs1.getString("seats");
//				String price1 = rs1.getString("price");
//				Ride rideFound = new Ride(name1, start1, destination1, date1, time1, seat1, price1);
//				matchingRides.add(rideFound);
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return matchingRides;
	}
	
	public void connectDB() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/passage_db", "root", "");
			System.out.println("Connected to database");
		} catch(Exception e) {
			System.out.println("Failed to connect to database");
			System.out.println(e.getMessage());
			
		}
	}
}
