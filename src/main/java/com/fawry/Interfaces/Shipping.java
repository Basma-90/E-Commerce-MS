package main.java.com.fawry.Interfaces;

/**
 * Shipping is to determine whether an item is shippable or not and returns its weight for shipping
 */
public interface Shipping {
    boolean requiresShipping();
    double getWeight(); 
}
