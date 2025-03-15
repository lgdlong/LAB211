package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author LGD
 */
public class InputData {

    public static String inputString(String prompt) {
        Scanner sc = new Scanner(System.in);
        String id;
        do {
            System.out.print(prompt);
            id = sc.nextLine().trim();
        } while (id.isBlank());
        return id;
    }

    public static String inputString_Blank(String prompt) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print(prompt);
        
        return sc.nextLine().trim();
    }
    
    public static int inputPositiveInt(String prompt) {
        Scanner sc = new Scanner(System.in);
        int num = -1;
        do {
            try {
                System.out.print(prompt);

                String data = sc.nextLine();

                if (data.isBlank()) {
                    return -1;
                } else {
                    num = Integer.parseInt(data.trim());

                    if (!ValidateData.isPositiveInt(num)) {
                        System.out.println("The number must be positive. Please try again.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        } while (!ValidateData.isPositiveInt(num));
        return num;
    }

    public static int inputChoice(String prompt, int min, int max) {
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        try {
            do {
                System.out.print(prompt);
                choice = Integer.parseInt(sc.nextLine().trim());
            } while (min < choice && choice > max);

        } catch (NumberFormatException e) {
            inputChoice(prompt, min, max);
        }

        return choice;
    }

    public static String inputName(String prompt) {
        Scanner sc = new Scanner(System.in);
        String name;
        do {
            System.out.print(prompt);
            name = sc.nextLine().trim();
        } while (!ValidateData.isValidName(name, false));
        return name;
    }

    public static String inputCusCode(String prompt) {
        Scanner sc = new Scanner(System.in);
        String code;
        do {
            System.out.print(prompt);
            code = sc.nextLine().trim();
        } while (!ValidateData.isValidCusCode(code, false));
        return code;
    }

    public static String inputFeastCode(String prompt) {
        Scanner sc = new Scanner(System.in);
        String code;
        do {
            System.out.print(prompt);
            code = sc.nextLine().trim();
        } while (!ValidateData.isValidFeastCode(code, false));
        return code;
    }

    public static String inputPhone(String prompt) {
        Scanner sc = new Scanner(System.in);
        String phoneNumber;
        do {
            System.out.print(prompt);
            phoneNumber = sc.nextLine().trim();
        } while (!ValidateData.isValidPhoneNumber(phoneNumber, false));

        return phoneNumber;
    }

    public static String inputEmail(String prompt) {
        Scanner sc = new Scanner(System.in);
        String email;
        do {
            System.out.print(prompt);
            email = sc.nextLine().trim();
        } while (!ValidateData.isValidEmail(email, false));

        return email;
    }

    public static String inputName_Blank(String prompt) {
        Scanner sc = new Scanner(System.in);
        String name;
        do {
            System.out.print(prompt);
            name = sc.nextLine().trim();
        } while (!ValidateData.isValidName(name, true));
        return name;
    }

    public static String inputFeastCode_Blank(String prompt) {
        Scanner sc = new Scanner(System.in);
        String code;
        do {
            System.out.print(prompt);
            code = sc.nextLine().trim();
        } while (!ValidateData.isValidFeastCode(code, true));
        return code;
    }

    public static String inputPhone_Blank(String prompt) {
        Scanner sc = new Scanner(System.in);
        String phoneNumber;
        do {
            System.out.print(prompt);
            phoneNumber = sc.nextLine().trim();
        } while (!ValidateData.isValidPhoneNumber(phoneNumber, true));

        return phoneNumber;
    }

    public static String inputEmail_Blank(String prompt) {
        Scanner sc = new Scanner(System.in);
        String email;
        do {
            System.out.print(prompt);
            email = sc.nextLine().trim();
        } while (!ValidateData.isValidEmail(email, true));

        return email;
    }

    public static LocalDate inputDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner sc = new Scanner(System.in);
        LocalDate date = null;

        while (true) {
            try {
                System.out.print(prompt);
                String strDate = sc.nextLine().trim();
                date = LocalDate.parse(strDate, formatter);
                break; // Exit the loop if the date is valid
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Enter again (yyyy-MM-dd).");
            }
        }

        return date;
    }
}
