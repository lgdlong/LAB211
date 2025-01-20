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
        Customer customer = null;
        
        while (true) {
            customer = InputData.inputCustomerInfor();
            if (!cusRepo.isCodeExist(customer.getCode())) {
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
    
    
    /**
     * Yeu cau nhap id
     * lay ra customer can sua
     * nhap lai het thong tin
     * 
     * 
     */
    public void updateCustomerInformation() {
        System.out.println("Enter new customer information:");
        
        Customer newCustomer = InputData.inputCustomerInfor();
        
        if (update(newCustomer)) {
            System.out.println("Update customer " + newCustomer.getCode() + " SUCCESSFUL.");
        } else {
            System.out.println("Update customer " + newCustomer.getCode() + " FAIL.");
        }
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
    
    public void displayFeast() {
        for (Feast feast : feastRepo.getFeastList()) {
            System.out.println(feast);
        }
    }
    
    public void displayCustomerHeader() {
        System.out.printf("%-5s | %-20s | %-30s | %-10s\n", "Code", "Name", "Email", "Phone");
    }
    
    public void displayOrderHeader() {
        System.out.printf("%-22s | %-11s | %-10s | %-10s | %-6s | %-12s\n", 
                "ID", "Customer ID", "Set Menu", "Price", "Tables", "Total Cost");
    }
    
    private boolean update(Customer updatedCustomer) {
        boolean found = false;
        
        if (updatedCustomer == null) {
            System.out.println("Customer cannot be null!");
            return false;
        }
        
        for (int i = 0; i < cusRepo.getCusList().size(); i++) {
            if (cusRepo.getCusList().get(i).getCode().equals(updatedCustomer.getCode())) {
                found = true;
                cusRepo.getCusList().set(i, updatedCustomer);
                return true;
            }
        }
        
        if (!found) {
            System.out.println("Customer not found!");
        }
        return false;
}
}
