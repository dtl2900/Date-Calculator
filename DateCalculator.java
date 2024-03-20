// Dani Torres
// This program will calculate the amount of days until or how long ago a date was
// The date will be chosen by the user in a mm-dd-yyyy format and time will be calculated from the current date

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class DateCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculations = true;
        
        System.out.println("Date Calculator!");
        
        while (continueCalculations) {
            System.out.println("Enter '1' to calculate time until a certain date.");
            System.out.println("Enter '2' to calculate time ago from a certain date.");
            System.out.println("Enter '3' to exit the program.");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    calculateTimeUntil(scanner);
                    break;
                case 2:
                    calculateTimeAgo(scanner);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    continueCalculations = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter either '1', '2', or '3'.");
            }
        }
        
        scanner.close();
    }

    private static void calculateTimeUntil(Scanner scanner) {
        LocalDate currentDate = LocalDate.now();
        System.out.println("Enter the future date (MM-dd-yyyy): ");
        LocalDate userDate = getUserDate(scanner);
        if (userDate.isBefore(currentDate)) {
            System.out.println(formatDate(userDate) + " was " + ChronoUnit.DAYS.between(userDate, currentDate) + " days ago.");
        } else {
            System.out.println(formatDate(userDate) + " is in " + ChronoUnit.DAYS.between(currentDate, userDate) + " days.");
        }
        askForAnotherCalculation(scanner);
    }

    private static void calculateTimeAgo(Scanner scanner) {
        LocalDate currentDate = LocalDate.now();
        System.out.println("Enter the past date (MM-dd-yyyy): ");
        LocalDate userDate = getUserDate(scanner);
        if (userDate.isAfter(currentDate)) {
            System.out.println(formatDate(userDate) + " is in " + ChronoUnit.DAYS.between(currentDate, userDate) + " days.");
        } else {
            System.out.println(formatDate(userDate) + " was " + ChronoUnit.DAYS.between(userDate, currentDate) + " days ago.");
        }
        askForAnotherCalculation(scanner);
    }

    private static LocalDate getUserDate(Scanner scanner) {
        String dateString = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate userDate = LocalDate.parse(dateString, formatter);
        return userDate;
    }
    
    private static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return date.format(formatter);
    }
    
    private static void askForAnotherCalculation(Scanner scanner) {
        System.out.println("Would you like to calculate another date? (Y/N)");
        String option = scanner.next();
        if (!option.equalsIgnoreCase("Y")) {
            System.out.println("Goodbye!");
            System.exit(0);
        }
    }
}