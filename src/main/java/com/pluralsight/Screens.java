package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Screens {
    public static void welcomeScreen(Scanner myscanner) {
        // load transaction to read and calc balance
        List<Transaction> transactions = AccountingLedgerApp.readTransactions();
        double balance = 0;
        for (Transaction t : transactions) {
            balance += t.getAmount();
        }
        // format for last login
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss yyyy");
        System.out.println("""
                 ( (
                  ) )
               ........
               |      |]
               \\      /
                `----'
        """);
        System.out.println(Colors.BOLD + Colors.GREEN + "  [ ACCOUNTING LEDGER ]  " + Colors.RESET);    // print title
        System.out.println(Colors.bar(" COZYCAFE OS 1.0 ", 45, Colors.BG_GREEN, Colors.BLACK)); // OS bar with cafe name
        System.out.println("");

        // fake SSH welcome message
        System.out.println("Welcome to CozyCafe OS 1.0 LTS (Espresso/Linux 6.9.0-latte)");
        System.out.println();
        System.out.println(" * Register:     https://cozycafe.example/menu");
        System.out.println(" * Support:      ask the barista");
        System.out.println();

        // system info: current date/time in cyan, plus real data from the CSV
        System.out.println("  System information as of " + Colors.CYAN + LocalDateTime.now().format(fmt) + Colors.RESET);
        System.out.println();
        System.out.printf("  Register balance:      $%.2f%n", balance);
        System.out.printf("  Transactions on file:  %d%n", transactions.size());
        System.out.println("  Beans in the hopper:   plenty");
        System.out.println("  Coffee pot status:     BREWING");
        System.out.println();
        System.out.println("Last login: " + Colors.CYAN + LocalDateTime.now().minusMinutes(7).format(fmt) + " from 192.168.1.42" + Colors.RESET);
        System.out.println();

        // shell-style prompt;
        System.out.print(Colors.GREEN + "barista@cozycafe:~$ " + Colors.RESET + "[ Press ENTER to open the shop ] ");
        myscanner.nextLine();
        System.out.println();

    }
    // prints the shutdown steps, a session summary, and the fake SSH logout message
    public static void endScreen() {
        // shutdown steps
        String[] steps = {"Saving ledger", "Closing the register", "Turning off the espresso machine", "Locking the front door"};

    }
}
