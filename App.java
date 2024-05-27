package com.pluralsight;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


public class App {
    private static final String CSV_FILE = "transactions.csv";
    private static final String CSV_HEADER = "date|time|description|vendor|amount";

    private static final String CSV_FILE2 = "orders.csv";
    private static final String CSV_HEADER2 = "date|time|description|amount";

    public static final String delimiter = ",";

    public static void readCSV(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                for(String tempStr : tempArr) {
                    System.out.print(tempStr + " ");
                }
                System.out.println();
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void addDeposit(Scanner scanner) {
        System.out.println("=== Add Deposit ===");
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine().trim();
        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            try {
                System.out.print("Enter amount: ");
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    throw new IllegalArgumentException("Amount must be a positive number.");
                }
                validAmount = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Pleadse enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        LocalDateTime dateTime = LocalDateTime.now();
        Transaction deposit = new Transaction(dateTime, description, vendor, amount);
        saveTransaction(deposit);
        System.out.println("Deposit added successfully.");
    }

    private static void makePayment(Scanner scanner) {
        System.out.println("=== Make Payment (Debit) ===");
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine().trim();
        LocalDateTime dateTime = LocalDateTime.now();
        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            try {
                System.out.print("Enter amount: ");
                amount = Double.parseDouble(scanner.nextLine());
                if (amount >= 0) {
                    throw new IllegalArgumentException("Amount must be a negative number.");
                }
                validAmount = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        Transaction payment = new Transaction(dateTime, description, vendor, amount);
        saveTransaction(payment);
        System.out.println("Payment made successfully.");
    }

    private static void saveTransaction(Transaction transaction) {
        File file = new File(CSV_FILE);
        boolean fileExists = file.exists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE, true))) {
            if (!fileExists) {
                writer.println(CSV_HEADER);
            }
            writer.println(transaction);
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    private static void saveChips(AddChips addchips) {
        File file = new File(CSV_FILE2);
        boolean fileExists = file.exists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE2, true))) {
            if (!fileExists) {
                writer.println(CSV_HEADER2);
            }
            writer.println(addchips);
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    private static void saveDrinks(AddDrinks adddrinks) {
        File file = new File(CSV_FILE2);
        boolean fileExists = file.exists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE2, true))) {
            if (!fileExists) {
                writer.println(CSV_HEADER2);
            }
            writer.println(adddrinks);
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    private static void ledgerMenu(Scanner scanner) {
        // Implement ledger display logic here
        boolean inLedgerMenu = true;

        while (inLedgerMenu) {
            System.out.println("=== Ledger ===");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim().toUpperCase();

            switch (option) {
                case "A":
                    Ledger.displayLedger("All");
                    break;
                case "D":
                    Ledger.displayLedger("Deposits");
                    break;
                case "P":
                    Ledger.displayLedger("Payments");
                    break;
                case "R":
                    Ledger.reportsMenu(scanner);
                    break;
                case "H":
                    inLedgerMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void new_order(Scanner scanner) {
        // Implement ledger display logic here
        boolean inNewOrderMenu = true;
        boolean selectDrinkSize = true;

        File file2 = new File(CSV_FILE2);
        try(PrintWriter pw = new PrintWriter(file2)){

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (inNewOrderMenu) {
            System.out.println("=== Order Screen ===");
            System.out.println("1) Add Sandwhich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim().toUpperCase();

            switch (option) {
                case "1":
                    Ledger.displayLedger("All");
                    break;
                case "2":
                    while (selectDrinkSize) {
                        System.out.println("=== Please Choose a Drink Size ===");
                        System.out.println("1) Add Small");
                        System.out.println("2) Add Medium");
                        System.out.println("3) Add Large");
                        String option2 = scanner.nextLine().trim().toUpperCase();

                        switch (option2){
                            case "1":
                                saveDrinks(new AddDrinks(LocalDateTime.now(), "small drink", 2.00));
                                System.out.println("Small Drink saved successfully to order.");
                                selectDrinkSize = false;
                                //new_order(scanner);
                                break;

                            case "2":
                                saveDrinks(new AddDrinks(LocalDateTime.now(), "medium drink", 2.50));
                                System.out.println("Medium Drink saved successfully to order.");
                                selectDrinkSize = false;
                                //new_order(scanner);
                                break;

                            case "3":
                                saveDrinks(new AddDrinks(LocalDateTime.now(), "large drink", 3.00));
                                System.out.println("Large Drink saved successfully to order.");
                                selectDrinkSize = false;
                                //new_order(scanner);
                                break;

                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    }
                    break;
                case "3":
                    AddChips order = new AddChips(LocalDateTime.now(), "chips", 1.50);
                    saveChips(order);
                    System.out.println("Chips saved successfully to order.");
                    break;
                case "4":
                    readCSV("orders.csv");
                    inNewOrderMenu = false;
                    break;
                case "0":
                    inNewOrderMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    public static void main(String[] args) {
//        System.out.println("hello");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("=== Home Screen ===");
            System.out.println("1) New Order");
//            System.out.println("P) Make Payment (Debit)");
//            System.out.println("L) Ledger");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim().toUpperCase();
            switch (option) {
                case "1":
                    new_order(scanner);
                    break;
                case "P":
                    makePayment(scanner);
                    break;
                case "L":
                    ledgerMenu(scanner);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}


