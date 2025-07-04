package main.java.com.fawry.services;
import java.util.List;
import main.java.com.fawry.Interfaces.Shippable;

public class ShippingService {
    public static void shipItem(List<Shippable> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Item list cannot be null or empty");
        }
        double totalWeight = 0;
        for (Shippable item : items) {
            System.out.println(item.getName());
            System.out.println((int)(item.getWeight() * 1000) + "g");
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight " + totalWeight + "kg\n");
    }
}
