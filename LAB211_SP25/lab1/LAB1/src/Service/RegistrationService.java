package Service;

import Utils.InputData;
import java.util.Scanner;

public class RegistrationService {
    public static boolean createRegistration() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your student ID: ");
        String id = InputData.inputId();
        System.out.println("Enter your name: ");
        String name = InputData.inputName();
        System.out.println("Enter your phone number: ");
//        String phoneNumber = InputData
        System.out.println("Enter your email: ");
        String email = InputData.inputEmail();
        System.out.println("Enter mountain code: ");
    }
}
