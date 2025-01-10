package Utils;


import java.util.Scanner;

public class InputData {
    
    // Will check again this method.
    public static int inputPositiveInt() {
        Scanner sc = new Scanner(System.in);
        int num;
        do {
            num = Integer.parseInt(sc.nextLine());
        } while (!ValidationData.isPositiveInt(num));
        
        return num;
    }
    
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
    
    public static String inputId() {
        Scanner sc = new Scanner(System.in);
        
        String id;
        
        do {
            System.out.print("Enter student ID: ");
            id = sc.nextLine().trim();
        } while (!ValidationData.isValidId(id));
        
        return id;
    }
    
    public static String inputName() {
        Scanner sc = new Scanner(System.in);
        String name;
        do {
            System.out.print("Enter name: ");
            name = sc.nextLine().trim();
        } while (!ValidationData.isValidName(name));
        
        return name;
    }
    
    public static String inputEmail() {
        Scanner sc = new Scanner(System.in);
        String email;
        do {
            System.out.print("Enter email: ");
            email = sc.nextLine().trim();
        } while (!ValidationData.isValidEmail(email));
        
        return email;
    }
    
    public static  String inputPhoneNumber() {
        Scanner sc = new Scanner(System.in);
        String phoneNumber;
        do {
            System.out.print("Enter phone number: ");
            phoneNumber = sc.nextLine().trim();
        } while (!ValidationData.isValidPhoneNumber(phoneNumber));
        
        return phoneNumber;
    }
    
}
