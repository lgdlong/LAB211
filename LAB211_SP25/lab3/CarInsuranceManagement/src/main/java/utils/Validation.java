package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
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

//    private static final Map<String[], String> REGIONS = Map.of(
//            new String[]{"T1", "T2"}, "Quận 1",
//            new String[]{"B1"}, "Quận 2",
//            new String[]{"F1", "F2"}, "Quận 3",
//            new String[]{"C1"}, "Quận 4",
//            new String[]{"H1"}, "Quận 5",
//            new String[]{"K1", "K2"}, "Quận 6",
//            new String[]{"C2"}, "Quận 7",
//            new String[]{"L1", "L2"}, "Quận 8",
//            new String[]{"X1"}, "Quận 9",
//            new String[]{"U1", "U2"}, "Quận 10",
//            new String[]{"M1", "M2"}, "Quận 11",
//            new String[]{"G1", "G2"}, "Quận 12",
//            new String[]{"D1"}, "Tân Phú",
//            new String[]{"E1"}, "Phú Nhuận",
//            new String[]{"N1", "N2"}, "Bình Tân",
//            new String[]{"P1", "P2"}, "Tân Bình",
//            new String[]{"S1", "S2", "S3"}, "Bình Thạnh",
//            new String[]{"V1", "V2", "V3"}, "Gò Vấp",
//            new String[]{"X2", "X3"}, "Thủ Đức",
//            new String[]{"N2", "N3"}, "Huyện Bình Chánh",
//            new String[]{"Z2"}, "Huyện Cần Giờ",
//            new String[]{"Z1"}, "Huyện Nhà Bè",
//            new String[]{"Y1"}, "Huyện Hóc Môn",
//            new String[]{"Y2", "Y3"}, "Huyện Củ Chi"
//    );

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

    public static boolean isValidRegistrationPlace(String registrationPlace) {
        if (registrationPlace == null) {
            return false;
        }
        try {
            LocalDate date = LocalDate.parse(registrationPlace, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidSeatCount(String seatCount) {
        return seatCount.matches("[1-9][0-9]*");
    }
}
