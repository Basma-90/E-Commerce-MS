package main.java.com.fawry.tests;
import main.java.com.fawry.models.Product;
import main.java.com.fawry.models.ShippedProducts;
import main.java.com.fawry.models.NotShippedProducts;
import main.java.com.fawry.models.ExpirableProducts;
import main.java.com.fawry.models.NotExpirableProducts;
import main.java.com.fawry.constants.AppConstants;
import main.java.com.fawry.models.*;
import main.java.com.fawry.services.*;
import java.time.LocalDate;

public class CheckoutTests {
    public static void main(String[] args) {
        SetUpTestEnvironment();
    }

    public static void SetUpTestEnvironment() {
        try {
            System.out.println("\n[TEST] Normal Checkout with Mixed Products");

            Product productA = new Product("Cheese", 100.00, 5,
                    new ExpirableProducts("Cheese",LocalDate.of(2025, 12, 31)),
                    new ShippedProducts("Cheese",0.2));

            Product productB = new Product("TV", 1000.00, 2,
                    new NotExpirableProducts(),
                    new ShippedProducts("TV",5.0));

            Product productC = new Product("Mobile Scratch Card", 50.00, 10,
                    new NotExpirableProducts(),
                    new NotShippedProducts());

            Customer customer = new Customer("Ahmed", 2000);

            Cart cart = new Cart();
            cart.addItem(productA, 2);
            cart.addItem(productB, 1);
            cart.addItem(productC, 3);

 
            CheckoutService.checkout(customer, cart);

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        }

        testEmptyCart();
        testExpiredProduct();
        testInsufficientBalance();
        testOutOfStock();
        testNoShipping();
        testExactBalance();
    }

    public static void testEmptyCart() {
        System.out.println("\n[TEST] Empty Cart");
        try {
            Customer customer = new Customer("EmptyCartUser", 100);
            Cart cart = new Cart();
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }

    public static void testExpiredProduct() {
        System.out.println("\n[TEST] Expired Product");
        try {
            Product expired = new Product("SourCandy", 70.00, 5,
                    new ExpirableProducts("SourCandy",LocalDate.of(2020, 1, 1)),
                    new ShippedProducts("SourCandy",0.5));
            Customer customer = new Customer("SourCandy", 500);
            Cart cart = new Cart();
            cart.addItem(expired, 1);
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }

    public static void testInsufficientBalance() {
        System.out.println("\n[TEST] Insufficient Balance");
        try {
            Product tv = new Product("TV", 1000.00, 1,
                    new NotExpirableProducts(),
                    new ShippedProducts("TV",5.0));
            Customer customer = new Customer("LowBalanceUser", 500);
            Cart cart = new Cart();
            cart.addItem(tv, 1);
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }

    public static void testOutOfStock() {
        System.out.println("\n[TEST] Out of Stock");
        try {
            Product card = new Product("Card", 50.00, 1,
                    new NotExpirableProducts(),
                    new NotShippedProducts());
            Customer customer = new Customer("StockUser", 500);
            Cart cart = new Cart();
            cart.addItem(card, 2);
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }

    public static void testNoShipping() {
        System.out.println("\n[TEST] No Shipping Items");
        try {
            Product card = new Product("Card", 50.00, 3,
                    new NotExpirableProducts(),
                    new NotShippedProducts());
            Customer customer = new Customer("NoShipUser", 300);
            Cart cart = new Cart();
            cart.addItem(card, 3);
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public static void testExactBalance() {
        System.out.println("\n[TEST] Exact Balance");
        try {
            Product cheese = new Product("Cheese", 100.00, 2,
                    new ExpirableProducts("Cheese",LocalDate.of(2026, 1, 1)),
                    new ShippedProducts("Cheese",0.2));
            double total = (2 * 100.00) + AppConstants.SHIPPING_FEE;
            Customer customer = new Customer("ExactBalanceUser", total);
            Cart cart = new Cart();
            cart.addItem(cheese, 2);
            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
