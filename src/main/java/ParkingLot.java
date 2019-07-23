package main.java;

import java.util.ArrayList;
import java.util.List;

import main.java.ParkingSpot.ParkingSpotSize;
import main.java.Vehicle.VehiclePassengers;
import main.java.Vehicle.VehicleSize;

public class ParkingLot {
	
	// Instance Variables
	
	List<ParkingSpot> parkingSpots;
	
	// Constructors
	
	public ParkingLot(int totalSpots) {
		parkingSpots = new ArrayList<>();
		for (int i = 0; i < totalSpots; i++) {
			parkingSpots.add(new ParkingSpot());
		}
	}
	
	// Methods

	public int allocateParkingSpot(Vehicle vehicle) {

		int spotAllocatedId = -1;                    // -1 means no space allocated.
		// Process Handicapped vehicles first
		if (vehicle.getIncludesHandicapped() == VehiclePassengers.INCLUDESHANDICAPPED) {
			// Handicap vehicles should park in handicap spaces first
			spotAllocatedId = allocateSpot(vehicle, true, spotAllocatedId);
			if (spotAllocatedId == -1) {
				// Check if any regular spots are available
				spotAllocatedId = allocateSpot(vehicle, false, spotAllocatedId);
			}
		} else {
			// Process Regular vehicles
			spotAllocatedId = allocateSpot(vehicle, false, spotAllocatedId);
		}
		return spotAllocatedId;
	}

	private int allocateSpot(Vehicle vehicle, boolean isForHandicapped, int spotAllocatedId) {
		int i = 0;
		while (spotAllocatedId==-1 && i < parkingSpots.size()) {
			ParkingSpot ps = parkingSpots.get(i);
			if (ps.getIsForHandicapped() == isForHandicapped
			 && vehicleFits(vehicle.getSize(),ps.getSize()) 
			 &&	ps.getIsAvailable()) {
				ps.setIsAvailable(false);
				ps.setVehicle(vehicle);
				spotAllocatedId = ps.getId();
			}
			i++;
		}
		return spotAllocatedId;
	}

	public boolean vehicleFits(VehicleSize vehicleSize, ParkingSpotSize parkingSpotSize) {
	//private boolean vehicleFits(VehicleSize vehicleSize, ParkingSpotSize parkingSpotSize) {
		
		//System.out.println("vehicleFits vehicleSize: " + vehicleSize);
		boolean vehicleFits = false;
		switch (vehicleSize) {
			case LARGE:
				if (parkingSpotSize.equals(ParkingSpotSize.LARGE))
					vehicleFits = true;
				break;
			case MEDIUM:
				if (parkingSpotSize.equals(ParkingSpotSize.MEDIUM)
				 ||	parkingSpotSize.equals(ParkingSpotSize.LARGE))
					vehicleFits = true;
				break;
			case SMALL:
				if (parkingSpotSize.equals(ParkingSpotSize.SMALL)
				 ||	parkingSpotSize.equals(ParkingSpotSize.MEDIUM)
				 ||	parkingSpotSize.equals(ParkingSpotSize.MEDIUM))
					vehicleFits = true;
				break;
			default:
		}
		return vehicleFits;
	}
	
	// Getters and Setters
	
	public void setParkingSpaces(int spots, boolean isForHandicapped, ParkingSpotSize size) {
		int i = 0;
		while (i < parkingSpots.size() && spots > 0) {
			ParkingSpot ps = parkingSpots.get(i); 
			// Use ParkingSpot.size to determine if ParkingSpot has been fully initialised or not.
			if (ps.getSize().equals(ParkingSpotSize.UNDEFINED)) {
				ps.setSize(size);
				// Now check ParkingSpot.isForHandicapped is set correctly. 
				if (ps.getIsForHandicapped() != isForHandicapped) {
					ps.setIsForHandicapped(isForHandicapped);
				}
				spots--;
			}
			i++;
		}
	}

	public List<ParkingSpot> getParkingSpots() {
		return parkingSpots;
	}

	public void setParkingSpots(List<ParkingSpot> parkingSpots) {
		this.parkingSpots = parkingSpots;
	}
	
}
