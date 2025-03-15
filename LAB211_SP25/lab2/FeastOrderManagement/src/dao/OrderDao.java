package dao;

import model.Order;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LGD
 */
public class OrderDao implements I_FullAccessDao<Order> {
    
    private static final String FILE_PATH = "src" + File.separator + "resource" + File.separator + "feast_order_service.csv";
    private static final File FILE = new File(FILE_PATH);

    @Override
    public boolean save(List<Order> orderList) {
        if (orderList.isEmpty()) {
            System.err.println("Warning: Attempting to save an empty customer list.");
            return false;
        }
        
        try {
            // Write the list to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
                writer.write("Id,CustomerId,SetMenu,Price,Tables,Cost");
                writer.newLine();
                
                for (Order order : orderList) {
                    writer.write(toCSVString(order));
                    writer.newLine();
                }
            }

            return true; // Return true to indicate success
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false; // Return false if there was an error
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orderList = new ArrayList<>();
        
        if (!FILE.exists()) {
            System.err.println("Warning: Feast menu file not found at " + FILE_PATH);
            return orderList;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line = reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                
                
                if (row.length != 6) {
                    System.out.println("READ ORDERS IN FILE ERROR AT LENGTH!");
                    continue;
                }
                
                Order order = getOrderByRow(row);
                if (order != null) {
                    orderList.add(order);
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return orderList;
    }
    
    private Order getOrderByRow(String[] data) {
        String id = data[0].trim();
        String customerCode = data[1].trim();
        String feastCode = data[2].trim();
        int numberOfTables = Integer.parseInt(data[3].trim());
        LocalDate date = LocalDate.parse(data[4].trim(), Order.getFORMATTER());
        double totalCost = getDoubleSafety(data[5].trim());
        
        return new Order(id, customerCode, feastCode, numberOfTables, date, totalCost);
    }
    
    private double getDoubleSafety(String input) { // chatgpt lets go
        try {
            return Double.parseDouble(input); // Attempt to parse the string as a double
        } catch (NumberFormatException e) {
            return 0.0; // Return 0.0 if parsing fails
        }
    }
    
    private String toCSVString(Order order) {
        return String.join(",", 
                order.getId(),
                order.getCustomerCode(),
                order.getFeastCode(),
                String.valueOf(order.getNumberOfTables()),
                order.getDate().format(Order.getFORMATTER()),
                String.format("%.2f", order.getTotalCost())
        );
    }
}
