package Utils;

import Model.Customer;
import Model.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author LGD
 */
public class InputData {
    
    public static int inputPositiveInt(String prompt) {
        Scanner sc = new Scanner(System.in);
        int num = -1; 
        do {
            try {
                System.out.print(prompt);
                num = Integer.parseInt(sc.nextLine());
                if (!ValidateData.isPositiveInt(num)) {
                    System.out.println("The number must be positive. Please try again.");
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
        } while (!ValidateData.isValidName(name));
        return name;
    }
    
    public static String inputCusCode(String prompt) {
        Scanner sc = new Scanner(System.in);
        String code;
        do {
            System.out.print(prompt);
            code = sc.nextLine().trim();
        } while (!ValidateData.isValidCusCode(code));
        return code;
    }
    
    public static String inputFeastCode(String prompt) {
        Scanner sc = new Scanner(System.in);
        String code;
        do {
            System.out.print(prompt);
            code = sc.nextLine().trim();
        } while (!ValidateData.isValidFeastCode(code));
        return code;
    }
    
    public static String inputPhone(String prompt) {
        Scanner sc = new Scanner(System.in);
        String phoneNumber;
        do {
            System.out.print(prompt);
            phoneNumber = sc.nextLine().trim();
        } while (!ValidateData.isValidPhoneNumber(phoneNumber));

        return phoneNumber;
    }
    
    public static String inputEmail(String prompt) {
        Scanner sc = new Scanner(System.in);
        String email;
        do {
            System.out.print(prompt);
            email = sc.nextLine().trim();
        } while (!ValidateData.isValidEmail(email));

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
    
    public static Customer inputCustomerInfor() {
        // ENter information
        String code = inputCusCode("Enter code: ");
        String name = inputName("Enter name: ");
        String phone = inputPhone("Enter phone: ");
        String email = inputEmail("Enter email: ");
        
        // Create a customer object
        return new Customer(code, name, email, phone);
    }
    
}
