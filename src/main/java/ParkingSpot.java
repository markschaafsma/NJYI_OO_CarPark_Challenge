package main.java;

public class ParkingSpot {
	
	// Enums
	
	public enum ParkingSpotSize {
		
		LARGE("Large"),
		MEDIUM("Medium"),
		SMALL("Small"),
		UNDEFINED("");
		
		private String name;
		
		ParkingSpotSize (String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}	
	
	// Instance Variables
	
	private int id;
	private boolean isForHandicapped;
	private ParkingSpotSize size;         
	private boolean isAvailable;
	private Vehicle vehicle;

	// Static variables
	
	private static int parkingSpotId = 0;

	// Constructors
	
	public ParkingSpot() {
		this.id = parkingSpotId++;
		this.isForHandicapped = false;
		this.size = ParkingSpotSize.UNDEFINED;
		this.isAvailable = true;
		this.vehicle = null;            // null means no vehicle allocated  
	}

	public ParkingSpot(boolean isForHandicapped, ParkingSpotSize size) {
		this.id = parkingSpotId++;
		this.isForHandicapped = isForHandicapped;
		this.size = size;
		this.isAvailable = true;
	}

	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsForHandicapped() {
		return isForHandicapped;
	}

	public void setIsForHandicapped(boolean isForHandicapped) {
		this.isForHandicapped = isForHandicapped;
	}
	
	public ParkingSpotSize getSize() {
		return this.size;
	}

	public void setSize(ParkingSpotSize size) {
		this.size = size;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}
	
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public static int getParkingSpotId() {
		return parkingSpotId;
	}

	public static void setParkingSpotId(int parkingSpotId) {
		ParkingSpot.parkingSpotId = parkingSpotId;
	}
	
}
