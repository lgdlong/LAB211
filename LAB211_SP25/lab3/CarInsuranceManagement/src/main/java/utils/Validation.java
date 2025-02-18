package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validation {
    private static final String VIETNAM_MOBILE_REGEX =
            "^(086|096|097|098|032|033|034|035|036|037|038|039|" +  // Viettel
                    "088|091|094|081|082|083|084|085|" +  // Vinaphone
                    "089|090|093|070|076|077|078|079|" +  // Mobifone
                    "092|056|058|" +  // Vietnamobile
                    "099|059|" +  // Gmobile
                    "024|028)\\d{7}$";



    private static final String LICENSE_PLATE_REGEX = "5[0-9]{1}[A-Z]{1}[1-9]{1}[0-9]{5}";
    private static final Pattern PHONE_PATTERN = Pattern.compile(VIETNAM_MOBILE_REGEX);

    public static boolean isValidLicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.isBlank()) {
            return false;
        }



        boolean firstTwoDigitsRequire = 50 <= Integer.parseInt(licensePlate.substring(0, 2)) && Integer.parseInt(licensePlate.substring(0, 2)) <= 59;

//        if ()

        return licensePlate.matches("[0-9]{2}[A-Z]{1}-[0-9]{3}.[0-9]{2}");
//        return licensePlate.matches("[0-9]{2}[A-Z]{1}-[0-9]{3}.[0-9]{2}");
    }

    public static boolean isValidOwnerName(String ownerName) {
        if (ownerName == null) {
            return false;
        }

        if (ownerName.isBlank()) {
            return false;
        }

        return 2 <= ownerName.length() && ownerName.length() <= 25;
    }

    public static boolean isValidOwnerPhone(String ownerPhone) {
        if (ownerPhone == null) {
            return false;
        }

        return PHONE_PATTERN.matcher(ownerPhone).matches();
    }

    public static boolean isValidBrand(String brand) {
        if (brand == null) {
            return false;
        }

        if (brand.isBlank()) {
            return false;
        }

        return 5 <= brand.length() && brand.length() <= 12;
    }

    public static boolean isValidPrice(int price) {
        return price > 999;
    }

    public static boolean isValidRegistrationDate(String registrationDate) {
        if (registrationDate == null) {
            return false;
        }
        try {
            LocalDate date = LocalDate.parse(registrationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidSeatCount(String seatCount) {
        return seatCount.matches("[1-9][0-9]*");
    }
}
