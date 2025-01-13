package Service;

import Dao.MountainDao;
import Dao.RegistrationDao;
import Model.Registration;
import Utils.InputData;
import java.util.List;

import Model.Mountain;
import Model.RegStat;
import Repository.MountainRepo;
import Repository.RegistrationRepo;
import Utils.ValidationData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Service {
    
    // REPOSITORIES
    private static RegistrationRepo regRepo = new RegistrationRepo();
    private static MountainRepo mountainRepo = new MountainRepo();

    // METHODS
    public static void createRegistration() {
        String id = InputData.inputId();
        while (ValidationData.isIdExist(id, regRepo)) {
            System.out.println("Id exists.");
            id = InputData.inputId();
        }
        
        String name = InputData.inputName();
        
        String phoneNumber = InputData.inputPhoneNumber();
        
        String email = InputData.inputEmail();
        
        String mountainCode = InputData.inputMountainCode();
        while (!ValidationData.isMoutainCodeExist(mountainCode, mountainRepo)) {
            System.out.println("Mountain code does not exist.");
            mountainCode = InputData.inputMountainCode();

        }

        Registration registration = new Registration(id, name, phoneNumber, email, mountainCode);

        if (regRepo.add(registration)) {
            System.out.println("Register successful into repository with student ID: " + registration.getId());
        } else {
            System.out.println("Register fail into repository with student ID: " + registration.getId());
        }
    }

    public static void displayAllRegistrations() {
        if (regRepo.getRegistrationList().isEmpty()) {
            System.out.println("No registration found.");
            return;
        }

        printRegistrationHeader();
        displayList(regRepo.getRegistrationList());
    }
    
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
    
    public static void searchByName() {
        String name = InputData.inputName();

        List<Registration> nameReg = new ArrayList<>(); // list to store registration match with name

        // add registrations match name to nameReg 
        for (Registration r : regRepo.getRegistrationList()) {
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

    public static void filterByCampus() {
        String campus = InputData.inputCampus();

        List<Registration> regCampusList = new ArrayList<>();
        
        // filter
        for (Registration reg : regRepo.getRegistrationList()) {
            if (regRepo.getCampusForId(reg.getId()).equalsIgnoreCase(campus)) {
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

                // get moutain infor by code first;
                Mountain m = MountainDao.getMoutainByCode(mountainCodeInt);

                // create a regStat and add in to list;
                RegStat r = new RegStat(mountainCodeString, m.getMountainName(), 1, registration.getFee());
                regStatList.add(r);

            } else { // moutain code exist in statList

                // get index of regStat in regStatList by mountainCode
                int index = regStatList.indexOf(getRegStatByCode(mountainCodeString, regStatList));
                RegStat regStatUpdate = regStatList.get(index);

                regStatUpdate.increTotalRegistrationByOne();
                regStatUpdate.increTotalFeeBy(registration.getFee());
            }
        }

        printStatHeader();
        // sort to print increment with mountain code
        Collections.sort(regStatList,
                (h1, h2) -> {
                    return h1.getMountainCode().compareTo(h2.getMountainCode());
                }
        );
        displayList(regStatList);
    }

    // Generic method
    private static <T> void displayList(List<T> t) {
        for (T _t : t) {
            System.out.println(_t.toString());
        }
    }

    public static void printAllMoutains() {
        if (mountainRepo.getMountainList() == null || mountainRepo.getMountainList().isEmpty()) {
            System.out.println("No mountain found.");
            return;
        }

        
        printMountainHeader();
        Collections.sort(mountainRepo.getMountainList(), 
                Comparator.comparingInt(Mountain::getId));
        displayList(mountainRepo.getMountainList());
    }

    public static void save() {
        RegistrationDao rDao = new RegistrationDao();
        
        // Save and alert.
        if (rDao.save(regRepo)) {
            System.out.println("Update registration file successful.");
        } else {
            System.out.println("Update registration file fail.");
        }
    }

//    public static void updateRegistration(List<Registration> regs) {
//        REG_DAO.
//    }

    public static void printRegistrationHeader() {
        System.out.printf("%-8s | %-20s | %-12s | %-20s | %-4s | %-7s\n", "ID", "Name", "Phone number", "Email", "Mountain Code", "Fee");
    }

    public static void printMountainHeader() {
        System.out.printf("%-4s | %-20s | %-10s | %-50s\n", "Code", "Moutain", "Province", "Description");
    }

    public static void printStatHeader() {
        System.out.println("========== Statistics Registration by Location ===========");
        System.out.printf("%-9s | %-22s | %-10s\n", "Peak Name", "Number of Participants", "Total Cost");
    }

    private static RegStat getRegStatByCode(String code, List<RegStat> regStatList) {
        for (RegStat r : regStatList) {
            if (r.getMountainCode() == null ? code == null : r.getMountainCode().equals(code)) {
                return r;
            }
        }
        return null;
    }

    public static int getIntByStringMountainCode(String moutainCode) {
        // moutain code format: "^MT//d+"
        return Integer.parseInt(moutainCode.replace("MT", ""));
    }
    
}
