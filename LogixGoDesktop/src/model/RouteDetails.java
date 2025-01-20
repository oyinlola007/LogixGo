package model;

public class RouteDetails {
    private int routeId;
    private String driverName;
    private int availableCapacity;
    private String deliveryDate;
    private int totalWeight; // New attribute

    // Constructor
    public RouteDetails(int routeId, String driverName, int availableCapacity, String deliveryDate, int totalWeight) {
        this.routeId = routeId;
        this.driverName = driverName;
        this.availableCapacity = availableCapacity;
        this.deliveryDate = deliveryDate;
        this.totalWeight = totalWeight;
    }

    // Getters and Setters
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }
}
