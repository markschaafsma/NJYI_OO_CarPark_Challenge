package main.java;

public class Truck extends Vehicle {

	// Instance Variables
	private String category;    // e.g. Tow, Dump, Haul, etc. 

	// Constructors
	public Truck(VehicleSize size, VehiclePassengers includesHandicapped) {
		super(VehicleType.TRUCK, size, includesHandicapped);
		this.category = "";
	}	
		
}
