/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Customer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author LGD
 */
public class CustomerDao implements I_FullAccessDao<Customer>{
    private static final String FILE_PATH = "src\\Resource\\customers.csv";
    private final File FILE = new File(FILE_PATH);

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public boolean save(List<Customer> cusList) {
        // check file exist first
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
        
        if (cusList.isEmpty()) {
            System.out.println("LIST IS EMPTY!");
            return false;
        }
        
        try {
            // Write the list to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                writer.write("Code,Name,Email,Phone");
                
                for (Customer customer : cusList) {
                    writer.newLine();
                    writer.write(customer.toCSVString());
                }
            }

            return true; // Return true to indicate success
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }
    
}
