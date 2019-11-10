package application;

public class Ride {
	private String driverName;
	private String start;
	private String destination;
	private String date;
	private String time;
	private String stopover1;
	private String stopover2;
	private String arrivalTime;
	private String vehicle;
	private String seats;
	private String price;
	
	public Ride(String driverName, String start, String destination, String date, String time, String stopover1, String stopover2, String arrivalTime, String vehicle, String seats, String price) {
		this.driverName = driverName;
		this.start = start;
		this.destination = destination;
		this.date = date;
		this.time = time;
		this.arrivalTime = arrivalTime;
		this.vehicle = vehicle;
		this.seats = seats;
		this.price = price;
		
		if (stopover1.equals("")) {
			this.stopover1 = "none";
		} else {
			this.stopover1 = stopover1;
		}
		
		if (stopover2.equals("")) {
			this.stopover2 = "none";
		} else {
			this.stopover2 = stopover2;
		}
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
	
	public String getStopover1() {
		return stopover1;
	}
	
	public String getStopover2() {
		return stopover2;
	}
	
	public String getArrivalTime() {
		return arrivalTime;
	}
	
	public String getVehicle() {
		return vehicle;
	}
	
	public String getSeats() {
		return seats;
	}
	
	public String getPrice() {
		return price;
	}
}
