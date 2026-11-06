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
        welcomeScreen(myscanner);

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
            String userInput = myscanner.nextLine(); // get user input

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
                case "L" -> ledgerScreen(); // user decides to display ledger screen
                case "X" -> displayMenu = false; // user decides to exit application. set to false so while loop ends here
                default -> System.out.println(Colors.RED + "Invalid input. Please choose from menu option only." + Colors.RESET); // user did not enter a valid input. ask user to try again and loop menu again
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
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.println("Please add deposit short description");
        String description = myscanner.nextLine();
        System.out.println("Please add vendor");
        String vendor = myscanner.nextLine();
        System.out.println("Please enter amount");
        String amount = myscanner.nextLine();
        int amountVal = Integer.parseInt(amount);
        Transaction transaction1 = new Transaction(date, time, description, vendor, amountVal);

        return transaction1;

    }

    public static void welcomeScreen(Scanner myscanner) {
        List<Transaction> transactions = readTransactions();
        double balance = 0;
        for (Transaction t : transactions) {
            balance += t.getAmount();
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss yyyy");

        System.out.println("""
                 ( (
                  ) )
               ........
               |      |]
               \\      /
                `----'
        """);
        System.out.println(Colors.BOLD + Colors.GREEN + "  [ ACCOUNTING LEDGER ]  " + Colors.RESET);
        //System.out.println(Colors.bar(" ACCOUNTING LEDGER ", 45, Colors.BG_GREEN, Colors.BLACK));
        System.out.println(Colors.bar(" COZYCAFE OS 1.0 ", 45, Colors.BG_GREEN, Colors.BLACK));

        System.out.println("");

        System.out.println("Welcome to CozyCafe OS 1.0 LTS (Espresso/Linux 6.9.0-latte)");
        System.out.println();
        System.out.println(" * Register:     https://cozycafe.example/menu");
        System.out.println(" * Support:      ask the barista");
        System.out.println();
        //System.out.println("  System information as of " + LocalDateTime.now().format(fmt));
        System.out.println("  System information as of " + Colors.CYAN + LocalDateTime.now().format(fmt) + Colors.RESET);
        System.out.println();
        System.out.printf("  Register balance:      $%.2f%n", balance);
        System.out.printf("  Transactions on file:  %d%n", transactions.size());
        System.out.println("  Beans in the hopper:   plenty");
        System.out.println("  Coffee pot status:     BREWING");
        System.out.println();
        //System.out.println("Last login: " + LocalDateTime.now().minusMinutes(7).format(fmt) + " from 192.168.1.42");
        System.out.println("Last login: " + Colors.CYAN + LocalDateTime.now().minusMinutes(7).format(fmt) + " from 192.168.1.42" + Colors.RESET);
        System.out.println();
        System.out.print(Colors.GREEN + "barista@cozycafe:~$ " + Colors.RESET + "[ Press ENTER to open the shop ] ");
        myscanner.nextLine();
        System.out.println();

    }

}



