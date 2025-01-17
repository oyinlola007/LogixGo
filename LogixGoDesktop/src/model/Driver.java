package model;

public class Driver {
	private int id;
	private String truckRegistration;
	private int truckCapacity;

	// Constructor
	public Driver(int id, String truckRegistration, int truckCapacity) {
		this.id = id;
		this.truckRegistration = truckRegistration;
		this.truckCapacity = truckCapacity;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTruckRegistration() {
		return truckRegistration;
	}

	public void setTruckRegistration(String truckRegistration) {
		this.truckRegistration = truckRegistration;
	}

	public int getTruckCapacity() {
		return truckCapacity;
	}

	public void setTruckCapacity(int truckCapacity) {
		this.truckCapacity = truckCapacity;
	}

	@Override
	public String toString() {
		return "Driver{" + "id=" + id + ", truckRegistration='" + truckRegistration + '\'' + ", truckCapacity="
				+ truckCapacity + '}';
	}
}
