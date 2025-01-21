package model;

import java.util.ArrayList;

public class Mission {
	private String date; // Date in yyyy-MM-dd format
	private String driverName; // Full name of the driver
	private String warehouseAddress;
	private ArrayList<String> deliveryAddresses;

	public Mission(String date, String driverName, String warehouseAddress, ArrayList<String> deliveryAddresses) {
		this.date = date;
		this.driverName = driverName;
		this.warehouseAddress = warehouseAddress;
		this.deliveryAddresses = deliveryAddresses;
	}

	public String getDate() {
		return date;
	}

	public String getDriverName() {
		return driverName;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public ArrayList<String> getDeliveryAddresses() {
		return deliveryAddresses;
	}

}
