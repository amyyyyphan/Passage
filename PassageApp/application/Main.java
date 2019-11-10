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
	
	//keeps track of who's using the app by keeping their username in currentUser
	private static String currentUser;
	
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
			//close
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//checks if login information is correct
	public static boolean checkAccountInfo(String username, String password) {
		try {
			Statement statement = con.createStatement();
			String sql = ("SELECT * FROM `user` WHERE username = '" + username + "' and password = '" + password + "'");
			ResultSet rs = statement.executeQuery(sql);
			
			//return true if there is a username with the password given in the database otherwise false
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//should not get to this line
		return false;
	}
	
	//change currentUser to given username
	public static void changeCurrentUser(String username) {
		currentUser = username;
	}
	
	public static String getCurrentUsername() {
		return currentUser;
	}
	
	public static String getDriverName(String username) {
		try {
			Statement statement = con.createStatement();
			String sql = "SELECT * FROM `user` WHERE username = '" + username + "'";
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String fullName = firstName + " " + lastName;
				System.out.println(fullName);
				return fullName;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "wrong username";
	}
	
	public static void addRide(Ride ride) {			
		try {
			String query = "INSERT INTO ride (username, start, destination, date, time, stopOver1, stopOver2, arrivalTime, vehicle, seats, price) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, ride.getDriverName());
			ps.setString(2, ride.getStart());
			ps.setString(3, ride.getDestination());
			ps.setString(4, ride.getDate());
			ps.setString(5, ride.getTime());
			ps.setString(6, ride.getStopover1());
			ps.setString(7, ride.getStopover2());
			ps.setString(8, ride.getArrivalTime());
			ps.setString(9, ride.getVehicle());
			ps.setString(10, ride.getSeats());
			ps.setString(11, ride.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Ride> searchRide(String start, String destination, String date, String time) {
		//keeps track of rides that matches with description
		ArrayList<Ride> matchingRides = new ArrayList<Ride>();
		
		try {
			Statement statement = con.createStatement();
			String sql = ("SELECT * FROM `ride` WHERE start = '" + start + "' and destination = '" + destination + "' and date = '" + date +"' order by abs(timediff(str_to_date('" + time + "', '%h %p'), str_to_date(time, '%h %p'))) asc");
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String name1 = rs.getString("username");
				String start1 = rs.getString("start");
				String destination1 = rs.getString("destination");
				String date1 = rs.getString("date");
				String time1 = rs.getString("time");
				String stopover1 = rs.getString("stopOver1");
				String stopover2 = rs.getString("stopOver2");
				String arrivalTime1 = rs.getString("arrivalTime");
				String vehicle1 = rs.getString("vehicle");
				String seat1 = rs.getString("seats");
				String price1 = rs.getString("price");
				Ride rideFound = new Ride(name1, start1, destination1, date1, time1, stopover1, stopover2, arrivalTime1, vehicle1, seat1, price1);
				matchingRides.add(rideFound);
				System.out.println("Passed");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return matchingRides;
	}
	
	public static ArrayList<Ride> viewMyRides() {
		ArrayList<Ride> myRides = new ArrayList<Ride>();
		
		try {
			Statement statement = con.createStatement();
			String sql = ("SELECT * FROM `ride` WHERE username = '" + currentUser + "' order by abs(datediff(now(), str_to_date(time, '%h %p'))) asc");
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String name1 = rs.getString("username");
				String start1 = rs.getString("start");
				String destination1 = rs.getString("destination");
				String date1 = rs.getString("date");
				String time1 = rs.getString("time");
				String stopover1 = rs.getString("stopOver1");
				String stopover2 = rs.getString("stopOver2");
				String arrivalTime1 = rs.getString("arrivalTime");
				String vehicle1 = rs.getString("vehicle");
				String seat1 = rs.getString("seats");
				String price1 = rs.getString("price");
				Ride rideFound = new Ride(name1, start1, destination1, date1, time1, stopover1, stopover2, arrivalTime1, vehicle1, seat1, price1);
				myRides.add(rideFound);
				System.out.println("Passed");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myRides;
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
