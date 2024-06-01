package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class AddSandwich {
    private LocalDateTime dateTime;
    private String description;
    private double price;

    public AddSandwich(LocalDateTime dateTime, String description, double price) {
        this.dateTime = dateTime;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return dateTime.toLocalDate() + "|" + dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "|" + description + "|" + price;
    }
}
