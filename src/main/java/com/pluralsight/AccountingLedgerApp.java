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
        if (userInput.equalsIgnoreCase("A")) {
            List<Transaction> transactions = readTransactions();
            List<Transaction> deposits = new ArrayList<>();
            for (Transaction line : transactions) {
                deposits.add(line);
            }
            for (Transaction line : deposits) {
                System.out.printf("%s|%s|%s|%s|%.2f%n",
                        line.getDate(), line.getTime(), line.getDescription(),
                        line.getVendor(), line.getAmount());
            }

        }
        // display only the entries that are deposits into the account
        else if (userInput.equalsIgnoreCase("D")) {
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
            for (Transaction line : deposits) {
                //System.out.println(" about to print deposits");
                System.out.printf("%s|%s|%s|%s|%.2f%n",
                        line.getDate(), line.getTime(), line.getDescription(),
                        line.getVendor(), line.getAmount());
            }
        }
        // Display only the negative entries (or payments")
        else if (userInput.equalsIgnoreCase("P")) {
            List<Transaction> transactions = readTransactions();
            List<Transaction> deposits = new ArrayList<>();
            for (Transaction line : transactions) {
                //System.out.println("line.getamount is " + line.getAmount());
                if (line.getAmount() < 0) {
                    //System.out.println("line amount is under 0");
                    deposits.add(line);
                }

            }
            for (Transaction line : deposits) {
                //System.out.println(" about to print payments");
                System.out.printf("%s|%s|%s|%s|%.2f%n",
                        line.getDate(), line.getTime(), line.getDescription(),
                        line.getVendor(), line.getAmount());
            }

        }
        // A new screen that allows the user to run predefined reports
        else if (userInput.equalsIgnoreCase("R")) {
            /*
            R) Reports - A new screen that allows the user to run pre-defined reports or
                to run a custom search
                ▪ 1) Month To Date
                ▪ 2) Previous Month
                ▪ 3) Year To Date
                ▪ 4) Previous Year
                ▪ 5) Search by Vendor - prompt the user for the vendor name and
                display all entries for that vendor
                ▪ 0) Back - go back to the Ledger page
             */
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
                if (userNum == 0) {
                    reports = false;
                }
                // month to date
                else if (userNum == 1) {
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
                    for (Transaction line : results) {
                        //System.out.println(" about to print ");
                        System.out.printf("%s|%s|%s|%s|%.2f%n",
                                line.getDate(), line.getTime(), line.getDescription(),
                                line.getVendor(), line.getAmount());
                    }
                }
                // previous month
                else if (userNum == 2) {
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
                    for (Transaction line : results) {
                        //System.out.println(" about to print ");
                        System.out.printf("%s|%s|%s|%s|%.2f%n",
                                line.getDate(), line.getTime(), line.getDescription(),
                                line.getVendor(), line.getAmount());
                    }

                }
                // year to date
                else if (userNum == 3) {
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
                    for (Transaction line : results) {
                        //System.out.println(" about to print ");
                        System.out.printf("%s|%s|%s|%s|%.2f%n",
                                line.getDate(), line.getTime(), line.getDescription(),
                                line.getVendor(), line.getAmount());
                    }

                }
                //previous year
                else if (userNum == 4) {
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
                    for (Transaction line : results) {
                        //System.out.println(" about to print ");
                        System.out.printf("%s|%s|%s|%s|%.2f%n",
                                line.getDate(), line.getTime(), line.getDescription(),
                                line.getVendor(), line.getAmount());
                    }

                }
                // search by vendor
                else if (userNum == 5) {
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
                    for (Transaction line : results) {
                        //System.out.println(" about to print ");
                        System.out.printf("%s|%s|%s|%s|%.2f%n",
                                line.getDate(), line.getTime(), line.getDescription(),
                                line.getVendor(), line.getAmount());
                    }

                }
                // user input is not 0-5
                else {
                    System.out.println("Invalid input.");
                }
            }
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


