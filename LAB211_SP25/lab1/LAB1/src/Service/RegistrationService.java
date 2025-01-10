package Service;

import Dao.MountainDao;
import Dao.RegistrationDao;
import Model.Registration;
import Utils.InputData;
import java.util.List;
import java.util.Scanner;

import Model.Mountain;
import Model.RegStat;
import Utils.ValidationData;
import java.util.ArrayList;

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
    
    public static void searchByName() {
        String name = InputData.inputName();
        
        List<Registration> reg = RegistrationDao.getAll();
        
        List<Registration> nameReg = new ArrayList<>(); // list to store registration match with name
        
        // add registrations match name to nameReg 
        for (Registration r : reg) {
            if (name.equalsIgnoreCase(r.getName())) {
                nameReg.add(r);
            }
        }
        
        if (nameReg.isEmpty()) {
            System.out.println("No registration found with name: " + name);
            return;
        }
        
        printRegistrationHeader(); // print header
        displayList(nameReg);
    }
    
    /**
     * duyet qua tung regis
     * neu moutainCode chua xuat hien trong regStatList:
     * - lay regis.moutainCode, regis.fee gan vao object RegStat va totalRegis++;
     * neu da xuat hien:
     * - lay RegStat ra trong list -> fee += object.Fee, totalRegis++;
     * 
     * 
     * 
     */
    public static void registrationStatisticsByLocation() {
        List<RegStat> regStatList = new ArrayList<>();
        List<Registration> registrations = RegistrationDao.getAll();
        
        
        for (Registration registration : registrations) {
            int moutainCode = registration.getMoutainCode();
            
            if (!ValidationData.isCodeExistInRegStatList(moutainCode, regStatList)) {
                
                // get moutain infor by code first;
                Mountain m = MountainDao.getMoutainByCode(moutainCode);
                
                // create a regStat and add in to list;
                RegStat r = new RegStat(moutainCode, m.getMountainName(), 1, registration.getFee());
                regStatList.add(r);
                
            } else { // moutain code exist in statList
                
                // get index of regStat in regStatList by mountainCode
                int index = regStatList.indexOf(getRegStatByCode(moutainCode, regStatList));
                RegStat regStatUpdate = regStatList.get(index);
                
                regStatUpdate.increTotalRegistrationByOne();
                regStatUpdate.increTotalFeeBy(registration.getFee());
            }
        }
        
        printStatHeader();
        displayList(regStatList);
    }
    
    // Generic method
    public static <T> void displayList(List<T> t) {
       for (T _t : t) {
           System.out.println(_t.toString());
       } 
    }
    
    public static void displayAllRegistrations() {
        List<Registration> registrations = RegistrationDao.getAll();
        
        if (registrations.isEmpty()) {
            System.out.println("No registration found.");
            return;
        } 
        
        printRegistrationHeader();
        for (Registration r : registrations) {
            System.out.println(r.toString());
        }
    }
    
    public static void printAllMoutains() {
        List<Mountain> mountains = MountainDao.getAll();
        
        printMountainHeader();
        for (Mountain m : mountains) {
            System.out.println(m.toString());
        }
    }
    
    public static void printRegistrationHeader() {
        System.out.printf("%-8s | %-20s | %-12s | %-20s | %-4s | %-7s\n", "ID", "Name", "Phone number", "Email", "Mountain Code", "Fee");
    }
    
    public static void printMountainHeader() {
        System.out.printf("%-4s | %-20s | %-10s | %-4s\n", "Code", "Moutain", "Province", "Description");
    }
    
    public static void printStatHeader() {
        System.out.println("========== Statistics Registration by Location ===========");
        System.out.printf("%-20s | %-22s | %-10s\n", "Peak Name", "Number of Participants", "Total Code");
    }
    
    public static RegStat getRegStatByCode(int code, List<RegStat> regStatList) {
        for (RegStat r : regStatList) {
            if (r.getMountainCode() == code) {
                return r;
            }
        }
        return null;
    }
}
