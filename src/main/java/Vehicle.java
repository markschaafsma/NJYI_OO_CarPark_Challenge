package main.java;

// Note this class has now been defined as abstract - since it should never be instantiated!!!
// Apart from declaring the class abstract, should any methods or attributes be declared as abstract?

public abstract class Vehicle {
	
	// Enums
	
	public enum VehicleType {
		
		CAR("Car"),
		TRUCK("Truck"),
		BIKE("Bike");
		
		private String name;
		
		VehicleType (String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	public enum VehicleSize {
		
		LARGE("Large"),
		MEDIUM("Medium"),
		SMALL("Small");
		
		private String name;
		
		VehicleSize (String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}	

	public enum VehiclePassengers {
		
		INCLUDESHANDICAPPED(true),
		DOESNTINCLUDEHANDICAPPED(false);
		
		private boolean includesHandicapped;
		
		VehiclePassengers (boolean includesHandicapped) {
			this.includesHandicapped = includesHandicapped;
		}
		
		// Not sure if this is useful/usable ???
		public boolean includesHandicapped() {
			return includesHandicapped;
		}
	}
	
	// Instance Variables
	
	private int id;
	private VehicleType type;                              // e.g. Car, Truck, Bike      
	private VehicleSize size;                              // e.g. Small, Medium, Large
	private VehiclePassengers includesHandicapped;         // e.g. true, false           
	
	// Static Variables
	
	private static int vehicleId = 0;
	
	// Static Constants 
	
	//public static final String SMALL = "Small";          // Not required - Using enums instead 
	//public static final String MEDIUM = "Medium";        // Not required - Using enums instead 
	//public static final String LARGE = "Large";          // Not required - Using enums instead 

	// Constructors
	
	public Vehicle(VehicleType type, VehicleSize size, VehiclePassengers includesHandicapped) {
		this.id = vehicleId++;
		this.type = type;
		this.size = size;
		this.includesHandicapped = includesHandicapped;
	}

	// Getters and Setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}
	public VehicleSize getSize() {
		return this.size;
	}
	public void setSize(VehicleSize size) {
		this.size = size;
	}
	public VehiclePassengers getIncludesHandicapped() {
		return includesHandicapped;
	}
	public void setIncludesHandicapped(VehiclePassengers includesHandicapped) {
		this.includesHandicapped = includesHandicapped;
	}
	public static int getVehicleId() {
		return vehicleId;
	}
	public static void setVehicleId(int vehicleId) {
		Vehicle.vehicleId = vehicleId;
	}
	
}
