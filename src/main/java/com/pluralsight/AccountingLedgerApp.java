package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.pluralsight.LedgerScreen.ledgerScreen;

public class AccountingLedgerApp {
    public static void main(String[] args) {
        Scanner myscanner = new Scanner(System.in);

        // while loop displays home screen until user decides to exit
        boolean displayMenu = true;
        while (displayMenu) {
            // menu options
            System.out.println("D: Add Deposit Information");
            System.out.println("P: Make Payment (Debit)");
            System.out.println("L: Display Ledger Screen");
            System.out.println("X: Exit the application");
            // get user input
            String userInput = myscanner.nextLine();

            // user decides to add deposit information
            switch (userInput) {
                case "D" -> {
                    Transaction transaction = askUserInfo(myscanner);
                    writeTransactions(transaction);
                }
                // user decides to make payment
                case "P" -> {
                    Transaction transaction1 = askUserInfo(myscanner);
                    writeTransactions(transaction1);
                }
                // user decides to display ledger screen
                case "L" -> ledgerScreen();
                // user decides to exit application
                case "X" -> displayMenu = false; // set to false so while loop ends here
                // user did not enter a valid input. ask user to try again and loop menu again
                default -> System.out.println("Invalid input. Please choose from menu option only.");
            }
        }
    }

    // method to print sub screen menu

    public static void writeTransactions(Transaction transaction) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            // 2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00
            String line = String.format("%s|%s|%s|%s|%.2f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            fileWriter.write(line);
            fileWriter.write("\n");

            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static List<Transaction> readTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("transactions.csv"));
            String line;
            while((line = fileReader.readLine()) != null) {
                //print out each line
                //System.out.println(line);
                // parse each line into a Transaction object so i can later compare amounts
                if (line.isBlank() || line.startsWith("date")) {
                    continue;
                }
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                transactions.add(new Transaction(date, time, description, vendor, amount));
            }

            //close the file when we are done
            fileReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return transactions;
    }

    public static Transaction askUserInfo(Scanner myscanner) {
        System.out.println("Please enter date (yyyy-mm-dd");
        LocalDate date = LocalDate.parse(myscanner.next());
        System.out.println("Please enter time");
        LocalTime time = LocalTime.parse(myscanner.next());
        System.out.println("Please add deposit short description");
        myscanner.nextLine();
        String description = myscanner.nextLine();
        System.out.println("Please add vendor");
        String vendor = myscanner.next();
        System.out.println("Please enter amount");
        double amount = myscanner.nextDouble();
        Transaction transaction1 = new Transaction(date, time, description, vendor, amount);

        return transaction1;

    }

}


