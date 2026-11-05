package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;


    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public String vendor() {
        return vendor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String description) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getVendor() {
        return vendor;
    }








    ///
    /*
    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }
    */
    // header row: same widths as toString() below
    public static String header() {
        return String.format("%-12s %-9s %-26s %-17s %10s%n%-12s %-9s %-26s %-17s %10s",
                "DATE", "TIME", "DESCRIPTION", "VENDOR", "AMOUNT",
                "----------", "--------", "-------------------------", "----------------", "----------");
    }

    @Override
    public String toString() {
        String GREEN = "\u001B[32m", RED = "\u001B[31m", RESET = "\u001B[0m";
        String color = amount >= 0 ? GREEN : RED;
        String timeText = time.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));

        return color + String.format("%-12s %-9s %-26s %-17s %10.2f",
                date, timeText, description, vendor, amount) + RESET;
    }
}
