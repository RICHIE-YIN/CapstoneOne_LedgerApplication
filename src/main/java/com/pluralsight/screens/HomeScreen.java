package com.pluralsight.screens;

import com.pluralsight.objects.Ledger;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeScreen {

    public static void main(String[] args) {
        String art = """
            /$$$$$                                      /$$$$$$  /$$       /$$$$$$       /$$                       /$$                                                                  
           |__  $$                                     /$$__  $$| $$      |_  $$_/      | $$                      | $$                                                                  
              | $$  /$$$$$$  /$$    /$$ /$$$$$$       | $$  \\__/| $$        | $$        | $$        /$$$$$$   /$$$$$$$  /$$$$$$   /$$$$$$   /$$$$$$         /$$$$$$   /$$$$$$   /$$$$$$ 
              | $$ |____  $$|  $$  /$$/|____  $$      | $$      | $$        | $$        | $$       /$$__  $$ /$$__  $$ /$$__  $$ /$$__  $$ /$$__  $$       |____  $$ /$$__  $$ /$$__  $$
         /$$  | $$  /$$$$$$$ \\  $$/$$/  /$$$$$$$      | $$      | $$        | $$        | $$      | $$$$$$$$| $$  | $$| $$  \\ $$| $$$$$$$$| $$  \\__/        /$$$$$$$| $$  \\ $$| $$  \\ $$
        | $$  | $$ /$$__  $$  \\  $$$/  /$$__  $$      | $$    $$| $$        | $$        | $$      | $$_____/| $$  | $$| $$  | $$| $$_____/| $$             /$$__  $$| $$  | $$| $$  | $$
        |  $$$$$$/|  $$$$$$$   \\  $/  |  $$$$$$$      |  $$$$$$/| $$$$$$$$ /$$$$$$      | $$$$$$$$|  $$$$$$$|  $$$$$$$|  $$$$$$$| $$            |  $$$$$$$| $$$$$$$/| $$$$$$$/
         \\______/  \\_______/    \\_/    \\_______/       \\______/ |________/|______/      |________/ \\_______/ \\_______/ \\____  $$ \\_______/|__/             \\_______/| $$____/ | $$____/ 
                                                                                                                   /$$  \\ $$                                    | $$      | $$      
                                                                                                                  |  $$$$$$/                                    | $$      | $$      
                                                                                                                   \\______/                                     |__/      |__/      
            """;

        System.out.println(art);

        System.out.println("Enter ledger?");
        System.out.println("types 'yes' or 'no'");
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("yes")) {
            mainScreen();
        }

    }
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Ledger> ledger = new ArrayList<>();
    static LocalDate currentDate = LocalDate.now();
    static LocalTime currentTime = LocalTime.now().withNano(0);

    public static void mainScreen() {
        String art2 = """
                   __ __                 ____                  \s
                  / // /__  __ _  ___   / __/__________ ___ ___\s
                 / _  / _ \\/  ' \\/ -_) _\\ \\/ __/ __/ -_) -_) _ \\
                /_//_/\\___/_/_/_/\\__/ /___/\\__/_/  \\__/\\__/_//_/
                
                """;
        System.out.println(art2);
        System.out.println("D) Add Deposit\n"+
                "P) Make Payment\n" +
                "L) Ledger\n" +
                "X) Exit");
        String answer = scanner.nextLine();

        if(answer.equalsIgnoreCase("d")) {
            addDeposit();
        } else if (answer.equalsIgnoreCase("p")) {
            makePayment();
        } else if (answer.equalsIgnoreCase("l")) {
            LedgerScreen.ledgerScreen();
        } else if (answer.equalsIgnoreCase("x")) {

        }
    }

    public static void addDeposit() {
        try{
            System.out.println("Add a description for this transaction:");
            String addDescription = scanner.nextLine();
            System.out.println("Vendor:");
            String addVendor = scanner.nextLine();
            System.out.println("Amount:");
            double addAmount = Double.parseDouble(scanner.nextLine());


            FileWriter fileWriter = new FileWriter("src/main/java/com/pluralsight/data/transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(currentDate + "|" + currentTime + "|" +  addDescription + "|" + addVendor + "|" +  addAmount);
            bufferedWriter.newLine();
            bufferedWriter.close();

            Ledger depositToLedger = new Ledger(addDescription, addVendor, addAmount);
            System.out.printf("%s %s %s %s %.2f \n", depositToLedger.getDate(), depositToLedger.getTime(), depositToLedger.getDescription(), depositToLedger.getVendor(), depositToLedger.getAmount());
            ledger.add(depositToLedger);
            mainScreen();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makePayment() {
        try{System.out.println("Add a description for this transaction:");
            String addDescription = scanner.nextLine();
            System.out.println("Vendor:");
            String addVendor = scanner.nextLine();
            System.out.println("Amount:");
            double addAmount = Double.parseDouble(scanner.nextLine());


            FileWriter fileWriter = new FileWriter("src/main/java/com/pluralsight/data/transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(currentDate + "|" + currentTime + "|" +  addDescription + "|" + addVendor + "|-" +  addAmount);
            bufferedWriter.newLine();
            bufferedWriter.close();

            Ledger makePayment = new Ledger(addDescription, addVendor, addAmount);
            System.out.printf("%s %s %s %s %.2f \n", makePayment.getDate(), makePayment.getTime(), makePayment.getDescription(), makePayment.getVendor(), makePayment.getAmount());
            ledger.add(makePayment);
            mainScreen();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}