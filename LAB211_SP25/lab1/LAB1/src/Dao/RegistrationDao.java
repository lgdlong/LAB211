package Dao;

import Model.Registration;
import Repository.RegistrationRepo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDao implements I_RegistrationDao {

    private static final String FILE_PATH = "src\\Resources\\RegistrationList.csv";
    private static final File FILE = new File(FILE_PATH);

    @Override
    public List<Registration> getAll() {
        List<Registration> registrations = new ArrayList<>();

        if (!FILE.exists()) {
            System.out.println("File not found: " + FILE_PATH);
            return registrations;
        }
        
        String line;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine(); // pass header when read

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",\\s*"); //split data at each line by comma.

                registrations.add(getRegistrationByLine(row)); // put each moutain in to list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return registrations;
    }

    @Override
    public boolean save(RegistrationRepo regs) {
        // Check FILE is exist or not. If not, create FILE
        if (!FILE.exists()) {
            try {
                FILE.getParentFile().mkdirs();
                if (FILE.createNewFile()) {
                    System.out.println("File created at: " + FILE.getAbsolutePath());
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        
        // check null and empty
        if (regs == null || regs.getRegistrationList() == null || 
                regs.getRegistrationList().isEmpty()) {
            
            System.out.println("No data to save.");
            return false;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            writer.write("ID, Name, Phone Number, Email, Mountain Code, Fee");
            
            for (Registration reg : regs.getRegistrationList()) {
                writer.newLine();
                writer.write(reg.toCSVString());
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    @Override
    private Registration getRegistrationByLine(String[] data) {
        if (data.length == 0) {
            return null;
        }
        
        String[] filledData = new String[6];
        for (int i = 0; i < 6; i++) {
            filledData[i] = (i < data.length && !data[i].isBlank()) ? data[i] : "NULL";
        }

        String id = filledData[0];
        String name = filledData[1].trim();
        String phoneNumber = filledData[2].trim();
        String email = filledData[3].trim();
        String mountainCode = filledData[4].trim();
        // double should not be null, set to 0.0
        double fee = filledData[5].equals("NULL") ? 0.0 : parseDoubleSafely(filledData[5]);

        return new Registration(id, name, phoneNumber, email, mountainCode, fee);
    }

    // with every input invalid, it will be 0.0
    private double parseDoubleSafely(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

}
