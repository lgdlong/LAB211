package Bussiness;

import Dao.CustomerDao;
import Dao.OrderDao;
import Model.Customer;
import Model.Order;
import Repository.CustomerRepository;
import Repository.FeastRepository;
import Repository.OrderRepository;
import Utils.InputData;
import static Utils.InputData.inputCusCode;
import static Utils.InputData.inputEmail;
import static Utils.InputData.inputName;
import static Utils.InputData.inputPhone;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author LGD
 */
public class Service {
    CustomerRepository cusRepo = new CustomerRepository();
    FeastRepository feastRepo = new FeastRepository();
    OrderRepository orderRepo = new OrderRepository();
    
    public void registerCustomer() {
        Customer customer = null;
        
        while (true) {
            // ENter information
            String code = inputCusCode("Enter code: ");
            String name = inputName("Enter name: ");
            String phone = inputPhone("Enter phone: ");
            String email = inputEmail("Enter email: ");
            
            customer = new Customer(code, name, email, phone);
            
            if (!cusRepo.isExist(customer.getCode())) {
                break;
            } else {
                System.out.println("Customer code is duplicated!");
            }
        }
        
        // Save to repository and alert
        if (cusRepo.add(customer)) {
            System.out.println("Add customer " + customer.getCode() + " into repo SUCCESSFUL.");
        } else {
            System.out.println("Add customer " + customer.getCode() + " into repo FAIL.");
        }
    }
    
    public void updateCustomerInformation() {
        System.out.println("=== Update Customer Information ===");
        
        String newName, newPhone, newEmail;
        String code = InputData.inputCusCode("Enter customer code to update: ");
        
        Customer updateCus = cusRepo.getByCode(code);
        
        if (updateCus == null) {
            System.out.println("Customer not found!");
        } else {
            // ENter information
            newName = InputData.inputName_Blank("Enter name: ");
            newPhone = InputData.inputPhone_Blank("Enter phone: ");
            newEmail = InputData.inputEmail_Blank("Enter email: ");
            
            Customer newCustomer = new Customer(
                    code, 
                    newName.isBlank() ? updateCus.getName() : newName, 
                    newEmail.isBlank() ? updateCus.getEmail() : newEmail, 
                    newPhone.isBlank() ? updateCus.getPhone() : newPhone
            );
        
            if (!update(newCustomer)) {
                System.out.println("Update customer " + newCustomer.getCode() + " FAIL.");
            }
        }
    }
    
    public void searchCustomerByName() {
        System.out.print("Enter name to search: ");
        Scanner sc = new Scanner(System.in);
        String searchName = sc.nextLine().trim();
        
        List<Customer> nameList = cusRepo.getListByName(searchName);
        
        displayCustomerHeader();
        for (Customer cus : nameList) {
            System.out.println(cus);
        }
    }
    
    public void createOrder() {
        Order order = null;
        
        while (true) {
            String cusCode = InputData.inputCusCode("Enter customer code: ");
            String feastCode = InputData.inputFeastCode("Enter feast code: ");
            int numberOfTable = InputData.inputPositiveInt("Enter number of tables: ");
            LocalDate date = InputData.inputDate("Enter date (yyyy-MM-dd): ");

            order = new Order(cusCode, feastCode, numberOfTable, date, feastRepo);
            if (!orderRepo.isExist(order.getId())) {
                break;
            } else {
                System.out.println("Order is exist!");
            }
        }
        
        if (!orderRepo.add(order)) {
           System.out.println("Add order to repository FAIL.");
        }
    }
    
    public void updateOrderInformation() {
        String id = InputData.inputString("Enter order id to update: ");
        String feastCode;
        int numberOfTables;
        LocalDate newDate = null;
        
        Order order = orderRepo.getById(id);
        
        if (order == null) {
            System.out.println("Order not found.");
        } else {
            // Enter new information
            feastCode = InputData.inputFeastCode_Blank("Enter new feast id: ");
            numberOfTables = InputData.inputPositiveInt("Enter new number of tables: ");
            
            String dateStr = InputData.inputString_Blank("Enter new date (yyyy-MM-dd): ");
            
            while (!dateStr.isBlank()) {
                try {
                    newDate = toLocalDate(dateStr);
                    break;
                } catch (DateTimeParseException e) {
                    System.err.println("Invalid date format. Enter again (yyyy-MM-dd).");
                    dateStr = InputData.inputString_Blank("Enter new date again (yyyy-MM-dd): ");
                }
            }
            
            Order newOrder = new Order(
                    order.getCustomerCode(), 
                    feastCode.isBlank() ? order.getFeastCode() : feastCode, 
                    numberOfTables == -1 ? order.getNumberOfTables() : numberOfTables, 
                    newDate == null ? order.getDate() : newDate, // if newDate not change, keep old data.
                    feastRepo
            );

            // Save updated order
            if (!update(order, newOrder)) {
                System.out.println("Update order " + order.getId() + " FAIL.");
            }
        }
    }
    
    public void saveToFile() {
        CustomerDao cusDao = new CustomerDao();
        OrderDao orderDao = new OrderDao();
        
        if (!cusDao.save(cusRepo.getCusList())) {
            System.out.println("Save customers to file FAIL.");
        }
        
        if (!orderDao.save(orderRepo.getOrderList())) {
            System.out.println("Save orders to file FAIL.");
        }
        
    }
    
    public void displayCustomerList() {
        displayCustomerHeader();
        cusRepo.display();
    }
    
    public void displayOrderList() {
        displayOrderHeader();
        for (Order o : orderRepo.getOrderList()) {
            System.out.println(o);
        }
    }
    
    public void displayFeast() {
        feastRepo.display();
    }
    
    private void displayCustomerHeader() {
        System.out.printf("%-5s | %-30s | %-30s | %-10s\n", "Code", "Name", "Email", "Phone");
    }
    
    private void displayOrderHeader() {
        System.out.printf("%-22s | %-11s | %-10s | %-10s | %-6s | %-12s\n", 
                "ID", "Customer ID", "Set Menu", "Price", "Tables", "Total Cost");
    }

    /**
     * Updates an existing customer in the customer repository.
     *
     * @param updatedCustomer The customer object with updated information.
     * @return {@code true} if the customer is found and updated successfully, otherwise {@code false}.
     *         Returns {@code false} if the input is null, the list is empty, or the customer is not found.
     */
    private boolean update(Customer updatedCustomer) {
        if (updatedCustomer == null) {
            System.out.println("Customer cannot be null!");
            return false;
        }

        List<Customer> customers = cusRepo.getCusList();
        if (customers.isEmpty()) {
            System.out.println("Customer list is empty!");
            return false;
        }

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCode().equals(updatedCustomer.getCode())) {
                customers.set(i, updatedCustomer);
                return true;
            }
        }

        System.out.println("Customer not found!");
        return false;
    }

    private boolean update(Order oldOrder, Order newOrder) {
        if (newOrder == null) {
            System.out.println("Order cannot be null!");
            return false;
        }
        
        // it may throw exception, I don't know :)
        return orderRepo.add(newOrder) && orderRepo.getOrderList().remove(oldOrder);
    }
    
    private LocalDate toLocalDate(String strDate) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(strDate, formatter);
    }
}
