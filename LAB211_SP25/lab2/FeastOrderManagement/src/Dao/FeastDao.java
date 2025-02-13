package Dao;

import Model.Feast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LGD
 */
public class FeastDao implements I_ReadDao<Feast>{
    private static final String FILE_PATH = "src" + File.separator + "Resource" + File.separator + "FeastMenu.csv";
    private static final File FILE = new File(FILE_PATH);
    
    @Override
    public List<Feast> getAll() {
        List<Feast> feastList = new ArrayList<>();

        if (!FILE.exists()) {
            System.err.println("Warning: Feast menu file not found at " + FILE_PATH);
            return feastList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            reader.readLine(); // Skip header

            String line;
            while ((line = reader.readLine()) != null) {
                Feast f = getFeastByRow(line.split(","));
                if (f != null) {
                    feastList.add(f);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading feast menu file: " + e.getMessage());
        }

        return feastList;
    }


    private Feast getFeastByRow(String[] data) {
        String code = data[0].trim();
        String name = data[1].trim();
        double price = getDoubleSafety(data[2].trim());
        String ingredient = data[3].trim();
        
        return new Feast(code, name, price, ingredient);
    }
    
    private double getDoubleSafety(String input) { // chatgpt lets go
        try {
            return Double.parseDouble(input); // Attempt to parse the string as a double
        } catch (NumberFormatException e) {
            System.err.println("Invalid price format: " + input);
            return 0.0; // Return 0.0 if parsing fails
        }
    }
}
