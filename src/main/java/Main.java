package main.java;

import java.util.ArrayList;
import java.util.List;

import main.java.ParkingSpot.ParkingSpotSize;
import main.java.Vehicle.VehiclePassengers;
import main.java.Vehicle.VehicleSize;

/*
 * Challenge
 * 
 *   Design a Parking Lot
 * 
 * Requirements
 * 
 *   - Able to tell when a spot is available or unavailable.
 *   - Spaces can be regular or handicap.
 *   - Any car can park in a regular space.
 *   - Cars with a handicap identifier can park in handicap spaces.
 *   - If handicap spaces are available, handicap cars should park in handicap spaces first.
 *   - Each parking spot has a size: small, medium and large.
 *   - There are three types of vehicles: small(bikes), medium(cars) and large(trucks).
 *   - A small vehicle can park in a small, medium or large spot.
 *   - A medium vehicle can park in a medium or large spot.
 *   - A large vehicle can park in a large spot.
 *   
 * Assumptions
 * 
 *   - For this exercise:
 *     - No need to cater for vehicles leaving their spots.
 *     - No need to implement each method fully, but you should get a sense of how this 
 *       application would work in code, and how you code design it most effectively.
 *     
 * Output
 *  
 *   Printout of where each car parked.
 *   Printout of Parking Lot status summary.
 *  
 */
		

public class Main {

	public static void main(String[] args) {

		// Create a Parking Lot with 48 spaces
		// - 10 regular large spaces
		// - 24 regular medium spaces
		// - 9 regular small spaces
		// - 5 handicapped medium spaces
		ParkingLot parkingLot = new ParkingLot(48);
		parkingLot.setParkingSpaces(10, false, ParkingSpotSize.LARGE);
		parkingLot.setParkingSpaces(24, false, ParkingSpotSize.MEDIUM);
		parkingLot.setParkingSpaces(9, false, ParkingSpotSize.SMALL);
		parkingLot.setParkingSpaces(5, true, ParkingSpotSize.MEDIUM);

		// Get Parking Spot list
		List<ParkingSpot> psList = parkingLot.getParkingSpots();
		
		// Print summary of parking lot status
		printParkingLotSummary(psList);
		
		// Print summary of parking spots left  
		printParkingSpotsLeft(psList);		

		// Print out status of each Parking Spot
		printParkingSpotList(psList);
		
		// Process vehicle movements
		// - 6 handicapped cars arrive
		// - 8 regular trucks arrive
		// - 3 regular cars arrive
		// - 1 handicapped truck arrives
		// - 3 regular bikes arrive
		// - 1 regular car arrives
		List<Vehicle> vehicles = new ArrayList<>();
		for (int i = 0; i < 6; i++) {vehicles.add(new Car(VehicleSize.MEDIUM,VehiclePassengers.INCLUDESHANDICAPPED));}
		for (int i = 0; i < 8; i++) {vehicles.add(new Truck(VehicleSize.LARGE,VehiclePassengers.DOESNTINCLUDEHANDICAPPED));}
		for (int i = 0; i < 3; i++) {vehicles.add(new Car(VehicleSize.MEDIUM,VehiclePassengers.DOESNTINCLUDEHANDICAPPED));}
		for (int i = 0; i < 1; i++) {vehicles.add(new Truck(VehicleSize.LARGE,VehiclePassengers.INCLUDESHANDICAPPED));}
		for (int i = 0; i < 3; i++) {vehicles.add(new Bike(VehicleSize.SMALL,VehiclePassengers.DOESNTINCLUDEHANDICAPPED));}
		for (int i = 0; i < 1; i++) {vehicles.add(new Car(VehicleSize.MEDIUM,VehiclePassengers.DOESNTINCLUDEHANDICAPPED));}
		

		// Process Vehicle Movements and the allocation of Parking Spots
		processVehicleMovements(parkingLot, psList, vehicles);

		// Print summary of parking lot status
		printParkingLotSummary(psList);
		
		// Print summary of parking spots left  
		printParkingSpotsLeft(psList);
		
		// Print status of each Parking Spot
		printParkingSpotList(psList);		

	}


	public static void processVehicleMovements(ParkingLot parkingLot, List<ParkingSpot> psList,	List<Vehicle> vehicles) {
		System.out.println("Vehicle Movements");
		for (Vehicle vehicle: vehicles) {
			// Print vehicle movement
			String passengerType = vehicle.getIncludesHandicapped()==VehiclePassengers.INCLUDESHANDICAPPED
				? " with Handicapped passengers. " 
				: " with Regular passengers only. ";
			System.out.print("Vehicle " + vehicle.getId() + " is a " + vehicle.getSize() + 
					           " " + vehicle.getType() + passengerType);

			// Allocate parking spot
			int parkingSpotId = parkingLot.allocateParkingSpot(vehicle);

			// Print parking spot allocation
			if (parkingSpotId >= 0) {
				String spotType = psList.get(parkingSpotId).getIsForHandicapped()
					? "Handicapped"
					: "Regular";
				System.out.println("Vehicle " + vehicle.getId() + " parked in spot " + parkingSpotId +
						           " which takes " + psList.get(parkingSpotId).getSize() + " " + spotType + " vehicles.");
			} else {
				System.out.println("Vehicle " + vehicle.getId() + " could not park.");
			}
		}
		System.out.println();
	}


	public static void printParkingLotSummary(List<ParkingSpot> psList) {
		int capacity = psList.size();
		int spotsTaken = 0;
		for (ParkingSpot ps : psList) {
			if (!ps.getIsAvailable()) spotsTaken++;
		}
		int spotsAvailable = capacity - spotsTaken;  
		System.out.println("Parking Lot Status");
		System.out.println("Parking Lot Capacity: " + capacity);
		System.out.println("Parking Spots Taken:  " + spotsTaken);
		System.out.println("Parking Spots Left:   " + spotsAvailable);
		System.out.println();
	}


	public static void printParkingSpotsLeft(List<ParkingSpot> psList) {
		// What parking spots are left?
		int sr = 0;
		int sh = 0;
		int mr = 0;
		int mh = 0;
		int lr = 0;
		int lh = 0;
		for (ParkingSpot ps : psList) {
			if (ps.getIsAvailable()) {
				String spotType = (ps.getIsForHandicapped()==true) 
					? "Handicapped"
					: "Regular";		
				switch (ps.getSize() + " " + spotType) {
					case "Small Regular" :
						sr++;
						break;
					case "Small Handicapped" :
						sh++;
						break;
					case "Medium Regular" :
						mr++;
						break;
					case "Medium Handicapped" :
						mh++;
						break;
					case "Large Regular" :
						lr++;
						break;
					case "Large Handicapped" :
						lh++;
						break;
				}
			}
		}
		System.out.println("Parking Lot Spots Left");
		System.out.println(sr + " Regular Small spots left.");
		System.out.println(mr + " Regular Medium spots left.");
		System.out.println(lr + " Regular Large spots left.");
		System.out.println(sh + " Handicapped Small spots left.");
		System.out.println(mh + " Handicapped Medium spots left.");
		System.out.println(lh + " Handicapped Large spots left.");
//		if (sr > 0) System.out.println("There are " + sr + " Regular Small spots left.");
//		if (mr > 0) System.out.println("There are " + mr + " Regular Medium spots left.");
//		if (lr > 0) System.out.println("There are " + lr + " Regular Large spots left.");
//		if (sh > 0) System.out.println("There are " + sh + " Handicapped Small spots left.");
//		if (mh > 0) System.out.println("There are " + mh + " Handicapped Medium spots left.");
//		if (lh > 0) System.out.println("There are " + lh + " Handicapped Large spots left.");
		System.out.println();
	}

	
	public static void printParkingSpotList(List<ParkingSpot> psList) {
		
		System.out.println("Parking Spot List");
		for (int i = 0; i < psList.size(); i++) {
			ParkingSpot ps = psList.get(i);
			String spotType = (ps.getIsForHandicapped()==true) 
				? "Handicapped"
				: "Regular";
			String spotAvailability = (ps.getIsAvailable()==true) 
				? "Available"
				: "Taken";
			String vehiclePassengerType = "";
			if (ps.getIsAvailable()==false) {
				if (ps.getVehicle().getIncludesHandicapped()==VehiclePassengers.INCLUDESHANDICAPPED) {
					vehiclePassengerType = "with Handicapped passengers";
				} else {
					vehiclePassengerType = "with Regular passengers only";
				}
			}
			String spotTakenBy = (ps.getIsAvailable()==false)
				? " - Veh Id " + ps.getVehicle().getId() +
				  ": a " + ps.getVehicle().getSize() +
				  " " + ps.getVehicle().getType() +
				  " " + vehiclePassengerType
				: "";
			System.out.println("Spot " + ps.getId() + ": " + spotType + " " + ps.getSize() +
					           " - " + spotAvailability + spotTakenBy );
		}
		System.out.println();
	}

}
