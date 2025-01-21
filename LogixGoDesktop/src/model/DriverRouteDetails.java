package model;

public class DriverRouteDetails {
    private int routeId;
    private String date;
    private int totalWeight;

    // Constructor
    public DriverRouteDetails(int routeId, String date, int totalWeight) {
        this.routeId = routeId;
        this.date = date;
        this.totalWeight = totalWeight;
    }

    // Getters and Setters
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }
}
