package main.java.com.fawry.models;

public class Customer{
    private final String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() { return balance; }
    public void deductBalance(double amount) {
        if (balance < amount) throw new RuntimeException("Insufficient balance");
        balance -= amount;
    }
    public String getName() { return name; }
}