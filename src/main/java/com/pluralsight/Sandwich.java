package com.pluralsight;
import java.util.Scanner;

public class Sandwich {
    public double Sandwich() {
        Double price = 0.0 ;
        Scanner scanner = new Scanner(System.in);

        // Step 1: Choose bread
        System.out.println("Choose your bread:");
        System.out.println("1. White");
        System.out.println("2. Wheat");
        System.out.println("3. Rye");
        System.out.println("4. Wrap");
        int breadChoice = scanner.nextInt();
        String bread = "";
        switch (breadChoice) {
            case 1: bread = "White"; break;
            case 2: bread = "Wheat"; break;
            case 3: bread = "Rye"; break;
            case 4: bread = "Wrap"; break;
            default:
                System.out.println("Invalid choice.");
                return -1;
        }

        // Step 2: Choose size
        System.out.println("Choose your size:");
        System.out.println("1. 4\"\"");
        System.out.println("2. 8\"\"");
        System.out.println("3. 12\"\"");
        int sizeChoice = scanner.nextInt();
        String size = "";
        int sz = 0;
        switch (sizeChoice) {
            case 1: sz=1; size = "4\"\""; price +=5.50; break;
            case 2: sz=2; size = "8\"\""; price +=7.00; break;
            case 3: sz=3; size = "12\"\""; price += 8.50; break;
            default:
                System.out.println("Invalid choice.");
                return -1;
        }

        // Step 3: Choose extra Meat
        scanner.nextLine(); // Consume newline left-over
        System.out.println("Choose extra Meat (comma separated, e.g., Lettuce,Tomato):");
        System.out.println("1. Steak");
        System.out.println("2. Ham");
        System.out.println("3. Salami");
        System.out.println("4. Roast Beef");
        System.out.println("5. Chicken");
        System.out.println("6. Bacon");
        String extras = scanner.nextLine();
        String[] extraChoices = extras.split(",");
        StringBuilder extrasBuilder = new StringBuilder();
        for (String extra : extraChoices) {
            if (extra.equalsIgnoreCase("Steak") ||
                    extra.equalsIgnoreCase("Ham") ||
                    extra.equalsIgnoreCase("Salami") ||
                    extra.equalsIgnoreCase("Roast Beef") ||
                    extra.equalsIgnoreCase("Chicken") ||
                    extra.equalsIgnoreCase("Bacon")) {
                if (extrasBuilder.length() > 0) {
                    extrasBuilder.append(", ");
                }
                extrasBuilder.append(extra.trim());
            }
        }

        // Step 4: Choose extra Cheese
        scanner.nextLine(); // Consume newline left-over
        System.out.println("Choose extra Cheese (comma separated, e.g., Lettuce,Tomato):");
        System.out.println("1. american");
        System.out.println("2. provolone");
        System.out.println("3. cheddar");
        System.out.println("4. swiss");
        String extrascheese = scanner.nextLine();
        String[] extraChoicescheese = extrascheese.split(",");
        StringBuilder extrasBuildercheese = new StringBuilder();
        for (String extra : extraChoicescheese) {
            if (extra.equalsIgnoreCase("american") ||
                    extra.equalsIgnoreCase("provolone") ||
                    extra.equalsIgnoreCase("cheddar") ||
                    extra.equalsIgnoreCase("swiss")) {
                if (extrasBuildercheese.length() > 0) {
                    extrasBuildercheese.append(", ");
                }
                extrasBuildercheese.append(extra.trim());
            }
        }
        boolean eMeated = false;
        boolean eCheeseed = false;

        if (extrasBuilder.length() > 0) {
            System.out.println("Would you like extra Meat? (yes/no)");
            String extraMChoice = scanner.nextLine();
            eMeated = extraMChoice.equalsIgnoreCase("yes");
        }
        if (extrasBuildercheese.length() > 0) {
            System.out.println("Would you like extra Cheese? (yes/no)");
            String extraCChoice = scanner.nextLine();
            eCheeseed = extraCChoice.equalsIgnoreCase("yes");
        }


        // Step 4: Ask if the sandwich should be toasted
        System.out.println("Would you like your sandwich toasted? (yes/no)");
        String toastChoice = scanner.nextLine();
        boolean toasted = toastChoice.equalsIgnoreCase("yes");


        // Step 5: Ask if the sandwich need Extra Meat/Cheese:


        // Summary of the order

        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("You ordered a ").append(size).append(" ").append(bread).append(" sandwich");
        if (extrasBuilder.length() > 0) {
            if (sz == 1){
                price +=1;
            }
            if (sz == 2){
                price +=2;
            }
            if (sz == 3){
                price +=3;
            }
            orderSummary.append(" with ").append(extrasBuilder);
        }
        if (extrasBuildercheese.length() > 0) {
            if (sz == 1){
                price +=0.75;
            }
            if (sz == 2){
                price +=1.5;
            }
            if (sz == 3){
                price +=2.25;
            }
            orderSummary.append(" with ").append(extrasBuildercheese);
        }
        if (toasted) {
            orderSummary.append(", toasted");
        } else {
            orderSummary.append(", not toasted");
        }
        if (eMeated) {
            if (sz == 1){
                price +=0.5;
            }
            if (sz == 2){
                price +=1;
            }
            if (sz == 3){
                price +=1.5;
            }
            orderSummary.append(", extra meat");
        }
        if (eCheeseed) {
            if (sz == 1){
                price +=0.3;
            }
            if (sz == 2){
                price +=0.6;
            }
            if (sz == 3){
                price +=0.9;
            }
            orderSummary.append(", extra cheese");
        }

        // Display order summary
        System.out.println(orderSummary.toString());
        return price;
    }
}
