package Service;

import Dao.MountainDao;
import Model.Registration;
import Utils.InputData;
import java.util.List;
import java.util.Scanner;

import Model.Mountain;

public class RegistrationService {
    public static boolean createRegistration() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter your student ID: ");
        String id = InputData.inputId();
        
        System.out.println("Enter your name: ");
        String name = InputData.inputName();
        
        System.out.println("Enter your phone number: ");
        String phoneNumber = InputData.inputPhoneNumber();
        
        System.out.println("Enter your email: ");
        String email = InputData.inputEmail();
        
        System.out.println("Enter mountain code: ");
        int mountainCode = InputData.inputPositiveInt();
        
        Registration registration = new Registration(id, name, phoneNumber, email, mountainCode);
        
        if (true) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void printAllMoutains() {
        List<Mountain> mountains = MountainDao.getAll();
        
        for (Mountain m : mountains) {
            System.out.print(m.toString());
        }
    }
}
