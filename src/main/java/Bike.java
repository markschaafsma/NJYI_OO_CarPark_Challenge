package main.java;

public class Bike extends Vehicle {

	// Instance Variables
	private String category;          // .e.g. Road, Trail, etc.
	
	// Constructors
	public Bike(VehicleSize size, VehiclePassengers includesHandicapped) {
		super(VehicleType.BIKE, size, includesHandicapped);
		this.category = "";
	}

}
