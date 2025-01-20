package Bussiness;

import Dao.CustomerDao;
import Dao.OrderDao;
import Model.Customer;
import Model.Feast;
import Model.Order;
import Repository.CustomerRepository;
import Repository.FeastRepository;
import Repository.OrderRepository;
import Utils.InputData;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author LGD
 */
public class Service {
    CustomerRepository cusRepo = new CustomerRepository();
    FeastRepository feastRepo = new FeastRepository();
    OrderRepository orderRepo = new OrderRepository();
    
    public void registerCustomer() {
        // ENter information
        String code = InputData.inputCusCode("Enter code: ");
        String name = InputData.inputName("Enter name: ");
        String phone = InputData.inputPhone("Enter phone: ");
        String email = InputData.inputEmail("Enter email: ");
        
        // Create a customer object
        Customer customer = new Customer(code, name, email, phone);
        
        // Save to repository and alert
        if (cusRepo.add(customer)) {
            System.out.println("Add customer " + code + " into repo SUCCESSFUL.");
        } else {
            System.out.println("Add customer " + code + " into repo FAIL.");
        }
    }
    
    public void updateCustomerInformation() {
        
    }
    
    public void searchCustomerByName() {
        String name = InputData.inputName("Enter name to search: ");
        
        List<Customer> nameList = cusRepo.getListByName(name);
        
        displayCustomerHeader();
        for (Customer cus : nameList) {
            System.out.println(cus);
        }
    }
    
    public void createOrder() {
        String cusCode = InputData.inputCusCode("Enter customer code: ");
        String feastCode = InputData.inputFeastCode("Enter feast code: ");
        int numberOfTable = InputData.inputPositiveInt("Enter number of tables: ");
        LocalDate date = InputData.inputDate("Enter date (yyyy-MM-dd): ");
        
        Order order = new Order(cusCode, feastCode, numberOfTable, date, feastRepo);
        
        if (orderRepo.add(order)) {
            System.out.println("Add order to repository SUCCESSFUL.");
        } else {
           System.out.println("Add order to repository FAIL.");
        }
    }
    
    public void updateOrderInformation() {
        
    }
    
    public void saveToFile() {
        CustomerDao cusDao = new CustomerDao();
        OrderDao orderDao = new OrderDao();
        
        if (cusDao.save(cusRepo.getCusList())) {
            System.out.println("Save customers to file SUCCESSFUL.");
        } else {
            System.out.println("Save customers to file FAIL.");
        }
        
        if (orderDao.save(orderRepo.getOrderList())) {
            System.out.println("Save orders to file SUCCESSFUL.");
        } else {
            System.out.println("Save orders to file FAIL.");
        }
        
    }
    
    public void displayCustomerList() {
        displayCustomerHeader();
        for (Customer cus : cusRepo.getCusList()) {
            System.out.println(cus);
        }
    }
    
    public void displayOrderList() {
        displayOrderHeader();
        for (Order o : orderRepo.getOrderList()) {
            System.out.println(o);
        }
    }
    
    public void displayCustomerHeader() {
        System.out.printf("%-5s | %-20s | %-30s | %-10s\n", "Code", "Name", "Email", "Phone");
    }
    
    public void displayOrderHeader() {
        System.out.printf("%-22s | %-11s | %-10s | %-10s | %-6s | %-12s\n", 
                "ID", "Customer ID", "Set Menu", "Price", "Tables", "Total Cost");
    }
    
    public void displayFeast() {
        for (Feast feast : feastRepo.getFeastList()) {
            System.out.println(feast);
        }
    }
}
