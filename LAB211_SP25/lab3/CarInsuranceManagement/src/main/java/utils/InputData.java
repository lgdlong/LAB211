package utils;

import java.time.LocalDate;
import java.util.Scanner;

public class InputData {
    private static final Scanner scanner = new Scanner(System.in); // Reuse Scanner

    public static String inputLicensePlate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String licensePlate = scanner.nextLine().trim();
            if (Validation.isValidLicensePlate(licensePlate)) {
                return licensePlate; // Return valid input immediately
            }
            System.out.println(Color.ANSI_YELLOW + "⚠ License plate must be in the right format (e.g., 43C12345)." + Color.ANSI_RESET);
        }
    }

    public static String inputOwnerName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String ownerName = scanner.nextLine().trim();
            if (Validation.isValidOwnerName(ownerName)) {
                return ownerName; // Return valid input immediately
            }
            System.out.println(Color.ANSI_YELLOW + "⚠ Owner name must be from 2 to 25 characters." + Color.ANSI_RESET);
        }
    }

    public static String inputOwnerPhone(String prompt) {
        while (true) {
            System.out.print(prompt);
            String ownerPhone = scanner.nextLine().trim();
            if (Validation.isValidOwnerPhone(ownerPhone)) {
                return ownerPhone; // Return valid input immediately
            }
            System.out.println(Color.ANSI_YELLOW + "⚠ Owner phone must be a valid phone number." + Color.ANSI_RESET);
        }
    }

    public static String inputBrand(String prompt) {
        while (true) {
            System.out.print(prompt);
            String brand = scanner.nextLine().trim();
            if (Validation.isValidBrand(brand)) {
                return brand; // Return valid input immediately
            }
            System.out.println(Color.ANSI_YELLOW + "⚠ Brand must be from 5 to 12 characters." + Color.ANSI_RESET);
        }
    }

    public static Long inputPrice(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                long price = Long.parseLong(input);
                if (price >= 999) {
                    return price; // Return valid price
                }
                System.out.println(Color.ANSI_YELLOW + "⚠ Price must be a non-negative number." + Color.ANSI_RESET);
            } catch (NumberFormatException e) {
                System.out.println(Color.ANSI_YELLOW + "⚠ Invalid input. Please enter a valid number." + Color.ANSI_RESET);
            }
        }
    }

    public static LocalDate inputRegistrationDate(String prompt) {
        while (true) {

        }
    }
}
