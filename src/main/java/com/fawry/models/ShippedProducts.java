package main.java.com.fawry.models;
import main.java.com.fawry.Interfaces.Shipping;

public class ShippedProducts implements Shipping{
    private String productName;
    private double weight;

    public ShippedProducts(String productName, double weight) {
        this.productName = productName;
        this.weight = weight;

    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    
    public String getProductName() {
        return productName;
    }
    
}
