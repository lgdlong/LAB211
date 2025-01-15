package Presentation;

import Utilities.DataInput;
import java.util.Arrays;
import Core.Interfaces.ICustomerDAO;
import Core.Interfaces.IEmployeeDAO;
import BussinessObject.CustomerManagement;
import BussinessObject.EmployeeManagement;
import java.util.List;

/**
 *
 * @author SwordLake
 */
public class Menu {

    public static void print(String str) {
        List<String> menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("Select")) {
                System.out.print(menuItem);
            } else {
                System.out.println(menuItem);
            }

        });
    }

    public static int getUserChoice() {
        int number = 0;
        try {
            number = DataInput.getIntegerNumber();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return number;
    }

    public static void manageEmployee(IEmployeeDAO service) {
        EmployeeManagement empMenu = new EmployeeManagement(service);
        empMenu.processMenuForEmployee();
    }

    public static void manageCustomer(ICustomerDAO service) {
        CustomerManagement cusMenu = new CustomerManagement(service);
        cusMenu.processMenuForCustomer();
    }
}
