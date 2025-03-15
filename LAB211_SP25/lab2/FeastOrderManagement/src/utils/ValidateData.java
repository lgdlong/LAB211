package utils;

/**
 *
 * @author LGD
 */
public class ValidateData {
    
    private static final String CUS_CODE_REGEX = "^([CGK])\\d{4}$";
    private static final String FEAST_CODE_REGEX = "^PW\\d+";
    private static final String PHONE_REGEX = "^(03|05|07|08|09)\\d{8}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    
    public static boolean isPositiveInt(int n) {
        if (n < 0) {
            System.out.println("Not positive integer!");
            return false;
        }
        return true;
    }
    
    public static boolean isValidName(String name, boolean blank) {
        // can be blank if true
        if (blank && name.isBlank()) {
            return true;
        }
        
        if (name.length() < 2 || name.length() > 25 || name.isBlank()) {
            System.out.println("Invalid name!");
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean isValidCusCode(String code, boolean blank) {
        // can be blank if true
        if (blank && code.isBlank()) {
            return true;
        }
        
        if (code.length() != 5) {
            System.out.println("CODE LENGTH MUST BE 4!");
            return false;
        }
        
        if (code.matches(CUS_CODE_REGEX)) {
            return true;
        } else {
            System.out.println("Invalid code.");
            return false;
        }
    }
    
    public static boolean isValidFeastCode(String code, boolean blank) {
        // can be blank if true
        if (blank && code.isBlank()) {
            return true;
        }
        
        if (code.length() < 5) {
            return false;
        }
        if (code.matches(FEAST_CODE_REGEX)) {
            return true;
        } else {
            System.out.println("Invalid code.");
            return false;
        }
    }
    
    public static boolean isValidPhoneNumber(String phoneNumber, boolean blank) {
        // can be blank if true
        if (blank && phoneNumber.isBlank()) {
            return true;
        }
        
        if (!phoneNumber.matches("^\\d{10}$")) {
            System.out.println("Invalid phone number. Only digits are allowed.");
        }

        if (phoneNumber.length() != 10) {
            System.out.println("Phone must has 10 digits.");
            return false;
        }

        if (phoneNumber.matches(PHONE_REGEX)) {
            return true;
        } else {
            System.out.println("Invalid phone number. It must start with one of the following prefixes: 03 (Viettel), 05 (Vietnamobile), 07 (Mobifone), 08 (Vinaphone-VNPT), 09 (Older numbers).");
            return false;
        }
    }
    
    public static boolean isValidEmail(String email, boolean blank) {
        if (blank && email.isBlank()) {
            return true;
        }
        
        if (email.matches(EMAIL_REGEX)) {
            return true;
        } else {
            System.out.println("Invalid email!");
            return false;
        }
    }
}
