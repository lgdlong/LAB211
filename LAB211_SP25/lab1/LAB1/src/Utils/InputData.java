package Utils;

import java.util.Scanner;

public class InputData {

    //--------------------------------------------
    public static boolean inputYesNo() {
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            choice = sc.nextLine().trim();
        } while (!ValidationData.isYesOrNo(choice));

        return choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes");
    }

    //--------------------------------------------
    public static String inputCampus() {
        Scanner scanner = new Scanner(System.in);
        String campus;
        
        // Loop until the user inputs a valid campus code
        while (true) {
            System.out.print("Enter a campus code (CE, DE, HE, SE, QE): ");
            campus = scanner.nextLine().trim();

            // Check if the entered campus is valid
            if (ValidationData.isValidCampus(campus)) {
                System.out.println("Valid campus: " + campus);
                break; // Exit the loop if the campus is valid
            } else {
                System.out.println("Invalid campus code. Please try again.");
            }
        }
        return campus;
    }

    //--------------------------------------------
    public static int inputChoice(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());

                if (choice < min || choice > max) {
                    System.out.println("Choice must be between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (choice < min || choice > max);

        return choice;
    }

    //--------------------------------------------
    public static String inputId() {
        Scanner sc = new Scanner(System.in);

        String id;

        do {
            System.out.print("Enter student ID: ");
            id = sc.nextLine().trim();
        } while (!ValidationData.isValidId(id));

        return id;
    }

    //--------------------------------------------
    public static String inputName() {
        Scanner sc = new Scanner(System.in);
        String name;
        do {
            System.out.print("Enter name: ");
            name = sc.nextLine().trim();
        } while (!ValidationData.isValidName(name, false));

        return name;
    }

    //--------------------------------------------
    public static String inputEmail() {
        Scanner sc = new Scanner(System.in);
        String email;
        do {
            System.out.print("Enter email: ");
            email = sc.nextLine().trim();
        } while (!ValidationData.isValidEmail(email, false));

        return email;
    }

    //--------------------------------------------
    public static String inputPhoneNumber() {
        Scanner sc = new Scanner(System.in);
        String phoneNumber;
        do {
            System.out.print("Enter phone number: ");
            phoneNumber = sc.nextLine().trim();
        } while (!ValidationData.isValidPhoneNumber(phoneNumber, false));

        return phoneNumber;
    }

    //--------------------------------------------
    public static String inputMountainCode() {
        Scanner sc = new Scanner(System.in);
        String mountainCode;
        do {
            System.out.print("Enter mountain code: ");
            mountainCode = sc.nextLine().trim();
        } while (!ValidationData.isValidMountainCode(mountainCode, false));

        return mountainCode;
    }

    //--------------------------------------------
    public static String inputName_Blank() {
        Scanner sc = new Scanner(System.in);
        String name;
        do {
            System.out.print("Enter name: ");
            name = sc.nextLine().trim();
        } while (!ValidationData.isValidName(name, true));

        return name;
    }

    //--------------------------------------------
    public static String inputEmail_Blank() {
        Scanner sc = new Scanner(System.in);
        String email;
        do {
            System.out.print("Enter email: ");
            email = sc.nextLine().trim();
        } while (!ValidationData.isValidEmail(email, true));

        return email;
    }

    //--------------------------------------------
    public static String inputPhoneNumber_Blank() {
        Scanner sc = new Scanner(System.in);
        String phoneNumber;
        do {
            System.out.print("Enter phone number: ");
            phoneNumber = sc.nextLine().trim();
        } while (!ValidationData.isValidPhoneNumber(phoneNumber, true));

        return phoneNumber;
    }

    //--------------------------------------------
    public static String inputMountainCode_Blank() {
        Scanner sc = new Scanner(System.in);
        String mountainCode;
        do {
            System.out.print("Enter mountain code: ");
            mountainCode = sc.nextLine().trim();
        } while (!ValidationData.isValidMountainCode(mountainCode, true));

        return mountainCode;
    }


}
