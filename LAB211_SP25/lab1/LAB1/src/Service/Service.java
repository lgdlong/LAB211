package Service;

import Dao.I_RegistrationDao;
import Dao.RegistrationDao;
import Model.Registration;
import Utils.InputData;
import Model.Mountain;
import Model.RegStat;
import Repository.MountainRepo;
import Repository.RegistrationRepo;
import Utils.ValidationData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Service {
    
    // REPOSITORIES
    private static final RegistrationRepo regRepo = new RegistrationRepo();
    private static final MountainRepo mountainRepo = new MountainRepo();
    
    //-----------------------------------------------
    public static void createRegistration() {
        String id = InputData.inputId();
        while (ValidationData.isIdExist(id, regRepo)) {
            System.out.println("Id exists. Please enter a different ID.");
            id = InputData.inputId();
        }
    
        String name = InputData.inputName();
        String phoneNumber = InputData.inputPhoneNumber();
        String email = InputData.inputEmail();
        String mountainCode = InputData.inputMountainCode();
    
        while (!ValidationData.isMoutainCodeExist(mountainCode, mountainRepo)) {
            System.out.println("Mountain code does not exist. Please enter a valid mountain code.");
            mountainCode = InputData.inputMountainCode();
        }

        Registration registration = new Registration(id, name, phoneNumber, email, mountainCode);

        if (regRepo.add(registration)) {
            System.out.println("Registration successful with student ID: " + registration.getId());
        } else {
            System.out.println("Registration failed for student ID: " + registration.getId());
        }
    }

    //-----------------------------------------------
    public static void displayAllRegistrations() {
        List<Registration> registrations = regRepo.getRegistrationList();
        if (registrations.isEmpty()) {
            System.out.println("No registration found.");
            return;
        }

        printRegistrationHeader();
        displayList(registrations);
    }

    //-----------------------------------------------
    public static void deleteRegistration() {
        String id = InputData.inputId();

        if (!ValidationData.isIdExist(id, regRepo)) {
            System.out.println("ID not found.");
            return;
        }

        System.out.print("ARE YOU SURE TO DELETE " + id + " (y/n),(yes/no):");
        boolean ready = InputData.inputYesNo();

        if (!ready) {
            return;
        }

        if (regRepo.delete(id)) {
            System.out.println("Delete registration in repository successful.");
        } else {
            System.out.println("Delete registration in repository fail.");
        }
    }

    //-----------------------------------------------
    public static void searchByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();

        List<Registration> nameReg = new ArrayList<>(); // list to store registration match with name

        // add registrations match name to nameReg
        for (Registration r : regRepo.getRegistrationList()) {
            if (r.getName().toLowerCase().contains(name.toLowerCase())) {
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

    //-----------------------------------------------
    public static void filterByCampus() {
        String campus = InputData.inputCampus();

        List<Registration> regCampusList = new ArrayList<>();

        // filter
        for (Registration reg : regRepo.getRegistrationList()) {
            if (reg.getCampusForId(reg.getId()).equalsIgnoreCase(campus)) {
                regCampusList.add(reg);
            }
        }

        // print
        if (regCampusList.isEmpty()) {
            System.out.println("No campus registration.");
        } else {
            printRegistrationHeader();
            displayList(regCampusList);
        }
    }

    /**
     * duyet qua tung regis neu moutainCode chua xuat hien trong regStatList: -
     * lay regis.moutainCode, regis.fee gan vao object RegStat va totalRegis++;
     * neu da xuat hien: - lay RegStat ra trong list -> fee += object.Fee,
     * totalRegis++;
     */
    public static void registrationStatisticsByLocation() {
        List<RegStat> regStatList = new ArrayList<>();

        for (Registration registration : regRepo.getRegistrationList()) {
            String mountainCodeString = registration.getMoutainCode();
            int mountainCodeInt = getIntByStringMountainCode(mountainCodeString);

            if (!ValidationData.isCodeExistInRegStatList(mountainCodeString, regStatList)) {

                // get mountain info by code first;
                Mountain m = mountainRepo.getMoutainByCode(mountainCodeInt);
                // create a regStat and add in to list;
                RegStat r = new RegStat(mountainCodeString, m.getMountainName(), 1, registration.getFee());
                regStatList.add(r);

            } else { // mountain code exist in statList

                // get index of regStat in regStatList by mountainCode
                int index = regStatList.indexOf(getRegStatByCode(mountainCodeString, regStatList));
                RegStat regStatUpdate = regStatList.get(index);

                regStatUpdate.increTotalRegistrationByOne();
                regStatUpdate.increTotalFeeBy(registration.getFee());
            }
        }

        printStatHeader();
        // sort to print increment with mountain code
        regStatList.sort(Comparator.comparing(RegStat::getMountainCode));
        displayList(regStatList);
    }

    //-----------------------------------------------
    private static <T> void displayList(List<T> t) {
        for (T _t : t) {
            System.out.println(_t.toString());
        }
    }

    //-----------------------------------------------
    public static void printAllMountains() {
        if (mountainRepo.getMountainList() == null || mountainRepo.getMountainList().isEmpty()) {
            System.out.println("No mountain found.");
            return;
        }


        printMountainHeader();
        mountainRepo.getMountainList().sort(Comparator.comparingInt(Mountain::getId));
        displayList(mountainRepo.getMountainList());
    }

    //-----------------------------------------------
    public static void save() {
        I_RegistrationDao rDao = new RegistrationDao();

        // Save and alert.
        if (rDao.save(regRepo)) {
            System.out.println("Update registration file successful.");
        } else {
            System.out.println("Update registration file fail.");
        }
    }

    //-----------------------------------------------
    public static void printRegistrationHeader() {
        System.out.printf("%-8s | %-20s | %-12s | %-20s | %-4s | %-7s\n", "ID", "Name", "Phone number", "Email", "Mountain Code", "Fee");
    }

    //-----------------------------------------------
    public static void printMountainHeader() {
        System.out.printf("%-4s | %-20s | %-10s | %-50s\n", "Code", "Mountain", "Province", "Description");
    }

    //-----------------------------------------------
    public static void printStatHeader() {
        System.out.println("========== Statistics Registration by Location ===========");
        System.out.printf("%-9s | %-22s | %-10s\n", "Peak Name", "Number of Participants", "Total Cost");
    }

    //-----------------------------------------------
    private static RegStat getRegStatByCode(String code, List<RegStat> regStatList) {
        for (RegStat r : regStatList) {
            if (r.getMountainCode() == null ? code == null : r.getMountainCode().equals(code)) {
                return r;
            }
        }
        return null;
    }

    //-----------------------------------------------
    public static int getIntByStringMountainCode(String mountainCode) {
        // moutain code format: "^MT//d+"
        return Integer.parseInt(mountainCode.replace("MT", ""));
    }
    
}
