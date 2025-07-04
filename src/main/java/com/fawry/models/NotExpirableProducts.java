package main.java.com.fawry.models;
import main.java.com.fawry.Interfaces.Expirey;

public class NotExpirableProducts implements Expirey {
    @Override
    public boolean isExpired() {
        return false;
    }
}
