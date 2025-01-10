package Dao;

import Model.Registration;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDao {
    private static final String filePath = "src\\Resources\\RegistrationList.csv";
    
    public static List<Registration> getAll() {
        List<Registration> registrations = new ArrayList<>();
        
        String line = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // pass a first line
            
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",\\s*"); //split data at each line by comma.
                
                registrations.add(getRegistrationByLine(row)); // put each moutain in to list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return registrations;
    }
    
    public static boolean save(Registration registration) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("\n");
            writer.write(registration.toStringWriteToCSV());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static Registration getRegistrationByLine(String[] data) {
        List<String> s = new ArrayList<>();
        
        // Van de: Neu String[] nha.n va`o ba`ng spit khong co' du? 4 phan tu thi` sao
        
        int lengthOfRowNotEmpty = data.length;
        
        for (int i = 1; i <= 6; i++) {
            if (i <= lengthOfRowNotEmpty) {
                s.add(data[i-1]);
            } else {
                s.add("NULL");
            }
        }

        String id = s.getFirst();
        String name = s.get(1);
        String phoneNumber = s.get(2);
        String email = s.get(3);
        int mountainCode = Integer.parseInt(s.get(4));
        double fee = Double.parseDouble(s.getLast());
        
        return new Registration(id, name, phoneNumber, email, mountainCode, fee);
    }
}
