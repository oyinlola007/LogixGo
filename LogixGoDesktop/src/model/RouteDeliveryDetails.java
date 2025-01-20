package model;

public class RouteDeliveryDetails {
    private int routeId;
    private int deliveryId;
    private String addressLine1;
    private String zipCode;
    private String city;
    private int order;

    // Constructor
    public RouteDeliveryDetails(int routeId, int deliveryId, String addressLine1, String zipCode, String city, int order) {
        this.routeId = routeId;
        this.deliveryId = deliveryId;
        this.addressLine1 = addressLine1;
        this.zipCode = zipCode;
        this.city = city;
        this.order = order;
    }

    // Getters and Setters
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
