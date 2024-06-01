package com.pluralsight;
import java.util.Scanner;

public class SandwichOrder {
    public SandwichOrder() {
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
                return;
        }

        // Step 2: Choose size
        System.out.println("Choose your size:");
        System.out.println("1. 4\"\"");
        System.out.println("2. 8\"\"");
        System.out.println("3. 12\"\"");
        int sizeChoice = scanner.nextInt();
        String size = "";
        switch (sizeChoice) {
            case 1: size = "4\"\""; break;
            case 2: size = "8\"\""; break;
            case 3: size = "12\"\""; break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        // Step 3: Choose extra ingredients
        scanner.nextLine(); // Consume newline left-over
        System.out.println("Choose extra ingredients (comma separated, e.g., Lettuce,Tomato):");
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
            if (extra.equalsIgnoreCase("Lettuce") ||
                    extra.equalsIgnoreCase("Tomato") ||
                    extra.equalsIgnoreCase("Cheese") ||
                    extra.equalsIgnoreCase("Ham")) {
                if (extrasBuilder.length() > 0) {
                    extrasBuilder.append(", ");
                }
                extrasBuilder.append(extra.trim());
            }
        }

        // Step 4: Ask if the sandwich should be toasted
        System.out.println("Would you like your sandwich toasted? (yes/no)");
        String toastChoice = scanner.nextLine();
        boolean toasted = toastChoice.equalsIgnoreCase("yes");

        // Summary of the order

        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("You ordered a ").append(size).append(" ").append(bread).append(" sandwich");
        if (extrasBuilder.length() > 0) {
            orderSummary.append(" with ").append(extrasBuilder);
        }
        if (toasted) {
            orderSummary.append(", toasted");
        } else {
            orderSummary.append(", not toasted");
        }

        // Display order summary
        System.out.println(orderSummary.toString());

    }
}
