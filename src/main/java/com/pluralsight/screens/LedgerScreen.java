package com.pluralsight.screens;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LedgerScreen {
    public static void main(String[] args) {

//        viewDeposits();
//        viewPayments();
    }

    static Scanner scanner = new Scanner(System.in);
    public static void ledgerScreen(){
        System.out.println("A) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("REMINDER TO SELF YOU NEED TO DISPLAY BACKWARDS");

        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("a")) {
            viewAll();
        } else if (answer.equalsIgnoreCase("d")) {
            viewDeposits();
        } else if (answer.equalsIgnoreCase("p")) {
            viewPayments();
        } else if (answer.equalsIgnoreCase("r")) {

        } else if (answer.equalsIgnoreCase("h")) {
            HomeScreen.mainScreen();
        }
    }

    public static void viewAll() {
        try{
            FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String input;
            while((input = bufferedReader.readLine()) != null) {
                System.out.println(input);
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
        String input;
        while((input = bufferedReader.readLine()) != null) {
            String[] data = input.split("\\|");
            double tempDouble = Double.parseDouble(data[4]);
            if(tempDouble > 0) {
                System.out.printf("%s %s %s %s %.2f \n", data[0], data[1], data[2], data[3], Double.parseDouble(data[4]));
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
            String input;
            while((input = bufferedReader.readLine()) != null) {
                String[] data = input.split("\\|");
                double tempDouble = Double.parseDouble(data[4]);
                if(tempDouble <= 0) {
                    System.out.printf("%s %s %s %s %.2f \n", data[0], data[1], data[2], data[3], Double.parseDouble(data[4]));
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
