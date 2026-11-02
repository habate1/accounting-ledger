package com.pluralsight;

import java.util.Scanner;

public class AccountingLedgerApp {
    public static void main(String[] args) {
        Scanner myscanner = new Scanner(System.in);

        boolean displayMenu = true;
        while (displayMenu) {
            System.out.println("D: Add Deposit Information");
            System.out.println("P: Make Payment (Debit)");
            System.out.println("L: Display Ledger Screen");
            System.out.println("X: Exit the application");
            String userInput = myscanner.nextLine();

            if (userInput.equalsIgnoreCase("D")) {

            }
            else if (userInput.equalsIgnoreCase("P")) {

            }
            else if (userInput.equalsIgnoreCase("L")) {

            }
            else if (userInput.equalsIgnoreCase("X")) {
                displayMenu = false;
            }
            else {
                System.out.println("Invalid input. Please choose from menu option only.");
            }
        }


    }
}
