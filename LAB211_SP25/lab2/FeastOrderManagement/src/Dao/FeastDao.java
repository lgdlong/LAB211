package Dao;

import Model.Feast;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LGD
 */
public class FeastDao implements I_ReadDao<Feast>{
    private static final String FILE_PATH = "src\\Resource\\FeastMenu.csv";
    
    @Override
    public List<Feast> getAll() {
        List<Feast> feastList = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine(); //pass header
            
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                
                
                if (row.length != 4) {
                    System.out.println("READ FEAST IN FILE ERROR AT LENGTH!");
                    continue;
                }
                
                Feast f = getFeastByRow(row);
                if (f != null) {
                    feastList.add(f);
                }
                
            }
        } catch(Exception e) {
            e.printStackTrace();
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
    
    private static double getDoubleSafety(String input) { // chatgpt lets go
        try {
            return Double.parseDouble(input); // Attempt to parse the string as a double
        } catch (NumberFormatException e) {
            return 0.0; // Return 0.0 if parsing fails
        }
    }
}
