package com.pluralsight;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.pluralsight.AccountingLedgerApp.readTransactions;

public class LedgerScreen {
    public static void ledgerScreen() {
        Scanner myscanner = new Scanner(System.in);
        // display all menu options then save user input
        System.out.println();
        System.out.println(Colors.GREEN + "barista@cozycafe:~/ledger$ " + Colors.RESET + "menu");
        System.out.println("----------------------------");
        System.out.println("  [A]  all        show all entries");
        System.out.println("  [D]  deposits   show deposits only");
        System.out.println("  [P]  payments   show payments only");
        System.out.println("  [R]  reports    run reports or custom search");
        System.out.println("  [H]  home       back to the main menu");
        System.out.println("----------------------------");
        System.out.print(Colors.GREEN + "barista@cozycafe:~/ledger$ " + Colors.RESET);
        String userInput = myscanner.nextLine().trim().toUpperCase(); // change to upper case for switch case

        // user wants to display all entries
        switch(userInput) {
            case "A" -> {
                // load every transaction from transactions.csv into a list
                List<Transaction> transactions = readTransactions();
                for (Transaction line : transactions) {
                    System.out.println(line); // print every transaction
                }
            }
            // display only the entries that are deposits into the account
            case "D" -> {
                List<Transaction> transactions = readTransactions();
                for (Transaction line : transactions) {
                    if (line.getAmount() > 0) { // deposits only
                        System.out.println(line);
                    }
                }
            }
            // Display only the negative entries
            case "P" -> {
                List<Transaction> transactions = readTransactions();    // load every transaction from transactions.csv into a list
                for (Transaction line : transactions) {
                    if (line.getAmount() < 0) { // payments only
                        System.out.println(line);
                    }
                }
            }
            // A new screen that allows the user to run predefined reports
            case "R" -> {
                boolean reports = true;
                while (reports) {
                    System.out.println("Reports Screen");
                    System.out.println();
                    System.out.println(Colors.GREEN + "barista@cozycafe:~/ledger/reports" + Colors.RESET + "$ menu");
                    System.out.println("----------------------------");
                    System.out.println("  [1]  month to date");
                    System.out.println("  [2]  previous month");
                    System.out.println("  [3]  year to date");
                    System.out.println("  [4]  previous year");
                    System.out.println("  [5]  search by vendor");
                    System.out.println("  [6]  custom search");
                    System.out.println("  [0]  return to ledger");
                    System.out.println("----------------------------");
                    System.out.print(Colors.GREEN + "barista@cozycafe:~/ledger/reports~$ " + Colors.RESET);
                    int userNum = myscanner.nextInt();
                    // go back to ledger page
                    switch (userNum) {
                        case 0 -> reports = false;
                        // month to date
                        case 1 -> {
                            LocalDate today = LocalDate.now();
                            int year = today.getYear(), month = today.getMonthValue();

                            // load every transaction from transactions.csv into a list
                            List<Transaction> transactions = readTransactions();
                            for (Transaction line : transactions) {
                                //System.out.println("line.getamount is " + line.getAmount());
                                if (line.getDate().getYear() == year && line.getDate().getMonthValue() == month) {
                                    System.out.println(line);
                                }
                            }
                        }
                        // previous month
                        case 2 -> {
                            LocalDate today = LocalDate.now();
                            int year = today.getYear(), month = today.getMonthValue();

                            List<Transaction> transactions = readTransactions();
                            for (Transaction line : transactions) {
                                if (line.getDate().getYear() == year && line.getDate().getMonthValue() == month - 1) {
                                    System.out.println(line);
                                }
                            }
                        }
                        // year to date
                        case 3 -> {
                            LocalDate today = LocalDate.now();
                            int year = today.getYear();

                            // load every transaction from transactions.csv into a list
                            List<Transaction> transactions = readTransactions();
                            for (Transaction line : transactions) {
                                if (line.getDate().getYear() == year) {
                                    System.out.println(line);
                                }
                            }
                        }
                        //previous year
                        case 4 -> {
                            LocalDate today = LocalDate.now();
                            int year = today.getYear();

                            List<Transaction> transactions = readTransactions();    // load transaction
                            for (Transaction line : transactions) {
                                if (line.getDate().getYear() == year - 1) {
                                    System.out.println(line);
                                }
                            }
                        }
                        // search by vendor
                        case 5 -> {
                            // needs to accept just enter
                            System.out.println("Enter name of vendor: ");
                            myscanner.nextLine();
                            String vendorName = myscanner.nextLine();

                            List<Transaction> transactions = readTransactions();    // load transaction
                            for (Transaction line : transactions) {
                                if (line.getVendor().equalsIgnoreCase(vendorName)) {
                                    System.out.println(line);
                                }
                            }
                        }
                        case 6 -> { // custom search
                            System.out.println("Enter Start Date: ");
                            String startDate = myscanner.nextLine().trim();
                            System.out.println("Enter End Date: ");
                            String endDate = myscanner.nextLine().trim();
                            System.out.println("Enter Description: ");
                            String descr = myscanner.nextLine().trim();
                            System.out.println("Enter Vendor: ");
                            String vendorName = myscanner.nextLine().trim();
                            System.out.println("Enter Amount: ");
                            //String v = myscanner.nextLine().trim();
                            //myscanner.nextLine();
                            String amountText = myscanner.nextLine().trim();
                            double amount = Double.parseDouble(amountText);

                            LocalDate parseStart = LocalDate.parse(startDate);
                            LocalDate parseEnd = LocalDate.parse(endDate);

                            List<Transaction> transactions = readTransactions();
                            // List<Transaction> results = new ArrayList<>(); // add to results and print results at the end

                            // maybe add contains lowercase
                            for (Transaction line : transactions) {
                                //System.out.println("line.getamount is " + line.getAmount());
                                if (line.getDate() == parseStart) {
                                    System.out.println(line);
                                }
                                if (line.getDate() == parseEnd) {
                                    System.out.println(line);
                                }
                                if (line.getDescription().equalsIgnoreCase(descr)) {
                                    System.out.println(line);
                                }
                                if (line.getVendor().equalsIgnoreCase(vendorName)) {
                                    System.out.println(line);
                                }
                                if (line.getAmount() == amount) {
                                    System.out.println(line);
                                }
                            }
                        }
                        // user input is not 0-5
                        default -> {
                            System.out.println(Colors.RED + "Invalid input." + Colors.RESET);
                        }
                    }
                }
            }
            // use wants to go back home
            case "H" -> {
                // call main method so user can go back to home screen
                AccountingLedgerApp.main(new String[0]);
            }
            // user did not enter correct input
            default -> {
                System.out.println(Colors.RED + "Invalid input." + Colors.RESET);
            }
        }

    }
}
