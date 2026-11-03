package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
            if (userInput.equalsIgnoreCase("D")) {
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
                try {
                    BufferedWriter fileWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
                    fileWriter.write("\n");
                    fileWriter.write(String.valueOf(transaction1));

                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }


            }
            // user decides to make payment
            else if (userInput.equalsIgnoreCase("P")) {
                System.out.println("Please enter date (yyyy-mm-dd");
                LocalDate date = LocalDate.parse(myscanner.next());
                System.out.println("Please enter time");
                LocalTime time = LocalTime.parse(myscanner.next());
                System.out.println("Please add payment short description");
                myscanner.nextLine();
                String description = myscanner.nextLine();
                System.out.println("Please add vendor");
                String vendor = myscanner.next();
                System.out.println("Please enter amount");
                double amount = myscanner.nextDouble();

                Transaction transaction1 = new Transaction(date, time, description, vendor, amount);
                try {
                    BufferedWriter fileWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
                    fileWriter.write("\n");
                    fileWriter.write(String.valueOf(transaction1));

                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }

            }
            // user decides to display ledger screen
            else if (userInput.equalsIgnoreCase("L")) {
                ledgerScreen();
            }
            // user decides to exit application
            else if (userInput.equalsIgnoreCase("X")) {
                // set to false so while loop ends here
                displayMenu = false;
            }
            // user did not enter a valid input. ask user to try again and loop menu again
            else {
                System.out.println("Invalid input. Please choose from menu option only.");
                System.out.println("Back to Home screen");
            }
        }
    }

    public static void ledgerScreen() {
        Scanner myscanner = new Scanner(System.in);
        System.out.println("Ledger Menu options");
        System.out.println("A: Display All Entries");
        System.out.println("D: Deposits - Display only the entries that are deposits into the account");
        System.out.println("P: Payments - Display only the negative entries (or payments");
        System.out.println("R: Reports - A new screen that allows the user to run predefined reports or run custom search");
        System.out.println("H: Home - Go back to Home");
        String userInput = myscanner.nextLine();

        // user wants to display all entries
        if (userInput.equalsIgnoreCase("A")) {

        }
        // display only the entries that are deposits into the account
        else if (userInput.equalsIgnoreCase("D")) {

        }
        // Display only the negative entries (or payments")
        else if (userInput.equalsIgnoreCase("P")) {

        }
        // A new screen that allows the user to run predefined reports
        else if (userInput.equalsIgnoreCase("R")) {

        }
        // use wants to go back home
        else if (userInput.equalsIgnoreCase("H")) {
            // call main method so user can go back to home screen
            AccountingLedgerApp.main(new String[0]);

        }
        // user did not enter correct input
        else {
            System.out.println("Invalid input.");
        }
    }

}
