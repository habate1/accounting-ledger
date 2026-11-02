package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private LocalDate date;
    private LocalDateTime time;
    private String description;
    private double amount;


    public Transaction(LocalDate date, LocalDateTime time, String description, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
