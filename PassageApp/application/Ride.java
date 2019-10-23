package application;

public class Ride {
	private String driverName;
	private String start;
	private String destination;
	private String date;
	private String time;
	private String seats;
	private String price;
	
	public Ride(String driverName, String start, String destination, String date, String time, String seats, String price) {
		this.driverName = driverName;
		this.start = start;
		this.destination = destination;
		this.date = date;
		this.time = time;
		this.seats = seats;
		this.price = price;
	}
	
	public String getDriverName() {
		return driverName;
	}
	
	public String getStart() {
		return start;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getSeats() {
		return seats;
	}
	
	public String getPrice() {
		return price;
	}
}
