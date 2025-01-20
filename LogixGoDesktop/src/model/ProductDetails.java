package model;

public class ProductDetails {
    private String productName;
    private Integer productWeight;

    // Constructor
    public ProductDetails(String productName, Integer productWeight) {
        this.productName = productName;
        this.productWeight = productWeight;
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Integer productWeight) {
        this.productWeight = productWeight;
    }
}
