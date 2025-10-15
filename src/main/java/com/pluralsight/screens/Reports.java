package com.pluralsight.screens;

import com.pluralsight.objects.Ledger;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Reports {

    public static void main(String[] args) {
//        monthToDate();
//        yearToDate();
//        previousMonth();
//        previousYear();
        searchByVendor("test1");

    }

    static Scanner scanner = new Scanner(System.in);
    public static void reportsScreen() {
        System.out.println("1) Month To Date\n" +
                "2) Previous Month\n" +
                "3) Year To Date\n" +
                "4) Previous Year\n" +
                "5) Search by Vendor\n" +
                "0) Back - go back to the Ledger page");
        int answer = Integer.parseInt(scanner.nextLine());
        switch(answer) {
            case 1:
                monthToDate();
                break;
            case 2:
                previousMonth();
                break;
            case 3:
                yearToDate();
                break;
            case 4:
                previousYear();
                break;
            case 5:
                System.out.println("What is the vendor's name?");
                String vendorAnswer = scanner.nextLine();
                searchByVendor(vendorAnswer);
                break;
            case 0:
                LedgerScreen.ledgerScreen();
                break;
        }

    }

    public static void monthToDate() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        try{
        FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String input;

        while((input = bufferedReader.readLine()) != null) {
            String trimmedInput = input.trim();
            String [] data = trimmedInput.split("\\|");
            String [] dateSplit = data[0].split("-");
            int year = Integer.parseInt(dateSplit[0]);
            int month = Integer.parseInt(dateSplit[1]);
            if(year == currentYear && month == currentMonth) {
                Ledger filteredList = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                System.out.println(filteredList);
            }


        }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void previousMonth() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        try{
            FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;

            while((input = bufferedReader.readLine()) != null) {
                String trimmedInput = input.trim();
                String [] data = trimmedInput.split("\\|");
                String [] dateSplit = data[0].split("-");
                int year = Integer.parseInt(dateSplit[0]);
                int month = Integer.parseInt(dateSplit[1]);
                if(year == currentYear && month == currentMonth - 1) {
                    Ledger filteredList = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                    System.out.println(filteredList);
                }


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void yearToDate() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        try{
            FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;

            while((input = bufferedReader.readLine()) != null) {
                String trimmedInput = input.trim();
                String [] data = trimmedInput.split("\\|");
                String [] dateSplit = data[0].split("-");
                int year = Integer.parseInt(dateSplit[0]);
                int month = Integer.parseInt(dateSplit[1]);
                if(year == currentYear) {
                    Ledger filteredList = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                    System.out.println(filteredList);
                }


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void previousYear() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        try{
            FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;

            while((input = bufferedReader.readLine()) != null) {
                String trimmedInput = input.trim();
                String [] data = trimmedInput.split("\\|");
                String [] dateSplit = data[0].split("-");
                int year = Integer.parseInt(dateSplit[0]);
                int month = Integer.parseInt(dateSplit[1]);
                if(year == currentYear - 1) {
                    Ledger filteredList = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                    System.out.println(filteredList);
                }


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void searchByVendor(String vendorName) {
        try{
        FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String input;

        while((input = bufferedReader.readLine()) != null) {
            String trimmedInput = input.trim();
            String [] data = trimmedInput.split("\\|");
            String vendor = data[3];
            if(vendor.equalsIgnoreCase(vendorName)) {
                Ledger filteredList = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                System.out.printf("%s %s %s %s %.2f \n", filteredList.getDate(), filteredList.getTime(), filteredList.getDescription(), filteredList.getVendor(), filteredList.getAmount());
            }
        }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
