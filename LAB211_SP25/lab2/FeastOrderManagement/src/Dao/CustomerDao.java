package Dao;

import Model.Customer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LGD
 */
public class CustomerDao implements I_FullAccessDao<Customer> {

    private static final String FILE_PATH = "src" + File.separator + "Resource" + File.separator + "customers.csv";
    private static final File FILE = new File(FILE_PATH);


    @Override
    public List<Customer> getAll() {
        List<Customer> cusList = new ArrayList<>();

        // check file is not exist -> create new file
        if (!FILE.exists()) {
            try {
                FILE.getParentFile().mkdirs();
                FILE.createNewFile();  // Ensure file exists
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
            return cusList;  // Return empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line = reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                // chatgpt
                if (row.length != 4) {
                    System.err.println("Invalid row format in customers.csv: " + line);
                    continue;
                }

                cusList.add(new Customer(row[0].trim(), row[1].trim(), row[2].trim(), row[3].trim()));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return cusList;
    }

    @Override
    public boolean save(List<Customer> cusList) {
        if (cusList.isEmpty()) {
            System.err.println("Warning: Attempting to save an empty customer list.");
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
            writer.write("Code,Name,Email,Phone");
            writer.newLine();

            // I'm not using stringbuilder because its may lost data when append error.
            // Fix: using loop to write each line more safety.
            for (Customer customer : cusList) {
                writer.write(toCSVString(customer));
                writer.newLine(); // Write each entry immediately
            }

            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
    
    private String toCSVString(Customer cus) {
        return String.format("%s,%s,%s,%s", 
                cus.getCode(), 
                cus.getName(), 
                cus.getEmail(), 
                cus.getPhone()
        );
    }
}
