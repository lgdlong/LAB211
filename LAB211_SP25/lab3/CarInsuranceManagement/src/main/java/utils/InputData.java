package utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputData {
    private static final Scanner scanner = new Scanner(System.in); // Reuse Scanner

    public static int inputInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim()); // Return valid input immediately
            } catch (NumberFormatException e) {
                System.out.println(Color.ANSI_YELLOW + "⚠ Invalid input. Please enter a valid number." + Color.ANSI_RESET);
            }
        }
    }

    public static String inputYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")) {
                return choice; // Return valid choice
            }
            System.out.println(Color.ANSI_YELLOW + "⚠ Please enter Y or N." + Color.ANSI_RESET);
        }
    }

    public static int inputChoice(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    return choice; // Return valid choice
                }
                System.out.println(Color.ANSI_YELLOW + "⚠ Please enter a number from " + min + " to " + max + "." + Color.ANSI_RESET);
            } catch (NumberFormatException e) {
                System.out.println(Color.ANSI_YELLOW + "⚠ Invalid input. Please enter a valid number." + Color.ANSI_RESET);
            }
        }
    }

    public static String inputId(String prompt) {
        while (true) {
            System.out.print(prompt);
            String id = scanner.nextLine().trim();
            if (Validation.isValidId(id)) {
                return id;
            }
            System.out.println(Color.ANSI_YELLOW + "⚠ ID must be in the right format (e.g., 0004)." + Color.ANSI_RESET);
        }
    }

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
            if (ownerName.isEmpty()) {
                return "blank"; // Return "blank" if the input is empty
            }
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
            if (input.isEmpty()) {
                return -1L; // Return 0 if the input is empty
            }
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

    public static LocalDate inputDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String registrationDate = scanner.nextLine().trim();
            if (registrationDate.isEmpty()) {
                return null; // Return null if the input is empty
            }
            try {
                return LocalDate.parse(registrationDate); // Return valid input immediately
            } catch (DateTimeParseException e) {
                System.out.println(Color.ANSI_YELLOW + "⚠ Invalid date format. Please enter the date in the correct format (e.g., YYYY-MM-DD)." + Color.ANSI_RESET);
            }
            System.out.println(Color.ANSI_YELLOW + "⚠ Registration date must be in the past." + Color.ANSI_RESET);
        }
    }

    public static int inputSeatCount(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return -1; // its -1 if blank
            }
            try {
                int seatCount = Integer.parseInt(input);
                if (Validation.isValidSeatCount(seatCount)) {
                    return seatCount;
                }
                System.out.println(Color.ANSI_YELLOW + "⚠ Seat count must be a positive number." + Color.ANSI_RESET);
            } catch (NumberFormatException e) {
                System.out.println(Color.ANSI_YELLOW + "⚠ Invalid input. Please enter a valid number." + Color.ANSI_RESET);
            }
        }
    }
}
