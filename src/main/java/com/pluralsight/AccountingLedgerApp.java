package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.pluralsight.LedgerScreen.ledgerScreen;

public class AccountingLedgerApp {
    public static void main(String[] args) {
        Scanner myscanner = new Scanner(System.in);
        Screens.welcomeScreen(myscanner);

        // while loop displays home screen until user decides to exit
        boolean displayMenu = true;
        while (displayMenu) {
            // menu options
            System.out.println(Colors.GREEN + "barista@cozycafe:~$ " + Colors.RESET + "menu");
            System.out.println("---------------------------------------------");
            System.out.println("  [D]  deposit    add a deposit");
            System.out.println("  [P]  payment    make a payment (debit)");
            System.out.println("  [L]  ledger     open the ledger");
            System.out.println("  [X]  exit       close the register");
            System.out.println("---------------------------------------------");
            System.out.print(Colors.GREEN + "barista@cozycafe:~$ " + Colors.RESET);
            String userInput = myscanner.nextLine().trim().toUpperCase(); // get user input and convert to upper case

            switch (userInput) {
                case "D" -> {   // user decides to add deposit information
                    Transaction transaction = askUserInfo(myscanner);   // Ask the user for the transaction info
                    writeTransactions(transaction); // save it to transactions.csv
                }
                case "P" -> {   // user decides to make payment
                    Transaction transaction1 = askUserInfo(myscanner);  // build a Transaction object from their answers
                    writeTransactions(transaction1);    // save it to transactions.csv
                }
                case "L" -> ledgerScreen(); // user decides to display ledger screen
                case "X" -> {
                    Screens.endScreen();    // call for exit screen
                    displayMenu = false;    // end the loop here
                }
                default -> System.out.println(Colors.RED + "Invalid input. Please choose from menu option only." + Colors.RESET); // user did not enter a valid input. ask user to try again and loop menu again
            }
        }
    }

    // method to write to csv file
    public static void writeTransactions(Transaction transaction) {
        try {   // second arg true = append mode, to keep existing transactions
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            // 2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00 format
            String line = String.format("%s|%s|%s|%s|%.2f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            fileWriter.write(line); // write line
            fileWriter.write("\n"); // so every transaction has its own line
            fileWriter.close(); // close and save file
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // read csv file and return list of Transaction Objects
    public static List<Transaction> readTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("transactions.csv"));
            String line;
            while((line = fileReader.readLine()) != null) {;
                // parse each line into a Transaction object so i can later compare amounts
                if (line.isBlank() || line.startsWith("date")) {
                    continue;
                }
                String[] parts = line.split("\\|"); // split on |
                // convert each piece of text into the right type
                // date|time|description|vendor|amount
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                transactions.add(new Transaction(date, time, description, vendor, amount)); // build the Transaction and add it to the list
            }
            fileReader.close(); // close the file when we are done
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return transactions;
    }

    // prompts for the description, vendor, and amount, then builds a Transaction
    public static Transaction askUserInfo(Scanner myscanner) {
        // date and time come from computer
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        // prompt and store info from user for descr, vendor, and amount
        System.out.println("Please add deposit short description");
        String description = myscanner.nextLine();
        System.out.println("Please add vendor");
        String vendor = myscanner.nextLine();
        System.out.println("Please enter amount");
        String amount = myscanner.nextLine();
        int amountVal = Integer.parseInt(amount);   // parse string into int for transaction object

        return new Transaction(date, time, description, vendor, amountVal); // build and return the Transaction from the five values

    }
}



