package Utils;

import Model.RegStat;
import Repository.MountainRepo;
import Repository.RegistrationRepo;
import Service.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidationData {

    private static final String ID_REGEX = "^(SE|HE|DE|QE|CE)\\d{6}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_NUMBER_REGEX = "^(03|05|07|08|09)\\d{8}$";
    private static final String MOUNTAIN_CODE_REGEX = "^MT\\d+";
    
    public static boolean isValidCampus(String campus) {
        // Define the array of valid campuses
        String[] campuses = {"CE", "DE", "HE", "SE", "QE"};

        // Check if the input campus exists in the array
        for (String validCampus : campuses) {
            if (validCampus.equalsIgnoreCase(campus)) {
                return true; // Found a match
            }
        }
        return false; // No match found
    }

    
    public static boolean isYesOrNo(String choice) {
        return choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n") ||
                choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("no");
    }
    
    public static boolean isValidName(String name) {
        if (name.isBlank() || name.isEmpty()) {
            return false;
        }

        if (2 <= name.length() && name.length() <= 20) {
            return true;
        } else {
            System.out.println("Invalid name!");
            return false;
        }
    }

    public static boolean isValidId(String id) {
        if (id.isBlank() || id.isEmpty()) {
            return false;
        }

        if (id.matches(ID_REGEX)) {
            return true;
        } else {
            System.out.println("Invalid ID!");
            return false;
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("^\\d{10}$")) {
            System.out.println("Invalid phone number. Only digits are allowed.");
        }

        if (phoneNumber.isBlank() || phoneNumber.isEmpty()) {
            return false;
        }

        if (phoneNumber.length() != 10) {
            System.out.println("Phone must has 10 digits.");
            return false;
        }

        if (phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            return true;
        } else {
            System.out.println("Invalid phone number. It must start with one of the following prefixes: 03 (Viettel), 05 (Vietnamobile), 07 (Mobifone), 08 (Vinaphone-VNPT), 09 (Older numbers).");
            return false;
        }
    }

    public static boolean isValidMountainCode(String mountainCode) {
        if (mountainCode.length() < 4) {
            System.out.println("Invalid mountain code. Length must >= 4.");
            return false;
        }
        
        if (mountainCode.isBlank() || mountainCode.isEmpty()) {
            return false;
        }
        
        if (!mountainCode.matches(MOUNTAIN_CODE_REGEX)) {
            System.out.println("Invalid mountain code. Have to start with MT.");
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean isValidEmail(String email) {
        if (email.isBlank() || email.isEmpty()) {
            return false;
        }
        if (email.matches(EMAIL_REGEX)) {
            return true;
        } else {
            System.out.println("Invalid email!");
            return false;
        }
    }

    public static boolean isPositiveInt(int n) {
        if (n < 0) {
            System.out.println("Not positive integer!");
            return false;
        }
        return true;
    }

    public static boolean isCodeExistInRegStatList(String code, List<RegStat> regStatList) {
        for (RegStat regStat : regStatList) {
            if (regStat.getMountainCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isIdExist(String id, RegistrationRepo regRepo) {
        List<String> idList = regRepo.getIdList();
        if (idList == null) {
            return false;
        }
        
        return idList.contains(id);
    }
    
    public static boolean isMoutainCodeExist(String moutainCode, MountainRepo mountainRepo) {
        int mountainCodeInt = Service.getIntByStringMountainCode(moutainCode);
        
        List<Integer> codeList = mountainRepo.getCodeList();
        
        if (codeList == null) {
            return false;
        }
        
        return codeList.contains(mountainCodeInt);
    }
}
