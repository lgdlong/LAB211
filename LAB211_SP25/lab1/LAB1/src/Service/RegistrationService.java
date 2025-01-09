package Service;

import Dao.MountainDao;
import Dao.RegistrationDao;
import Model.Registration;
import Utils.InputData;
import java.util.List;
import java.util.Scanner;

import Model.Mountain;

public class RegistrationService {
    public static void createRegistration() {
        Scanner sc = new Scanner(System.in);
        
        String id = InputData.inputId();
        
        String name = InputData.inputName();
        
        String phoneNumber = InputData.inputPhoneNumber();
        
        String email = InputData.inputEmail();
        
        System.out.print("Enter the mountain code: ");
        int mountainCode = InputData.inputPositiveInt();
        
        Registration registration = new Registration(id, name, phoneNumber, email, mountainCode);
        
        if (RegistrationDao.save(registration)) {
            System.out.println("Register successful with student ID: " + registration.getId());
        } else {
            System.out.println("Register fail with student ID: " + registration.getId());
        }
    }
    
    public static void printAllMoutains() {
        List<Mountain> mountains = MountainDao.getAll();
        
        for (Mountain m : mountains) {
            System.out.print(m.toString());
        }
    }
}
