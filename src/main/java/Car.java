package main.java;

public class Car extends Vehicle{
	
	// Instance Variables
	private String category;         // e.g. Sedan, SUV, Sports, etc.    
	
	// Constructors
	public Car(VehicleSize size, VehiclePassengers includesHandicapped) {
		super(VehicleType.CAR, size, includesHandicapped);
		category = "";
	}
	
}
