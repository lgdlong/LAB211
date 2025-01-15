package BussinessObject;

import Presentation.Menu;
import Utilities.DataInput;
import Core.Entities.Employee;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import Core.Interfaces.IEmployeeDAO;

/**
 *
 * @author SwordLake
 */
public class EmployeeManagement {

    IEmployeeDAO employeeDAO;

    //--------------------------------------------------  
    public EmployeeManagement(IEmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    //--------------------------------------------------  
    //--------------------------------------------------  
    public void processMenuForEmployee() {
        boolean stop = true;
        try {
            do {
                Menu.print("******Employee Management******|1.Add Employee|2.Update Employee|3.Remove Employee"
                        + "|4.Search Employees|5.Print Employee List|6.Export to file|7.Back to main menu|Select :");
                int choice = Menu.getUserChoice();
                switch (choice) {
                    case 1:
                        addNewEmployee();
                        break;
                    case 2:
                        updateEmployee();
                        break;
                    case 3:
                        deleteEmployee();
                        break;
                    case 4:
                        searchEmployees();
                        break;
                    case 5:
                        System.out.println(">>Employee List:");
                        printList(employeeDAO.getEmployees());
                        break;
                    case 6:
                        exportToFile();
                        System.out.println(">>The employee list has successfully exported.");
                        break;
                    case 7:
                        stop = false;
                    default:
                        System.out.println(">>Choice invalid");
                        break;
                }
            } while (stop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //--------------------------------------------------    

    public Employee inputEmployee() throws Exception {
        String id = DataInput.getString("Enter employee id:");
        String name = DataInput.getString("Enter employee name:");
        String email = DataInput.getString("Enter employee email:");
        return new Employee(id, name, email);
    }

    //--------------------------------------------------    
    public void setNewEmployeeInfo(Employee employee) throws Exception {
        String name = DataInput.getString("Enter new name:");
        if (!name.isEmpty()) {
            employee.setName(name);
        }
        String email = DataInput.getString("Enter new email:");
        if (!email.isEmpty()) {
            employee.setEmail(email);
        }
    }

    //--------------------------------------------------  
    public void addNewEmployee() {
        try {
            Employee employee = inputEmployee();
            if (employeeDAO.getEmployeeById(employee.getId()) != null) {
                System.out.println(">>The employee already exists.");
                return;
            }
            employeeDAO.addEmployee(employee);
            System.out.println(">>The employee has updated successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    public void updateEmployee() {
        try {
            String id = DataInput.getString("Enter employee id:");
            Employee employee = employeeDAO.getEmployeeById(id);
            if (employee == null) {
                System.out.println(">>The employee not found.");
                return;
            }
            setNewEmployeeInfo(employee);
            employeeDAO.updateEmployee(employee);
            System.out.println(">>The employee has updated successfully.");

        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    public void deleteEmployee() {
        try {
            String id = DataInput.getString("Enter employee id:");
            Employee employee = employeeDAO.getEmployeeById(id);
            if (employee == null) {
                System.out.println(">>The employee not found.");
                return;
            }
            employeeDAO.removeEmployee(employee);
            System.out.println(">>The employee has deleted successfully.");
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    public void findEmployeeById() {
        Employee employee;
        try {
            String id = DataInput.getString("Enter employee id:");
            employee = employeeDAO.getEmployeeById(id);
            if (employee != null) {
                System.out.println(employee);
            } else {
                System.out.format("The employee id : %s not found.%n", id);
            }
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    public void searchEmployees() throws Exception {
        int choice;
        boolean stop = true;
        String value;
        try {
            do {
                Menu.print("1.Search by Name|2.Search by Email|3.Back|Select:");
                choice = DataInput.getIntegerNumber();
                switch (choice) {
                    case 1:
                        value = DataInput.getString("Enter employee name:");
                        List<Employee> employees = searchEmployeeByName(value);
                        if (!employees.isEmpty()) {
                            printList(employees);
                        } else {
                            System.out.format("The employees with name:%s not found.%n", value);
                        }
                        break;
                    case 2:
                        value = DataInput.getString("Enter employee email:");
                        employees = searchEmployeeByEmail(value);
                        if (!employees.isEmpty()) {
                            printList(employees);
                        } else {
                            System.out.format("The employees with email:%s not found.%n", value);
                        }
                        break;

                    case 3:
                        stop = false;
                        break;
                    default:
                        System.out.println(">>Choice invalid");
                        break;
                }
            } while (stop);
        } catch (Exception e) {
            System.out.println(">>Error:" + e.getMessage());
        }
    }

    //--------------------------------------------------  
    public void printList(List<Employee> employees) throws Exception {
        //employeeDAO.getEmployeeList().forEach(obj -> System.out.println(obj));
        //or
        System.out.format("%-10s | %-20s | %s%n", "Employee Id", "Employee Name", "Email");
        System.out.println(String.join("", Collections.nCopies(55, "-")));
        for (Employee employee : employees) {
            System.out.format("%-11s | %-20s | %s%n",
                    employee.getId(), employee.getName(), employee.getEmail());
        }
        System.out.println(String.join("", Collections.nCopies(55, "-")));
    }

    //--------------------------------------------------  
    public void exportToFile() throws Exception {
        employeeDAO.saveEmployeeListToFile();
    }
    //--------------------------------------------------  

    public List<Employee> searchEmployeeByName(String value) throws Exception {
        Predicate<Employee> predicate = p -> p.getName().toLowerCase().
                contains(value.toLowerCase());
        return employeeDAO.search(predicate);
    }

    //--------------------------------------------------  
    public List<Employee> searchEmployeeByEmail(String value) throws Exception {
        Predicate<Employee> predicate = p -> p.getEmail().toLowerCase().
                contains(value.toLowerCase());
        return employeeDAO.search(predicate);
    }
    //--------------------------------------------------  
    //To do here..........
}
