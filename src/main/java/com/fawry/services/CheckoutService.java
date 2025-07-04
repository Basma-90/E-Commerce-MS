package main.java.com.fawry.services;
import main.java.com.fawry.constants.AppConstants;
import main.java.com.fawry.models.Customer;
import main.java.com.fawry.models.Cart;
import main.java.com.fawry.models.CartItem;
import main.java.com.fawry.models.Product;
import main.java.com.fawry.Interfaces.Shippable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    public static void checkout(Customer customer, Cart cart) {
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        List<Shippable> shippables = new ArrayList<>();
        double subtotal = calculateSubtotalAndPrepareShippables(cart, shippables);

        double shipping = shippables.isEmpty() ? 0 : AppConstants.SHIPPING_FEE;
        double total = subtotal + shipping;

        customer.deductBalance(total);

        printShipmentNotice(shippables);
        printReceipt(cart, subtotal, shipping, total, customer);

        cart.getItems().clear();
    }

    private static double calculateSubtotalAndPrepareShippables(Cart cart, List<Shippable> shippables) {
        double subtotal = 0.0;

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            if (product.getQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
            }
            if (product.getExpirey().isExpired()) {
                throw new IllegalArgumentException("Product " + product.getName() + " is expired.");
            }

            subtotal += product.getPrice() * quantity;

            if (product.getShipping().requiresShipping()) {
                for (int i = 0; i < quantity; i++) {
                    shippables.add(new Shippable() {
                        public String getName() { return product.getName(); }
                        public double getWeight() { return product.getShipping().getWeight(); }
                    });
                }
            }

            product.setQuantity(product.getQuantity() - quantity);
        }

        return subtotal;
    }

    private static void printShipmentNotice(List<Shippable> shippables) {
        if (shippables.isEmpty()) return;

        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (Shippable item : shippables) {
            System.out.println("1x " + item.getName());
            int weightInGrams = (int) (item.getWeight() * 1000);
            System.out.println(weightInGrams + "g");
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight " + totalWeight + "kg\n");
    }

    private static void printReceipt(Cart cart, double subtotal, double shipping, double total, Customer customer) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int qty = item.getQuantity();
            double lineTotal = product.getPrice() * qty;
            System.out.println(qty + "x " + product.getName());
            System.out.println((int) lineTotal);
        }
        System.out.println("----------------------");
        System.out.println("Subtotal         " + (int) subtotal);
        System.out.println("Shipping         " + (int) shipping);
        System.out.println("Amount           " + (int) total);
        System.out.println("Customer Balance " + customer.getBalance());
        System.out.println("END.\n");
    }
} 
