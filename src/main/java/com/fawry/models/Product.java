package main.java.com.fawry.models;
import java.util.UUID;
import main.java.com.fawry.Interfaces.Expirey;
import main.java.com.fawry.Interfaces.Shipping;

public class Product {
    private final String id;
    private final String name;
    private final double price;
    private int quantity;
    private final Expirey expirey;
    private final Shipping shipping;

    public Product(String name, double price, int quantity, Expirey expirey, Shipping shipping) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirey = expirey;
        this.shipping = shipping;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Expirey getExpirey() {
        return expirey;
    }

    public Shipping getShipping() {
        return shipping;
    }
}
