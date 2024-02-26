package academic.spring2024.cse106.oddEvenWithRange;

import java.util.Scanner;

public class App {
    final static String DEFAULT_GROUP = "odd";
    final static int DEFAULT_STARTING_POINT = 1;
    final static int DEFAULT_ENDING_POINT = 100;
    final static int MAX_NUMBERS_IN_A_ROW = 15;
    
    public static void printNumbersInRange(String... options) {
        String group = DEFAULT_GROUP;
        int startingPoint = DEFAULT_STARTING_POINT;
        int endingPoint = DEFAULT_ENDING_POINT;
        int numberOfProvidedOptions = options.length;
        
        for (int i = 0; i < numberOfProvidedOptions && i < 3; i++) {
            switch (i) {
                case 0 -> group = options[i].isBlank() ? DEFAULT_GROUP : options[i];
                case 1 -> startingPoint = options[i].isBlank() ? DEFAULT_STARTING_POINT : Integer.parseInt(options[i]);
                case 2 -> endingPoint = options[i].isBlank() ? DEFAULT_ENDING_POINT : Integer.parseInt(options[i]);
            }
        }
        
        // Validating Inputs
        if (numberOfProvidedOptions != 1 && numberOfProvidedOptions != 3) {
            throw new IllegalArgumentException("Number of arguments should be either 1 or 3");
        }
        if (!group.equalsIgnoreCase("odd") && !group.equalsIgnoreCase("even")) {
            throw new IllegalArgumentException("Group should be either 'odd' or 'even'");
        }
        if (startingPoint > endingPoint) {
            throw new IllegalArgumentException("Start point cannot be greater than end point");
        }
        if (endingPoint < 1 || startingPoint < 1) {
            throw new IllegalArgumentException("Start and end points must be positive integers");
        }
        
        System.out.println();
        System.out.println(group.equalsIgnoreCase("odd") ? "Odd numbers: " : "Even numbers: ");
        int numbersPrinted = 0;
        for(int i = startingPoint; i <= endingPoint; ++i) {
            if ((group.equalsIgnoreCase("even") && i % 2 == 0) || (group.equalsIgnoreCase("odd") && i % 2 != 0)) {
                System.out.print(i + "\t");
                if (numbersPrinted++ > 0 && numbersPrinted % MAX_NUMBERS_IN_A_ROW == 0) {
                    System.out.println();
                }
            }
        }
        System.out.println();
        System.out.println("Total - " + numbersPrinted + " number(s)");
    }

    public static void main(String[] args) {
        Scanner cliInput;
        cliInput = new Scanner(System.in);
        try {
            System.out.print("Enter group (odd or even) [" + DEFAULT_GROUP + "]: ");
            String group = cliInput.nextLine().trim();
            
            System.out.print("Enter starting point [" + DEFAULT_STARTING_POINT + "]: ");
            String from = cliInput.nextLine().trim();
            
            System.out.print("Enter ending point [" + DEFAULT_ENDING_POINT + "]: ");
            String to = cliInput.nextLine().trim();
            
            printNumbersInRange(group, from, to);
        } catch (IllegalArgumentException e) {
            System.err.println("Error (Illegal Argument): " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error (*): " + e.getMessage());
            throw e;
        } finally {
            cliInput.close();
        }
    }
}
