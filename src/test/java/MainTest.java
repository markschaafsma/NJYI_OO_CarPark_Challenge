package test.java;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Bike;
import main.java.Car;
import main.java.Main;
import main.java.ParkingLot;
import main.java.ParkingSpot;
import main.java.ParkingSpot.ParkingSpotSize;
import main.java.Truck;
import main.java.Vehicle;
import main.java.Vehicle.VehiclePassengers;
import main.java.Vehicle.VehicleSize;
import main.java.Vehicle.VehicleType;

class MainTest {

	//private ParkingLot parkingLot;
	//private ParkingSpot parkingSpot;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Car park with 48 spaces
		// - 10 large regular spaces
		// - 24 medium regular spaces
		// - 9 small regular spaces
		// - 5 medium handicapped spaces
	}

	@BeforeEach
	public void setUp() throws Exception {
		// Reset static variables
		ParkingSpot.setParkingSpotId(0);
		Vehicle.setVehicleId(0);
	}

	@Test
	void parkingSpotTest() {
		ParkingSpot parkingSpot = new ParkingSpot(false,ParkingSpotSize.SMALL); 
		assertEquals(parkingSpot.getIsForHandicapped(),false);
		assertEquals(parkingSpot.getSize(),ParkingSpotSize.SMALL);
		assertEquals(parkingSpot.getIsAvailable(),true);
		ParkingSpot.setParkingSpotId(0);
	}

	@Test
	void parkingLotTest() {
		ParkingLot parkingLot = new ParkingLot(40);
		assertEquals(parkingLot.getParkingSpots().size(),40);
		parkingLot.setParkingSpaces(2, false, ParkingSpotSize.LARGE);
		parkingLot.setParkingSpaces(4, false, ParkingSpotSize.MEDIUM);
		parkingLot.setParkingSpaces(2, true, ParkingSpotSize.MEDIUM);
		parkingLot.setParkingSpaces(2, false, ParkingSpotSize.SMALL);
		//List<ParkingSpot> psList = parkingLot.getParkingSpots();
		//Main.printParkingSpotList(psList);
		assertEquals(parkingLot.getParkingSpots().get(0).getIsForHandicapped(),false);
		assertEquals(parkingLot.getParkingSpots().get(0).getSize(),ParkingSpotSize.LARGE);
		assertEquals(parkingLot.getParkingSpots().get(0).getIsAvailable(),true);
		assertEquals(parkingLot.getParkingSpots().get(3).getIsForHandicapped(),false);
		assertEquals(parkingLot.getParkingSpots().get(3).getSize(),ParkingSpotSize.MEDIUM);
		assertEquals(parkingLot.getParkingSpots().get(3).getIsAvailable(),true);
		assertEquals(parkingLot.getParkingSpots().get(19).getIsForHandicapped(),false);
		assertEquals(parkingLot.getParkingSpots().get(19).getSize(),ParkingSpotSize.UNDEFINED);
		assertEquals(parkingLot.getParkingSpots().get(19).getIsAvailable(),true);
		// Tear Down
		// - External resources
		// - Everything else is within the scope of this method so is torn down.
		// NB Tear dow normally handled by an @AfterEach tearDown method
	}
	
//  The following test has been commented out since Vehicle has been changed to an abstract class.
//	@Test
//	void vehicleTest() {
//		Vehicle vehicle = new Vehicle(VehicleType.CAR,VehicleSize.MEDIUM,VehiclePassengers.DOESNTINCLUDEHANDICAPPED);
//		assertEquals(vehicle.getType(),VehicleType.CAR);
//		assertEquals(vehicle.getSize(),VehicleSize.MEDIUM);
//		assertEquals(vehicle.getIncludesHandicapped(),VehiclePassengers.DOESNTINCLUDEHANDICAPPED);
//	}	
	
	@Test
	void carTest() {
		Vehicle vehicle = new Car(VehicleSize.MEDIUM,VehiclePassengers.DOESNTINCLUDEHANDICAPPED);
		assertEquals(vehicle.getType(),VehicleType.CAR);
		assertEquals(vehicle.getSize(),VehicleSize.MEDIUM);
		assertEquals(vehicle.getIncludesHandicapped(),VehiclePassengers.DOESNTINCLUDEHANDICAPPED);
	}	
	
	@Test
	void truckTest() {
		Vehicle vehicle = new Truck(VehicleSize.LARGE,VehiclePassengers.INCLUDESHANDICAPPED);
		assertEquals(vehicle.getType(),VehicleType.TRUCK);
		assertEquals(vehicle.getSize(),VehicleSize.LARGE);
		assertEquals(vehicle.getIncludesHandicapped(),VehiclePassengers.INCLUDESHANDICAPPED);
	}	

	@Test
	void bikeTest() {
		Vehicle vehicle = new Bike(VehicleSize.SMALL,VehiclePassengers.DOESNTINCLUDEHANDICAPPED);
		assertEquals(vehicle.getType(),VehicleType.BIKE);
		assertEquals(vehicle.getSize(),VehicleSize.SMALL);
		assertEquals(vehicle.getIncludesHandicapped(),VehiclePassengers.DOESNTINCLUDEHANDICAPPED);
	}	

	@Test
	void parkingLotVehicleFitsTest() {
		// This test requires ParkingLot method vehicleFits to be made public.
		ParkingLot parkingLot = new ParkingLot(1);
		parkingLot.setParkingSpaces(1, false, ParkingSpotSize.SMALL);
		assertEquals(parkingLot.vehicleFits(VehicleSize.LARGE, parkingLot.getParkingSpots().get(0).getSize()),false);
		assertEquals(parkingLot.vehicleFits(VehicleSize.SMALL, parkingLot.getParkingSpots().get(0).getSize()),true);
	}	
	
	@Test
	void parkingLotAllocateSpaceTest() {
		ParkingLot parkingLot = new ParkingLot(3);
		parkingLot.setParkingSpaces(1, false, ParkingSpotSize.LARGE);
		parkingLot.setParkingSpaces(1, false, ParkingSpotSize.MEDIUM);
		parkingLot.setParkingSpaces(1, false, ParkingSpotSize.SMALL);
		Vehicle vehicle1 = new Truck(VehicleSize.LARGE,VehiclePassengers.INCLUDESHANDICAPPED);
		//System.out.println(parkingLot.allocateParkingSpot(vehicle1));
		assertEquals(parkingLot.allocateParkingSpot(vehicle1),0);
		Vehicle vehicle2 = new Car(VehicleSize.MEDIUM,VehiclePassengers.INCLUDESHANDICAPPED);
		//System.out.println(parkingLot.allocateParkingSpot(vehicle2));
		assertEquals(parkingLot.allocateParkingSpot(vehicle2),1);
		Vehicle vehicle3 = new Truck(VehicleSize.LARGE,VehiclePassengers.DOESNTINCLUDEHANDICAPPED);
		//System.out.println(parkingLot.allocateParkingSpot(vehicle3));
		assertEquals(parkingLot.allocateParkingSpot(vehicle3),-1);
		// Get Parking Spot list
		List<ParkingSpot> psList = parkingLot.getParkingSpots();
		// Print status of each Parking Spot
		Main.printParkingSpotList(psList);
	}	

}
