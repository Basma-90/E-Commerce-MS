package main.java.com.fawry.models;
import main.java.com.fawry.Interfaces.Expirey;
import java.time.LocalDate;

public class ExpirableProducts implements Expirey {
    private String productName;
    private LocalDate expiryDate;

    public ExpirableProducts(String productName, LocalDate expiryDate) {
        this.productName=productName;
        this.expiryDate = expiryDate;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public boolean isExpired() {
     return LocalDate.now().isAfter(expiryDate);
    }
    
}
