package main.java.com.fawry.models;
import main.java.com.fawry.Interfaces.Shipping;

public class NotShippedProducts implements Shipping {
    @Override
    public boolean requiresShipping() {
        return false;
    }

    @Override
    public double getWeight() {
        return 0;
    }
}
