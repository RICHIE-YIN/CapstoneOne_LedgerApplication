package com.pluralsight.screens;

import com.pluralsight.objects.Ledger;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Ledger> ledger = new ArrayList<>();


    public static void reportsScreen() {
        String art = """
                   ___                    __        ____                  \s
                  / _ \\___ ___  ___  ____/ /____   / __/__________ ___ ___\s
                 / , _/ -_) _ \\/ _ \\/ __/ __(_-<  _\\ \\/ __/ __/ -_) -_) _ \\
                /_/|_|\\__/ .__/\\___/_/  \\__/___/ /___/\\__/_/  \\__/\\__/_//_/
                        /_/                                               \s
                """;
        System.out.println(art);
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
            ledger.clear();
            String input;
            boolean notEmpty = false;

            while((input = bufferedReader.readLine()) != null) {
                String trimmedInput = input.trim();
                String [] data = trimmedInput.split("\\|");
                String [] dateSplit = data[0].split("-");
                int year = Integer.parseInt(dateSplit[0]);
                int month = Integer.parseInt(dateSplit[1]);
                if(year == currentYear && month == currentMonth) {
                    Ledger filteredList = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                    filteredList.setDate(LocalDate.parse(data[0]));
                    filteredList.setTime(LocalTime.parse(data[1]));
                    ledger.add(filteredList);
                    notEmpty = true;
                }
            }
            bufferedReader.close();

            if(notEmpty) {
                for(int i = ledger.size() - 1; i >= 0; i--) {
                    Ledger current = ledger.get(i);
                    System.out.printf("%s %s %s %s %.2f \n", current.getDate(), current.getTime(), current.getDescription(), current.getVendor(), current.getAmount());
                }
            } else {
                System.out.println("Nothing found, sorry.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HomeScreen.mainScreen();
    }

    public static void previousMonth() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        int targetMonth = currentMonth - 1;
        int targetYear = currentYear;
        if(targetMonth == 0) {
            targetMonth = 12;
            targetYear--;
        }

        try{
            FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ledger.clear();
            String input;
            boolean notEmpty = false;

            while((input = bufferedReader.readLine()) != null) {
                String trimmedInput = input.trim();
                String [] data = trimmedInput.split("\\|");
                String [] dateSplit = data[0].split("-");
                int year = Integer.parseInt(dateSplit[0]);
                int month = Integer.parseInt(dateSplit[1]);
                if(year == targetYear && month == targetMonth) {
                    Ledger filteredList = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                    filteredList.setDate(LocalDate.parse(data[0]));
                    filteredList.setTime(LocalTime.parse(data[1]));
                    ledger.add(filteredList);
                    notEmpty = true;
                }

            }
            bufferedReader.close();

            if(notEmpty) {
                for(int i = ledger.size() - 1; i >= 0; i--) {
                    Ledger current = ledger.get(i);
                    System.out.printf("%s %s %s %s %.2f \n", current.getDate(), current.getTime(), current.getDescription(), current.getVendor(), current.getAmount());
                }
            } else {
                System.out.println("Nothing found, sorry.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HomeScreen.mainScreen();
    }

    public static void yearToDate() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        try{
            FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ledger.clear();
            String input;
            boolean notEmpty = false;

            while((input = bufferedReader.readLine()) != null) {
                String trimmedInput = input.trim();
                String [] data = trimmedInput.split("\\|");
                String [] dateSplit = data[0].split("-");
                int year = Integer.parseInt(dateSplit[0]);
                int month = Integer.parseInt(dateSplit[1]);
                if(year == currentYear) {
                    Ledger filteredList = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                    filteredList.setDate(LocalDate.parse(data[0]));
                    filteredList.setTime(LocalTime.parse(data[1]));
                    ledger.add(filteredList);
                    notEmpty = true;
                }
            }
            bufferedReader.close();

            if(notEmpty) {
                for(int i = ledger.size() - 1; i >= 0; i--) {
                    Ledger current = ledger.get(i);
                    System.out.printf("%s %s %s %s %.2f \n", current.getDate(), current.getTime(), current.getDescription(), current.getVendor(), current.getAmount());
                }
            } else {
                System.out.println("Nothing found, sorry.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HomeScreen.mainScreen();
    }

    public static void previousYear() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        try{
            FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ledger.clear();
            String input;
            boolean notEmpty = false;

            while((input = bufferedReader.readLine()) != null) {
                String trimmedInput = input.trim();
                String [] data = trimmedInput.split("\\|");
                String [] dateSplit = data[0].split("-");
                int year = Integer.parseInt(dateSplit[0]);
                int month = Integer.parseInt(dateSplit[1]);
                if(year == currentYear - 1) {
                    Ledger filteredList = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                    filteredList.setDate(LocalDate.parse(data[0]));
                    filteredList.setTime(LocalTime.parse(data[1]));
                    ledger.add(filteredList);
                    notEmpty = true;
                }
            }
            bufferedReader.close();

            if(notEmpty) {
                for(int i = ledger.size() - 1; i >= 0; i--) {
                    Ledger current = ledger.get(i);
                    System.out.printf("%s %s %s %s %.2f \n", current.getDate(), current.getTime(), current.getDescription(), current.getVendor(), current.getAmount());
                }
            } else {
                System.out.println("Nothing found, sorry.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HomeScreen.mainScreen();
    }

    public static void searchByVendor(String vendorName) {
        try{
            FileReader fileReader = new FileReader("src/main/java/com/pluralsight/data/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ledger.clear();
            String input;
            boolean notEmpty = false;

            while((input = bufferedReader.readLine()) != null) {
                String trimmedInput = input.trim();
                String [] data = trimmedInput.split("\\|");
                String vendor = data[3];
                if(vendor.equalsIgnoreCase(vendorName)) {
                    Ledger filteredList = new Ledger(data[2], data[3], Double.parseDouble(data[4]));
                    filteredList.setDate(LocalDate.parse(data[0]));
                    filteredList.setTime(LocalTime.parse(data[1]));
                    ledger.add(filteredList);
                    notEmpty = true;
                }
            }
            bufferedReader.close();

            if(notEmpty) {
                for(int i = ledger.size() - 1; i >= 0; i--) {
                    Ledger current = ledger.get(i);
                    System.out.printf("%s %s %s %s %.2f \n", current.getDate(), current.getTime(), current.getDescription(), current.getVendor(), current.getAmount());
                }
            } else {
                System.out.println("Vendor not found. Try again.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HomeScreen.mainScreen();
    }
}