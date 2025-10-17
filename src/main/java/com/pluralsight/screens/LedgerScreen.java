package com.pluralsight.screens;

import com.pluralsight.objects.Ledger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerScreen {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Ledger> ledger = new ArrayList<>();
    static LocalDate currentDate = LocalDate.now();
    static LocalTime currentTime = LocalTime.now().withNano(0);

    public static void ledgerScreen(){
        String art = """
                   __          __               ____                  \s
                  / /  ___ ___/ /__ ____ ____  / __/__________ ___ ___\s
                 / /__/ -_) _  / _ `/ -_) __/ _\\ \\/ __/ __/ -_) -_) _ \\
                /____/\\__/\\_,_/\\_, /\\__/_/   /___/\\__/_/  \\__/\\__/_//_/
                              /___/                                   \s
                """;
        System.out.println(art);
        System.out.println("A) All\n" +
                "D) Deposits\n" +
                "P) Payments\n" +
                "R) Reports");

        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("a")) {
            viewAll();
        } else if (answer.equalsIgnoreCase("d")) {
            viewDeposits();
        } else if (answer.equalsIgnoreCase("p")) {
            viewPayments();
        } else if (answer.equalsIgnoreCase("r")) {
            Reports.reportsScreen();
        } else if (answer.equalsIgnoreCase("h")) {
            HomeScreen.mainScreen();
        }
    }

    public static void viewAll() {
        try{
            FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ledger.clear();

            String input;
            while((input = bufferedReader.readLine()) != null) {
//                System.out.println(input);
                String trimmedInput = input.trim();
                String [] data = trimmedInput.split("\\|");
                Ledger temp = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                temp.setDate(LocalDate.parse(data[0]));
                temp.setTime(LocalTime.parse(data[1]));
                ledger.add(temp);
//                System.out.printf("%s %s %s %s %.2f \n", temp.getDate(), temp.getTime(), temp.getDescription(), temp.getVendor(), temp.getAmount());
            }
            bufferedReader.close();

            for(int i = ledger.size() - 1; i >= 0; i--) {
                Ledger current = ledger.get(i);
                System.out.printf("%s %s %s %s %.2f \n", current.getDate(), current.getTime(), current.getDescription(), current.getVendor(), current.getAmount());
            }
            HomeScreen.mainScreen();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewDeposits() {
        try{
            FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ledger.clear();

            String input;
            while((input = bufferedReader.readLine()) != null) {
                String trimmedInput = input.trim();
                String [] data = trimmedInput.split("\\|");
                Ledger temp = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                temp.setDate(LocalDate.parse(data[0]));
                temp.setTime(LocalTime.parse(data[1]));
                ledger.add(temp);

//            System.out.printf("%s %s %s %s %.2f \n", temp.getDate(), temp.getTime(), temp.getDescription(), temp.getVendor(), temp.getAmount());
            }
            bufferedReader.close();

            for(int i = ledger.size() - 1; i >= 0; i--) {
                Ledger current = ledger.get(i);
                if(current.getAmount() > 0) {
                    System.out.printf("%s %s %s %s %.2f \n", current.getDate(), current.getTime(), current.getDescription(), current.getVendor(), current.getAmount());
                }
            }
            HomeScreen.mainScreen();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewPayments() {
        try{
            FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ledger.clear();

            String input;
            while((input = bufferedReader.readLine()) != null) {
                String trimmedInput = input.trim();
                String [] data = trimmedInput.split("\\|");
                Ledger temp = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                temp.setDate(LocalDate.parse(data[0]));
                temp.setTime(LocalTime.parse(data[1]));
                ledger.add(temp);
            }
            bufferedReader.close();

            for(int i = ledger.size() - 1; i >= 0; i--) {
                Ledger current = ledger.get(i);
                if(current.getAmount() <= 0) {
                    System.out.printf("%s %s %s %s %.2f \n", current.getDate(), current.getTime(), current.getDescription(), current.getVendor(), current.getAmount());
                }
            }
            HomeScreen.mainScreen();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}