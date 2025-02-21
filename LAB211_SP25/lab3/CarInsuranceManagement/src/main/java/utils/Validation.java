package utils;

import entity.model.Insurance;
import repository.InsuranceRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

public class Validation {
    private static final String VIETNAM_MOBILE_REGEX =
            "^(086|096|097|098|032|033|034|035|036|037|038|039|" +  // Viettel
                    "088|091|094|081|082|083|084|085|" +  // Vinaphone
                    "089|090|093|070|076|077|078|079|" +  // Mobifone
                    "092|056|058|" +  // Vietnamobile
                    "099|059|" +  // Gmobile
                    "024|028)\\d{7}$";

    private static final String LICENSE_PLATE_REGEX = "5[0-9][A-Z][0-9]{6}";

    private static final Pattern PHONE_PATTERN = Pattern.compile(VIETNAM_MOBILE_REGEX);

    public static boolean isValidId(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }

        return id.length() == 4;
    }

    public static boolean isValidLicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.isBlank()) {
            return false;
        }

        return licensePlate.matches(LICENSE_PLATE_REGEX);
    }

    public static boolean isValidOwnerName(String ownerName) {
        if (ownerName == null) return false;
        if (ownerName.isBlank()) return false;

        return 2 <= ownerName.length() && ownerName.length() <= 25;
    }

    public static boolean isValidOwnerPhone(String ownerPhone) {
        if (ownerPhone == null) return false;

        return PHONE_PATTERN.matcher(ownerPhone).matches();
    }

    public static boolean isValidBrand(String brand) {
        if (brand == null) return false;
        if (brand.isBlank()) return false;

        return 5 <= brand.length() && brand.length() <= 12;
    }

    public static boolean isValidSeatCount(int seatCount) {
        return 4 <= seatCount && seatCount <= 36;
    }

    public static boolean isInsured(InsuranceRepository insuranceRepository, String licensePlate) {
        for (Insurance i : insuranceRepository.getInsuranceRepo()) {
            if (i.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return true;
            }
        }
        return false;
    }
}
