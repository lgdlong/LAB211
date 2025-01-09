package Dao;

import Model.Registration;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class RegistrationDao {
    private static String filePath = "src\\Resources\\RegistrationList.csv";
    
    public static boolean save(Registration registration) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(registration.toStringWriteToCSV());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
