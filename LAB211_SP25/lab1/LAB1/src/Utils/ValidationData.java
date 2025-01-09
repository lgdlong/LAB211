package Utils;

public class ValidationData {
    private static final String ID_REGEX = "^(SE|HE|DE|QE|CE)\\d{6}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_NUMBER_REGEX = "^(03|05|07|08|09)\\d{8}$";

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
}
