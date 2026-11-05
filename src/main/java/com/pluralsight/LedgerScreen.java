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
        System.out.println("Ledger Menu options");
        System.out.println("A: Display All Entries");
        System.out.println("D: Deposits - Display only the entries that are deposits into the account");
        System.out.println("P: Payments - Display only the negative entries (or payments");
        System.out.println("R: Reports - A new screen that allows the user to run predefined reports or run custom search");
        System.out.println("H: Home - Go back to Home");
        String userInput = myscanner.nextLine();

        // user wants to display all entries
        switch(userInput) {
            case "A" -> {
                List<Transaction> transactions = readTransactions();
                List<Transaction> entries = new ArrayList<>();
                for (Transaction line : transactions) {
                    entries.add(line);
                }
                printTransactions(entries);

            }
            // display only the entries that are deposits into the account
            case "D" -> {
                //System.out.println("inside D - print deposits");
                List<Transaction> transactions = readTransactions();
                List<Transaction> deposits = new ArrayList<>();
                for (Transaction line : transactions) {
                    //System.out.println("line.getamount is " + line.getAmount());
                    if (line.getAmount() > 0) {
                        //System.out.println("line amount is over 0");
                        deposits.add(line);
                    }
                }
                printTransactions(deposits);
            }
            // Display only the negative entries (or payments")
            case "P" -> {
                List<Transaction> transactions = readTransactions();
                List<Transaction> payments = new ArrayList<>();
                for (Transaction line : transactions) {
                    //System.out.println("line.getamount is " + line.getAmount());
                    if (line.getAmount() < 0) {
                        //System.out.println("line amount is under 0");
                        payments.add(line);
                    }
                }
                printTransactions(payments);

            }
            // A new screen that allows the user to run predefined reports
            case "R" -> {
                boolean reports = true;
                while (reports) {
                    System.out.println("Reports Screen");
                    System.out.println("""
                            ▪ 1) Month To Date
                            ▪ 2) Previous Month
                            ▪ 3) Year To Date
                            ▪ 4) Previous Year
                            ▪ 5) Search by Vendor - prompt the user for the vendor name and
                                 display all entries for that vendor
                            ▪ 0) Back - go back to the Ledger page
                            """);
                    int userNum = myscanner.nextInt();
                    // go back to ledger page
                    switch (userNum) {
                        case 0 -> {
                            reports = false;
                        }
                        // month to date
                        case 1 -> {
                            LocalDate today = LocalDate.now();

                            int year = today.getYear(), month = today.getMonthValue();

                            List<Transaction> transactions = readTransactions();
                            List<Transaction> results = new ArrayList<>();
                            for (Transaction line : transactions) {
                                //System.out.println("line.getamount is " + line.getAmount());
                                if (line.getDate().getYear() == year && line.getDate().getMonthValue() == month) {
                                    results.add(line);
                                }
                            }
                            printTransactions(results);
                        }
                        // previous month

                        // dont need to create a new list just print that specific transaction line

                        case 2 -> {
                            LocalDate today = LocalDate.now();

                            int year = today.getYear(), month = today.getMonthValue();

                            List<Transaction> transactions = readTransactions();
                            List<Transaction> results = new ArrayList<>();
                            for (Transaction line : transactions) {
                                //System.out.println("line.getamount is " + line.getAmount());
                                if (line.getDate().getYear() == year && line.getDate().getMonthValue() == month - 1) {
                                    results.add(line);
                                }

                            }
                            printTransactions(results);

                        }
                        // year to date
                        case 3 -> {
                            LocalDate today = LocalDate.now();

                            int year = today.getYear(), month = today.getMonthValue();

                            List<Transaction> transactions = readTransactions();
                            List<Transaction> results = new ArrayList<>();
                            for (Transaction line : transactions) {
                                //System.out.println("line.getamount is " + line.getAmount());
                                if (line.getDate().getYear() == year) {
                                    results.add(line);
                                }

                            }
                            printTransactions(results);

                        }
                        //previous year
                        case 4 -> {
                            LocalDate today = LocalDate.now();

                            int year = today.getYear(), month = today.getMonthValue();

                            List<Transaction> transactions = readTransactions();
                            List<Transaction> results = new ArrayList<>();
                            for (Transaction line : transactions) {
                                //System.out.println("line.getamount is " + line.getAmount());
                                if (line.getDate().getYear() == year - 1) {
                                    results.add(line);
                                }

                            }
                            printTransactions(results);

                        }
                        // search by vendor
                        case 5 -> {
                            System.out.println("Enter name of vendor: ");
                            myscanner.nextLine();
                            String vendorName = myscanner.nextLine();

                            List<Transaction> transactions = readTransactions();
                            List<Transaction> results = new ArrayList<>();
                            for (Transaction line : transactions) {
                                //System.out.println("line.getamount is " + line.getAmount());
                                if (line.getVendor().equalsIgnoreCase(vendorName)) {
                                    results.add(line);
                                }

                            }
                            printTransactions(results);

                        }
                        case 6 -> {
                    /*
                    6) Custom Search - prompt the user for the following search values.
                        ▪ Start Date
                        ▪ End Date
                        ▪ Description
                        ▪ Vendor
                        ▪ Amount
                        o If the user enters a value for a field you should filter on that field
                        o If the user does not enter a value, you should not filter on that field
                     */
                            System.out.println("Enter Start Date: ");
                            String startDate = myscanner.nextLine();
                            System.out.println("Enter End Date: ");
                            String endDate = myscanner.nextLine();
                            System.out.println("Enter Description: ");
                            String descr = myscanner.nextLine();
                            System.out.println("Enter Vendor: ");
                            String vendorName = myscanner.nextLine();
                            System.out.println("Enter Amount: ");
                            //myscanner.nextLine();
                            double amount = myscanner.nextDouble();

                            List<Transaction> transactions = readTransactions();
                            List<Transaction> results = new ArrayList<>();


                            // maybe add contains lowercase

                            for (Transaction line : transactions) {
                                //System.out.println("line.getamount is " + line.getAmount());
                                if (line.getAmount() == amount) {
                                    results.add(line);
                                }
                                if (line.getAmount() == amount) {
                                    results.add(line);
                                }
                                if (line.getDescription().equalsIgnoreCase(descr)) {
                                    results.add(line);
                                }
                                if (line.getVendor().equalsIgnoreCase(vendorName)) {
                                    results.add(line);
                                }
                                if (line.getAmount() == amount) {
                                    results.add(line);
                                }

                            }
                            printTransactions(results);
                        }
                        // user input is not 0-5
                        default -> {
                            System.out.println("Invalid input.");
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
                System.out.println("Invalid input.");
            }
        }

    }

    public static void printTransactions(List<Transaction> results) {
        for (Transaction line : results) {
            //System.out.println(" about to print ");
            System.out.printf("%s|%s|%s|%s|%.2f%n",
                    line.getDate(), line.getTime(), line.getDescription(),
                    line.getVendor(), line.getAmount());
        }

    }
}
